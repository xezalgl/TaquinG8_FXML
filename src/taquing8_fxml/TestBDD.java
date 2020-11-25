/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Anton
 */
public class TestBDD {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // TODO code application logic here
         ResultSet rs;
        PreparedStatement st; 

        Connection con = null;
        //recupération du pseudo et du password
        String p= "Xez3";
        String mot_de_passe= "ceBatard2";

        //vérification dans la base de donnée
       //String query ="SELECT * FROM joueur WHERE 'pseudo' = p AND 'mdp' = mot_de_passe";


         String host = "localhost";
        String port = "3309";
        String dbname = "taquin";
        String username = "root";
        String password ="";
        ConnexionBDD c = new ConnexionBDD(host, port,  dbname, username,password);
         c.openConnexion(); 
        System.out.println(""+p+mot_de_passe);
        c.insertUsers(p,mot_de_passe);
        System.out.println("Get User");
       System.out.println("L'utilisateur créer est : "+c.getUsers(p,mot_de_passe));

    }

}
