# Pharmacie Project

## Requirements

1. Disposer d'une base de données : `pharmacie`.
2. Ajouter les tables `pharmacien`, `medicament`, `acheteur` :   
   Astuce : Connectez-vous sur MySQL puis exécutez `"mysql> source chemin/vers/SQL/run.sql"` dans MySQL.

## Execution du Projet Pharmacie

1. Télécharger Vscode / Eclipse.
2. Ouvrir le projet pharmacie.
3. Modifier le fichier `Config.properties` (base de données, utilisateur, mot de passe) en fonction de votre SGBD.
4. Exécuter `ConnexionDB.java` pour vérifier la connexion vers votre SGBD.
5. Ouvrir `/src/classes/Main.java`.
6. Appuyer sur `Run`.

## Principe de Fonctionnement du Projet

### L'Internaute Peut:

- [1] Rechercher un médicament. Ensuite, acheter si l'âge est supérieur ou égal à 18, sinon une exception d'âge est déclenchée.
- [2] Quitter.

### Le Client Peut:

- [1] Acheter un médicament.
- [2] Demander des conseils.
- [3] Quitter.

### Le Pharmacien Peut :

- [1] Vendre un médicament.
- [2] Visualiser les détails d'un médicament.
- [3] Visualiser tous les médicaments.
- [4] Ajouter un médicament.
- [5] Modifier le stock d'un médicament.
- [6] Supprimer un médicament.
- [7] Afficher le pharmacien.
- [8] Visualiser tous les pharmaciens.
- [9] Ajouter un pharmacien.
- [10] Supprimer un pharmacien.
- [11] Quitter.
