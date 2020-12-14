/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Camille
 */
public class ConnexionBDD {
    private String host, port, dbname, username, password; 
    private Connection con = null;
    
    public ConnexionBDD(String h, String port, String dbn, String u, String p){
        this.host = h;
        this.port = port;
        this.dbname = dbn;
        this.username = u;
        this.password = p;
        
    }
    /**Fermeture de la connexion 
     * 
     */
    public void closeConnexion() {
        if(con != null){
            try{
                con.close();
                System.out.println("Database Connection terminated");
            } catch (Exception e){
                /* ignore close errors*/}
        }
    }
    
    /**Connexion avec la base de donnée taquin 
     * 
     */
    public void openConnexion() {
        String connectUrl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname;
        if (con != null) {
            this.closeConnexion();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(connectUrl, username, password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
            //Fermeture propre du jeu
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
            //Fermeture propre du jeu
            System.exit(0);
        }
    }
    
    /**
     * Méthode qui permet de créer un nouveau joueur dans la base de donnée 
     * @param pseudo recupere le pseudo 
     * @param mdp recupere le mot de passe 
     */
    public void insertUsers(String pseudo, String mdp) {
        try {
            this.openConnexion();
            Statement stmt = con.createStatement();
            String query = "INSERT INTO `joueur`( `pseudo`, `mdp`, `meilleur_score`, `nb_partie`) VALUES ('" + pseudo + "','" + mdp + "', 0,0)";
            stmt.executeUpdate(query);
            System.out.println(" Joueur crée ! Bonne chance!");
            stmt.close();
         

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete d'insertion");
            System.out.println("Utilisateur déjà existant"); 
           
        } finally {
            this.closeConnexion();
        }
       

    }
    
    
    
    /** 
     * Méthode qui recherche un joueur dans la BDD
     * @param pseudo recupere le pseudo 
     * @param mdp    recupere le mot de passe 
     * @return  si l'utilisateur existe 
     */
    public boolean getUsers(String pseudo, String mdp){
        boolean existe = false;
        
        try {
            this.openConnexion();
            Statement stmt = con.createStatement();
            // Requete SQL pour recuper les pseudos des joueurs
            String query = "SELECT pseudo FROM joueur";
            //execution de cette requete 
            ResultSet resultSet = stmt.executeQuery(query);
                while (resultSet.next() || !existe){
                    String p = resultSet.getString("pseudo");
                    // Si la saisie est identitque à un joueur present dans la BDD on l'affiche 
                    if(p.equals(pseudo)){
                        String query2 = "SELECT mdp FROM joueur WHERE pseudo = '" + pseudo + "' ";                   
                        ResultSet rs = stmt.executeQuery(query2);
                        while (rs.next()) {
                        String m = rs.getString("mdp");
                        //si la saisie est identique à un joueur present dans la BDD on l'affiche
                        if (m.equals(mdp)) {

                            existe = true;
                    }
                }
        }
    }
}catch (SQLException e){
            
}
        return existe;
}
    
    /**Affichage des informartions concernant le joueur , le nombre de partie jouée et le meilleur score
     * 
     * @param pseudo recupère le pseudo 
     * @param mdp  recupère le mot de passe 
     */
    public void afficheInformation(String pseudo, String mdp){
        int nb_p = 5;
        int meilleur_score = 4;
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT nb_partie, meilleur_score from joueur WHERE pseudo = '" + pseudo + "'  AND mdp ='" + mdp + "' ";
            ResultSet resultSet = stmt.executeQuery(query);
            System.out.println(query);
            while (resultSet.next()) {
                nb_p = (resultSet.getInt("nb_partie"));
                meilleur_score = (resultSet.getInt("meilleur_score"));
                System.out.println("Vous avez joué " + nb_p + "parties et votre meilleur score est de" + meilleur_score );
            }
        } catch (Exception e) {
            System.out.println("Probleme avec l'affichage ");
        }
        
        
    }
    
   /** Si le joueur gagne 
    * 
    * @param pseudo recupere le pseudo du joueur 
    */
    public void updateVictoire(String pseudo) {
        int valeur;
        System.out.println("Résultat base de donnée : ");

        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM joueur WHERE `pseudo`='" + pseudo + "' ");
            while (rs.next()) {
                valeur = rs.getInt("nb_partie");
                valeur= valeur + 1;
                String query = "UPDATE `joueur` SET `nb_partie`='" + valeur + "'  WHERE `pseudo`='" + pseudo + "' ";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                System.out.println("le joueur "+pseudo+" a joué"+valeur+" parties");

            }
            
        } catch (Exception e) {
            System.out.println("Probleme avec l'insertion ");
        }
        
    }

    
    
    
   /**
    * changement de pseudo 
    * @param pseudo
    * @param mdp
    * @param newPseudo 
    */
    
    
    public void updatePseudoBDD(String pseudo, String mdp, String newPseudo){
        try {
            this.openConnexion();
            Statement stmt = con.createStatement();
            String query = " UPDATE joueur SET pseudo = '" + newPseudo + "' WHERE pseudo='" + pseudo + "' AND mdp = '" + mdp + "' "; 
              stmt.executeUpdate(query);
            }catch (SQLException e){
            System.out.println("problème avec le nouveau pseudo");
    }

    }

  /**
   * changement de mot de passe 
   * @param pseudo
   * @param mdp
   * @param newMdp 
   */

    //changement de mot de passe
    public void updateMdpBDD(String pseudo, String mdp, String newMdp){

        try {
            this.openConnexion();
            Statement stmt = con.createStatement();
            String query = " UPDATE joueur SET mdp = '" + newMdp + "' WHERE pseudo='" + pseudo + "' AND mdp = '" + mdp + "' ";
              stmt.executeUpdate(query);



            }catch (SQLException e){
            System.out.println("problème avec le nouveau pseudo");
    }

    }
    
    
    
}
