package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class medicament {
	private static String Nom;
	private static String Code;
	private static int Prix;
	private static String Description;
	private static Date DateExpiration;
	private static int StockDisponible;
	
	public medicament () {}
	
	public medicament(String nom, String code, int prix, String description, Date dateExpiration, int stockDisponible) {
		super();
		Nom = nom;
		Code = code;
		Prix = prix;
		Description = description;
		DateExpiration = dateExpiration;
		StockDisponible = stockDisponible;
	}
	
	public static String getNom() {
		return Nom;
	}
	public static void setNom(String nom) {
		Nom = nom;
	}
	public static String getCode() {
		return Code;
	}
	public static void setCode(String code) {
		Code = code;
	}
	public static int getPrix() {
		return Prix;
	}
	public static void setPrix(int prix) {
		Prix = prix;
	}
	public static String getDescription() {
		return Description;
	}
	public static void setDescription(String description) {
		Description = description;
	}
	public static Date getDateExpiration() {
		return DateExpiration;
	}
	public static void setDateExpiration(Date dateExpiration) {
		DateExpiration = dateExpiration;
	}
	public static int getStockDisponible() {
		return StockDisponible;
	}
	public static void setStockDisponible(int stockDisponible) { StockDisponible = stockDisponible; }
	public static void initialiserMedicamentDB(String nom, String code, int prix, String description, Date date, int stockDisponible) {
		Connection con = ConnexionDB.connectDB();
		
		try {
			String query = Const.insertmed;
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, nom);
			statement.setString(2, code);
			statement.setInt(3, prix);
			statement.setString(4, description);
			statement.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
			statement.setInt(6, stockDisponible);
			int result = statement.executeUpdate();
			System.out.println(Const.SQLOK);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	public static void initialiserMedicament(medicament medicament) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Nom: ");
	    Classes.medicament.setNom(scanner.nextLine());
	    System.out.print("Code: ");
	    Classes.medicament.setCode(scanner.nextLine());
	    System.out.print("Prix: ");
	    Classes.medicament.setPrix(scanner.nextInt());
	    scanner.nextLine(); // Consommer la nouvelle ligne
	    System.out.print("Description: ");
	    Classes.medicament.setDescription(scanner.nextLine());
	    System.out.print("Date Expiration (YYYY-MM-DD): ");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(scanner.nextLine());
            Classes.medicament.setDateExpiration(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	    System.out.print("Stock Disponible: ");
	    Classes.medicament.setStockDisponible(scanner.nextInt());
}
	public static void afficherMedicament(medicament medicament) {
		System.out.println(Const.DM);
		System.out.println("Nom: "+Classes.medicament.getNom());
		System.out.println("Code: "+Classes.medicament.getCode());
		System.out.println("Prix: "+Classes.medicament.getPrix());
		System.out.println("Description: "+Classes.medicament.getDescription());
		System.out.println("Date Expiration: "+Classes.medicament.getDateExpiration());
		System.out.println("Stock Disponible: "+Classes.medicament.getStockDisponible());
}
	public static void afficherMedicamentBD(String med) {
		Connection con = ConnexionDB.connectDB();
		try {
			String query = Const.QselmedNom;
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, med);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				System.out.println(Const.DM);
				System.out.println("Nom: "+result.getString(2));
				System.out.println("Code: "+result.getString(3));
				System.out.println("Prix: "+result.getInt(4)+" FCFA");
				System.out.println("Description: "+result.getString(5));
				System.out.println("Date Expiration: "+result.getDate(6));
				System.out.println("Stock Disponible: "+result.getInt(7));
				
			} else {
				System.out.println(Const.NotAvailableMed);
			}
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
