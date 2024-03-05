# Pharmacie Project

## Exigences

1. Installer Mysql
```sh
   sudo apt update
   sudo apt install mysql
   ```

2. Disposer d'une base de données : `pharmacie`.
3. Ajouter les tables `pharmacien`, `medicament`, `acheteur`.

   Astuce : Connectez-vous sur MySQL puis exécutez ```mysql> source chemin/vers/pharmacie/SQL/run.sql``` sous l'utilisateur root dans MySQL.


## Exécution du Projet Pharmacie

1. Télécharger VScode / Eclipse-IDE
2. Executer sur le terminal:
  ```sh
   git clone https://github.com/line-session/pharmacie.git
   ```
4. Ouvrir le fichier `src/Classes/Main.java` sur vscode
5. Appuyer sur `Run` 


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
