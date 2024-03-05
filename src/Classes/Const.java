package Classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Const {
    public static final String AP = "--Ajouter Pharmacien--";
    public static final String AM = "--Ajouter Medicament--";
    public static final String DP = "__Details Pharmacien__";
    public static final String DM = "__Details Medicament__";
    public static final String Wait = "En cours ...";
    public static final String Thanks = "Merci de votre visite !";
    public static final String Asking = "Souhaitez-vous continuer(OUI/NON): ";
    public static final String AskingToBuy = "Souhaitez-vous acheter(OUI/NON): ";
    public static final String NotAvailablePhar = "Pharmacien Indisponible";
    public static final String NotAvailableMed = "Medicament Indisponible";
    public static final String NotAvailableMedecin = "Désolé, le médecin est indisponible pour le moment.";
    public static final String VenteOK = "Vendu avec succès: ";
    public static final String AchatOK = "Acheter avec succès: ";
    public static final String StockOK = "Stock mis à jour avec succès: ";
    public static final String StockError = "Impossible de supprimer: ";
    public static final String DelOK = "Suppression avec succès de : ";
    public static final String Qdelnom = "DELETE FROM medicament WHERE Nom = ?";
    public static final String Qdelprenom = "DELETE FROM medicament WHERE Prenom = ?";
    public static final String QdelprenomPhar = "DELETE FROM pharmacien WHERE Prenom = ?";
    public static final String selectmed = "SELECT * FROM medicament";
    public static final String selectallphar = "SELECT * FROM pharmacien";
    public static final String SpharPrenom = "SELECT * FROM pharmacien WHERE Prenom=?";
    public static final String SQLOK = "Query OK, 1 row affected";
    public static final String insertphar = "INSERT INTO pharmacien(Nom, Prenom, Sexe, Addresse, Salaire) VALUES (?, ?, ?, ?, ?)";
    public static final String insertmed = "INSERT INTO medicament(Nom, Code, Prix, Descriptions, DateExpiration, StockDisponible) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String insertacheteur = "INSERT INTO acheteur(Prenom, Nom, Age, CIN) VALUES (?, ?, ?, ?)";
    public static final String QaddStockNom = "UPDATE medicament SET StockDisponible = StockDisponible + ? WHERE Nom = ?";
    public static final String QdelStockNom = "UPDATE medicament SET StockDisponible = StockDisponible - ? WHERE Nom = ?";
    public static final String QselmedNom = "SELECT * FROM medicament WHERE Nom=?";

    public static void verifyAge(int age) throws AgeException {
        if (age < 18) {
            throw new AgeException("Minimum 18 ans");
        }
    }

    public static void verifyStock(String nom_medicament, int amount) throws StockException, SigneException {
        verifySigne(amount);
        try (Connection con = ConnexionDB.connectDB();
             PreparedStatement statement = con.prepareStatement(QselmedNom)) {
            statement.setString(1, nom_medicament);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int stockDisponible = result.getInt(7);
					int verifier = stockDisponible - amount;
                    if (verifier < 0) {
                        throw new StockException("Stock Insuffisant");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StockException("Erreur de base de données");
        }
    }

    public static void verifySigne(int amount) throws SigneException {
        if (amount < 0) {
            throw new SigneException("Une valeur positive est requise");
        }
    }
}
