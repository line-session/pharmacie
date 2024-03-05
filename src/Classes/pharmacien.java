package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class pharmacien {
	private static String Nom;
	private static String Prenom;
	private static EnumSexe Sexe;
	private static String Addresse;
	private static int Salaire;
	
	public pharmacien() { }

	public pharmacien(String nom, String prenom, EnumSexe sexe, String addresse, int salaire) {
		super();
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		Addresse = addresse;
		Salaire = salaire;
	}

	public static String getNom() { return Nom; }

	public static void setNom(String nom) { Nom = nom; }

	public static String getPrenom() { return Prenom; }

	public static void setPrenom(String prenom) { Prenom = prenom; }

	public static EnumSexe getSexe() { return Sexe; }

	public static void setSexe(EnumSexe sexe) { Sexe = sexe; }

	public static String getAddresse() { return Addresse; }

	public static void setAddresse(String addresse) { Addresse = addresse; }

	public static int getSalaire() { return Salaire; }

	public static void setSalaire(int salaire) { Salaire = salaire; }

	public static void initialiserPharmacien(pharmacien pharmacien) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Nom: ");
		pharmacien.setNom(scanner.nextLine());
		System.out.print("Prénom: ");
		pharmacien.setPrenom(scanner.nextLine());
		System.out.print("Sexe (MASCULIN/FEMININ): ");
		String sexeInput = scanner.nextLine();
		pharmacien.setSexe(EnumSexe.valueOf(sexeInput.toUpperCase()));
		System.out.print("Adresse: ");
		pharmacien.setAddresse(scanner.nextLine());
		System.out.print("Salaire: ");
		pharmacien.setSalaire(scanner.nextInt());
	}
	public static void initialiserPharmacienDB(String nom, String prenom, EnumSexe sexe, String addresse, int salaire) {
		Connection con = ConnexionDB.connectDB();

		try {
			String query = Const.insertphar;
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, nom);
			statement.setString(2, prenom);
			statement.setString(3, sexe.toString());
			statement.setString(4, addresse);
			statement.setInt(5, salaire);
			int result = statement.executeUpdate();
			System.out.println(Const.SQLOK);
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	public void afficherPharmacien(pharmacien pharmacien) {
		System.out.println(Const.DP);
		System.out.println("Nom: " + pharmacien.getNom());
		System.out.println("Prénom: " + pharmacien.getPrenom());
		System.out.println("Sexe: " + pharmacien.getSexe());
		System.out.println("Adresse: " + pharmacien.getAddresse());
		System.out.println("Salaire: " + pharmacien.getSalaire());
		return;
	}
	public static void afficherPharmacienBD(String Nomphar) {
		Connection con = ConnexionDB.connectDB();
		try {
			String query = Const.SpharPrenom;
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, Nomphar);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				System.out.println(Const.DP);
				System.out.println("Nom: " + result.getString(2));
				System.out.println("Prenom: " + result.getString(3));
				System.out.println("Sexe: " + result.getString(4));
				System.out.println("Addresse: " + result.getString(5));
				System.out.println("Salaire: " + result.getInt(6));
			} else {
				System.out.println(Const.NotAvailablePhar);
			}
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}

	public static void vendreMedicament(String nomMedicament, int quantiteVendue) throws StockException, SigneException {
		Connection con = ConnexionDB.connectDB();
		Const.verifyStock(nomMedicament, quantiteVendue);
		try {
			String query = Const.QdelStockNom;
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, quantiteVendue);
			statement.setString(2, nomMedicament);
			int result = statement.executeUpdate();

			if (result > 0) {
				System.out.println(Const.VenteOK + nomMedicament.toUpperCase());
				System.out.println(Const.StockOK + nomMedicament.toUpperCase());
			}else 
				System.out.println(Const.StockError + nomMedicament.toUpperCase());

			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	public static void afficherDetailsMedicament(String med) {
		medicament.afficherMedicamentBD(med);
	}

	public static void ajouterMedicamentDB() throws SigneException {
		Scanner in = new Scanner(System.in);
		System.out.println(Const.AM);
		medicament m = new medicament();
		medicament.initialiserMedicament(m);
		Const.verifySigne(m.getStockDisponible());
		medicament.initialiserMedicamentDB(m.getNom(), m.getCode(), m.getPrix(), m.getDescription(),
				m.getDateExpiration(), m.getStockDisponible());
		return;
	}

	public static void ajouterPharmacienDB() {
		Scanner in = new Scanner(System.in);
		System.out.println(Const.AP);
		pharmacien P = new pharmacien();
		initialiserPharmacien(P);
		initialiserPharmacienDB(P.getNom(), P.getPrenom(), P.getSexe(), P.getAddresse(), P.getSalaire());
		return;
	}

	public static void modifierStockMedicament(String medicament, int NouveauStock) throws StockException, SigneException {
		Connection con = ConnexionDB.connectDB();
		Const.verifySigne(NouveauStock);
		try {
			String query;
			if (NouveauStock > 0) query = Const.QaddStockNom;
			else query = Const.QdelStockNom;
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, NouveauStock);
			statement.setString(2, medicament);
			int result = statement.executeUpdate();

			if (result > 0)
				System.out.println(Const.StockOK+medicament.toUpperCase());
			else 
				System.out.println(Const.StockError+medicament.toUpperCase());
			
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
}
	public static void supprimerMedicamentBD(String medicament) {
		Connection con = ConnexionDB.connectDB();

		try {
			String query = Const.Qdelnom;
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, medicament);
			int result = statement.executeUpdate();

			if (result > 0) 
				System.out.println(Const.DelOK+ medicament.toUpperCase());
			else 
				System.out.println(Const.StockError+ medicament.toUpperCase());
	
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
}
	public static void supprimerPharmacienDB(String nomPharmacien) {
		Connection con = ConnexionDB.connectDB();

		try {
			String query = Const.QdelprenomPhar;
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, nomPharmacien);
			int result = statement.executeUpdate();

			if (result > 0)
				System.out.println(Const.DelOK+ nomPharmacien.toUpperCase());
			else 
				System.out.println(Const.StockError + nomPharmacien.toUpperCase());
			
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
}
	public static void AllmedecinesDB() {
		Connection con = ConnexionDB.connectDB();
		try {
			String query = Const.selectmed;
			Statement s = con.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				System.out.println(Const.DM);
				System.out.println("Nom: " + result.getString(2));
				System.out.println("Code: " + result.getString(3));
				System.out.println("Prix: " + result.getInt(4) + " FCFA");
				System.out.println("Description: " + result.getString(5));
				System.out.println("Date Expiration: " + result.getDate(6));
				System.out.println("Stock Disponible: " + result.getInt(7));
			}
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Allpharmacien() {
		Connection con = ConnexionDB.connectDB();
		try {
			String query = Const.selectallphar;
			Statement s = con.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				System.out.println(Const.DP);
				System.out.println("Nom: " + result.getString(2));
				System.out.println("Prenom: " + result.getString(3));
				System.out.println("Sexe: " + result.getString(4));
				System.out.println("Addresse: " + result.getString(5));
				System.out.println("Salaire: " + result.getInt(6));
			}
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int HandleAuth(){
		Scanner input = new Scanner(System.in);
            String user, password;
            System.out.println("  Authentification Pharmacien  ");
            zoneAuthentification:
            do {
                System.out.print("username ~/: ");
                user = input.next();
                System.out.print("password ~/: ");
                password = input.next();
                if(user.equals("root") && password.equals("passer")){
                    break;
                }
                else{
                    System.out.print("Informations Incorrectes \nAppuyer sur [q] pour quitter\n");
                    System.out.print("~/: ");
                    String reponse = input.next();
                    if(reponse.equals("q")){
                        return 1;}
                    else{
                        continue zoneAuthentification;
                    }}
            } while (!user.equals("root") || !password.equals("passer"));
			return 0;
	}

}
