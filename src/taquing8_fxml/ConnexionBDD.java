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
 * @author hazal
 *
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
    
    
    
    /**
     * Méthode permettant la fermeture de la connexion avec la base de donnée taquin
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
    
    /**
     *Méthode permettant la connexion avec la base de donnée taquin 
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
     * Méthode permettant la création d'un nouveau joueur dans la table joueur
     * @param pseudo    le pseudo du joueur
     * @param mdp       le mot de passe du joueur
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
   * Méthode permettant d'obtenir l'identifiant personnel d'un joueur dans la table joueur 
   * @param pseudo  le pseudo du joueur
   * @param mdp     le mot de passe du joueur 
   * @return Renvoie l'identifiant du joueur 
   */   
    public int getId(String pseudo, String mdp){
        int id = 5;
        
        try {
            this.openConnexion();
            Statement stmt = con.createStatement();
            // Requete SQL pour recuper les pseudos des joueurs
            String query = " SELECT `id_user` FROM `joueur` WHERE pseudo = '" + pseudo + "' AND mdp = '" + mdp + "' ";
             ResultSet resultSet = stmt.executeQuery(query);  
             while (resultSet.next()) {          
             id = resultSet.getInt("id_user");
             }   
       
    
}catch (SQLException e){
            System.out.println("problème avec l");
    }
        return id;
    }
    
    
 /**
  * Méthode permettant de rechercher un joueur dans le table joueur 
  * @param pseudo   le pseudo du joueur 
  * @param mdp      le mot de passe du joueur  
  * @return Renvoie true si l'utilisateur est présent dans la table joueur sinon renvoie false
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
    
    /**
     * Méthode qui permet d'afficher les informations du joueur présent dans la table joueur. 
     * @param pseudo    le pseudo du joueur
     * @param mdp       le mot de passe du joueur
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
    /**
     * Méthode permettant d'actualiser le nombre de partie joué par le joueur dans la table joueur.
     * @param pseudo pseudo du joueur
     */
    public void updateNbPartie(String pseudo) {
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
     * Méthode permettant au joueur de changer de pseudo dans la table joueur. 
     * @param pseudo    Le pseudo du joueur
     * @param mdp       le mot de passe du joueur
     * @param newPseudo le nouveau pseudo du joueur  
     */
    //changement de pseudo
    public void setPseudoBDD(String pseudo, String mdp, String newPseudo){         
        try {
            this.openConnexion();
            Statement stmt = con.createStatement();        
            String query = " UPDATE `joueur` SET pseudo = '" + newPseudo + "' WHERE `pseudo`='" + pseudo + "' AND mdp = '" + mdp + "' "; 
              stmt.executeUpdate(query);
            }catch (SQLException e){
            System.out.println("problème avec le nouveau pseudo");
    }
       
    }
        
        
    /**
     * Methode permettant au joueur de changer de mot de passe dans la table joueur.
     * @param pseudo    le pseudo du joueur
     * @param mdp       le mot de passe du joueur
     * @param newMdp    le nouveau mot de passe 
     */
    public void setMdpBDD(String pseudo, String mdp, String newMdp){
          
        try {
            this.openConnexion();
            Statement stmt = con.createStatement();
            String query = " UPDATE `joueur` SET mdp = '" + newMdp + "' WHERE `pseudo`='" + pseudo + "' AND mdp = '" + mdp + "' ";
              stmt.executeUpdate(query);
             
       
    
            }catch (SQLException e){
            System.out.println("problème avec le nouveau pseudo");
    }
       
    }
    
    
    
 /**
  * Méthode permettant l'insertion dans la table classement d'une partie. 
  * @param pseudo   le pseudo du joueur
  * @param mdp      le mot de passe du joueur
  * @param score   le score du joueur de la partie actuelle
  * @param mode     le type de grille auquel le joueur joue
  */
     public void creationPartie(String pseudo, String mdp, int score, int mode){
         int id = this.getId(pseudo,mdp);
             this.openConnexion();
        if(this.getPartie(this.getId(pseudo, mdp), mode)) {
        // si le score de l'ancienne partie est supérieur on change rien si il est inéfieur on udpdate     
        if(this.getScorePartie(id, mode)<score){
            this.setScore(id, mode, score);
            
        }  else{
            this.closeConnexion();
        } 
            
            
        
        }else{
                    try {
        
            
            System.out.println("le id est"+ id);
            Statement stmt = con.createStatement();
                String query = "INSERT INTO `classement`( `id_joueur`, `score`, `Mode`) VALUES ('" + id + "','" + score + "','" + mode + "')";
                stmt.executeUpdate(query);
                System.out.println("partie crée !");
            
         

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete d'insertion");

           
        } finally {
            this.closeConnexion();
        }
        
    
     }
     }
     /**
      * Méthode permettant d'accèder au score de la partie d'un joueur
      * @param id   identifiant personnel du joueur
      * @param mode Mode de jeu 
      */ 
     public int getScorePartie(int id, int mode){
        int score = 666;
        this.openConnexion();
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT score from classement WHERE id_joueur = '" + id + "'  AND Mode ='" + mode + "' ";
            ResultSet resultSet = stmt.executeQuery(query);
            
            while (resultSet.next()) {
                score = (resultSet.getInt("score"));            
                System.out.println("le score est de "+score);
            }
        } catch (Exception e) {
            System.out.println("Probleme avec l'accès au score ");
        }
        return score;  
     }
     
     
     
     
     
     

     /**
      * 
      * @param id 
      * @param mode
      * @return Renvoie vrai si une partie avec ce joueur et ce mode est déjà créer
      */
     public boolean getPartie(int id, int mode){
        boolean existe = false;
        
        try {
            this.openConnexion();
            Statement stmt = con.createStatement();         
            String query = "SELECT id_joueur FROM classement";
            //execution de cette requete 
            ResultSet resultSet = stmt.executeQuery(query);
                while (resultSet.next() || !existe){
                    int p = resultSet.getInt("id_joueur");
                    // Si la saisie est identitque à un joueur present dans la BDD on l'affiche 
                    if(p==id){
                        String query2 = "SELECT Mode FROM classement WHERE id_joueur = '" + id + "' ";                   
                        ResultSet rs = stmt.executeQuery(query2);
                        while (rs.next()) {
                        int mo = rs.getInt("Mode");
                        //si la saisie est identique à un joueur present dans la BDD on l'affiche
                        if (mo==mode) {

                            existe = true;
                    }
                }
        }
    }
}catch (SQLException e){
            
}
        return existe;
}
    
    
     
     /**
      * Méthode qui permet de modifier dans classement le score
      * @param id   identifiant du joueur
      * @param mode Mode du jeu 
      * @param score Nouveau score
      */
     public void setScore(int id, int mode, int score){
        

        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM classement WHERE `id_joueur`='" + id + "' ");
            while (rs.next()) {               
                String query = "UPDATE `classement` SET `score`='" + score + "'  WHERE `id_joueur`='" + id + "' AND Mode ='" + mode + "' ";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);           

            }
            
        } catch (Exception e) {
            System.out.println("Probleme avec l'update du score ");
         
     }
     
     }
     
     
     
     /**
      * Méthode qui permet d'obtenir le rang d'un joueur sur un mode
      * @param id   identifiant du joueur
      * @param mode Le mode 
      * @return Renvoie le rank du joueur pour ce mode
      */
     public int getRank(int id, int mode){
         int rank = 0;
           try {
            this.openConnexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_joueur FROM classement WHERE Mode ='" + mode + "' ORDER BY 'score' DESC   ");
            while (rs.next()) { 
                
                if (rs.getInt("id_joueur")==id){
                    rank = rs.getRow();
                
            }          

            }
            
            
            
            
        } catch (Exception e) {
            System.out.println("Probleme avec le classement ");
         
     }
         
         
         
         
         return rank;
     }

   
     
}