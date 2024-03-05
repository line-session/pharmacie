package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {
	public static void demanderConseil() {
		System.out.println(Const.NotAvailableMedecin);
		return;
	}
	public static void acheterMedicament(String motif) throws StockException, SigneException {
		Scanner input = new Scanner(System.in);
		Connection con = ConnexionDB.connectDB();
		System.out.print("Quantité: ");
		int quantite = input.nextInt();
		Const.verifyStock(motif, quantite);
		try {
			// Actualiser le nouveau stock
			String query = Const.QdelStockNom;
	        PreparedStatement statement = con.prepareStatement(query);
	        statement.setInt(1, quantite);
	        statement.setString(2, motif);
	        int result = statement.executeUpdate();
	        
	        // Affichage Ticket
			String query2 = Const.QselmedNom;
			PreparedStatement statement2 = con.prepareStatement(query2);
			statement2.setString(1, motif);
			ResultSet result2 = statement2.executeQuery();
			if(result2.next()) {
				input.nextLine();
				System.out.print("Prenom: ");
				String prenom = input.nextLine();
				System.out.print("Nom: ");
				String nom = input.nextLine();
				int prix = result2.getInt("Prix");
				int total = prix*quantite;
		        
				System.out.println();
				System.out.println("----TRANSACTION----");
				System.out.println("--PHARMACIE XENON--");
				System.out.println("Client: "+prenom+" "+nom.toUpperCase());
				System.out.println("Achat: "+motif.toUpperCase());
				System.out.println("Quantité: "+quantite);
				System.out.println("Total: "+total+" FCFA");
				System.out.println();
				System.out.println(Const.Thanks);
			}else {
				System.out.println(Const.NotAvailableMed);
				return;
			}
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}	
	}
}
