package Classes;

import java.util.Scanner;

public class Main {
    private static final String MENU_INTERNAUTE = "----BIENVENUE SUR LE SITE WEB----\n[1] Rechercher Medicament\n[2] Exit\n~/: ";
    private static final String MENU_CLIENT = "----BIENVENUE À LA PHARMACIE XENON----\n[1] Acheter Medicament\n[2] Demander Conseils\n[3] Exit\n~/: ";
    private static final String MENU_PHARMACIEN = "---BIENVENUE PHARMACIEN---\n[1] Vendre medicament\n[2] Visualiser Details Medicaments\n[3] Visualiser tous les medicaments\n[4] Ajouter Medicaments\n[5] Modifier Stock Medicament\n[6] Supprimer Medicament\n[7] Afficher Pharmacien\n[8] Visualiser tous les pharmaciens\n[9] Ajouter Pharmacien\n[10] Supprimer Pharmacien\n[11] Exit\n~/: ";

    public static void main(String[] args) throws AgeException, StockException, SigneException{
        Scanner in = new Scanner(System.in);
        String choix;

        while (true) {
            System.out.println("CHOISISSEZ UN ACTEUR:");
            System.out.println("[1] INTERNAUTE");
            System.out.println("[2] CLIENT");
            System.out.println("[3] PHARMACIEN");
            System.out.println("[4] EXIT");
            System.out.print("~/: ");
            choix = in.nextLine().toUpperCase();

            switch (choix) {
                case "INTERNAUTE":
                case "1":
                    interagirAvecInternaute(in);
                    break;

                case "CLIENT":
                case "2":
                    interagirAvecClient(in);
                    break;

                case "PHARMACIEN":
                case "3":
                    interagirAvecPharmacien(in);
                    break;
                
                case "EXIT":
                case "4":
                	return;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }

    private static void interagirAvecInternaute(Scanner in) throws AgeException, StockException, SigneException {
        String choixInternaute;
        zoneInternaute:
        while (true) {
            System.out.print(MENU_INTERNAUTE);
            choixInternaute = in.nextLine();
            
            switch (choixInternaute) {
                case "1":
                    System.out.print("Rechercher un medicament: ");
                    String medicament = in.nextLine();
                    System.out.println(Const.Wait);
                    Internaute.rechercherMedicament(medicament);
                    break;

                case "2":
                    break zoneInternaute;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }

    private static void interagirAvecClient(Scanner in) throws StockException, SigneException {
        String choixClient;
        zoneClient:
        while (true) {
            System.out.print(MENU_CLIENT);
            choixClient = in.nextLine();

            switch (choixClient) {
                case "1":
                    System.out.print("Medicament: ");
                    String med = in.nextLine();
                    Client.acheterMedicament(med);
                    break;

                case "2":
                	Client.demanderConseil();
                    break;

                case "3":
                    break zoneClient;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }

    private static void interagirAvecPharmacien(Scanner in) throws StockException, SigneException {
        String choixPharmacien;
        zonePharmacien:
        while (true) {

            int verification = pharmacien.HandleAuth();
            zoneHandle:
            if (verification == 0){ break zoneHandle; }
            else { return; }

            System.out.print(MENU_PHARMACIEN);
            choixPharmacien = in.nextLine();
            switch (choixPharmacien) {
	            case "1":
	            	System.out.print("Medicament: ");
	            	String sellmed = in.nextLine();
	            	System.out.print("Quantité: ");
	            	int sellquantite = in.nextInt();
	            	in.nextLine();
	            	pharmacien.vendreMedicament(sellmed, sellquantite);
	            	System.out.println();
	            	break;
	            	
                case "2":
                	System.out.print("Medicament: ");
					String findmed = in.nextLine();
					pharmacien.afficherDetailsMedicament(findmed);
					System.out.println();
                    break;

                case "3":
                	System.out.println(Const.Wait);
                	pharmacien.AllmedecinesDB();
                	System.out.println();
                	break;
                	
                case "4":
                	pharmacien.ajouterMedicamentDB();
                	System.out.println();
                	break;
                	
                case "5":
                	System.out.print("Medicament: ");
					String nom_med = in.nextLine();
					System.out.print("Quantité: ");
					int modifquantite = in.nextInt();
					in.nextLine();
					pharmacien.modifierStockMedicament(nom_med, modifquantite);
					System.out.println();
                    break;
                    
                case "6":
                	System.out.print("Medicament: ");
                	String delmed = in.nextLine();
                	pharmacien.supprimerMedicamentBD(delmed);
                	System.out.println();
                	break;

                case "7":
                	System.out.print("Prenom Pharmacien: ");
					String phar = in.nextLine();
					pharmacien.afficherPharmacienBD(phar);
					System.out.println();
                    break;

                case "8":
                    System.out.println(Const.Wait);
                    pharmacien.Allpharmacien();
                    System.out.println();
                    break;

                case "9":
                	pharmacien.ajouterPharmacienDB();
					System.out.println();
                    break;

                case "10":
                	System.out.print("Prenom Pharmacien: ");
					String delphar = in.nextLine();
					pharmacien.supprimerPharmacienDB(delphar);
					System.out.println();
                    break;
                case "11":
                    break zonePharmacien;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }
}
