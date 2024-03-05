package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Internaute {
	
	public static void rechercherMedicament(String motif) throws AgeException, StockException, SigneException {
		Scanner input = new Scanner(System.in);
		Connection con = ConnexionDB.connectDB();

		try {
			String query = Const.QselmedNom; // SQL INJECTION
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, motif);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
			do {
				System.out.println("Nom: "+result.getString(2));
				System.out.println("Description: "+result.getString(5));
				System.out.println("Prix: "+result.getInt(4)+" FCFA");
				System.out.println("Stock Disponible: "+result.getString(7));
			} while(result.next());
			} else {
				System.out.println(Const.NotAvailableMed);
				return;
			}
			
			System.out.print(Const.AskingToBuy);
			String reponse = input.next();
			
			if("oui".equalsIgnoreCase(reponse) || "o".equalsIgnoreCase(reponse)) 
				acheterMedicament(motif);
			else 
				System.out.print(Const.Thanks);
				System.out.println();
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}	
	}
	public static void acheterMedicament(String motif) throws AgeException, StockException, SigneException {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Quantité: ");
		int quantite = input.nextInt();
		input.nextLine();
		Const.verifyStock(motif, quantite);
		System.out.print("Prenom: ");
		String prenom = input.nextLine();
		System.out.print("Nom: ");
		String nom = input.nextLine();
		int age;
		do {
			System.out.print("Age: ");
			age = input.nextInt();
			Const.verifyAge(age);
		} while (age<18);
		
		System.out.print("Numero CIN: ");
		String CIN = input.next();
		
		try {
			Connection con = ConnexionDB.connectDB();
			
			// Insertion Informations
			String query1 = Const.insertacheteur; // SQL Injection
			PreparedStatement statement1 = con.prepareStatement(query1);
			statement1.setString(1, prenom);
			statement1.setString(2, nom);
			statement1.setInt(3, age);
			statement1.setString(4, CIN);
			int result1 = statement1.executeUpdate();
			
			// Affichage Ticket
			String query2 = Const.QselmedNom;
			PreparedStatement statement2 = con.prepareStatement(query2);
			statement2.setString(1, motif);
			ResultSet result2 = statement2.executeQuery();
			if(result2.next()) {
				int prix = result2.getInt(4);
				int total = prix*quantite;
				System.out.println("Total: "+total+" FCFA");
				System.out.println(Const.Asking);
				String reponse = input.next();
				
				System.out.println();
				if ("oui".equalsIgnoreCase(reponse) || "o".equalsIgnoreCase(reponse)) {
					
					System.out.println(Const.AchatOK+motif.toUpperCase());
				    System.out.println("----TRANSACTION----");
				    System.out.println("---PHARMACIE XENON---");
				    System.out.println("Client: "+prenom+" "+nom.toUpperCase());
				    System.out.println("Achat: "+motif.toUpperCase());
				    System.out.println("Quantité: "+quantite);
				    System.out.println("Total: "+total+" FCFA");
				    System.out.println();
				    System.out.println(Const.Thanks);
				    System.out.println();
				    
				} else if("non".equalsIgnoreCase(reponse)) {
				    System.out.println(Const.Thanks);
				    System.out.println();
				    return;
				}
			}
			con.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}	
	}
}
