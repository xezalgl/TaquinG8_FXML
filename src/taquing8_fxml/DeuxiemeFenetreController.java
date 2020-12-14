/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import static java.awt.Color.red;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hazal
 */
//
public class DeuxiemeFenetreController implements Initializable {
   //connexion
    Joueur j = new Joueur(); 
    @FXML
    private Button accesPage;
     @FXML
    private TextField saisiePseudo;
   
    @FXML
    private Button accesI;
    @FXML
    private PasswordField saisieMdp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     /**
     * passage à la fenetre de jeu si le joueur est bien connecté et présent dans la base de données 
     * @param event
     * @throws IOException 
     */
    @FXML
    public void passageDeuxiemeF (ActionEvent event ) throws IOException, ClassNotFoundException{
        String ps;
        String mdp ; 
        Joueur j = new Joueur();
        //conexion base de donees 
        String host = "localhost";
        String port = "3306";
        String dbname = "taquin";
        String username = "root";
        String password ="";
        ConnexionBDD c = new ConnexionBDD(host, port,  dbname, username,password); 
        c.openConnexion();
        //recupérer pseudo 
        ps= saisiePseudo.getText(); 
        //recuperer mdp 
         mdp= saisieMdp.getText();
        //verifie si la personne existe 
        if(c.getUsers(ps, mdp)==true){
            // enregistrement des données du joueur            
            j.setMdp(mdp);
            j.setPseudo(ps);            
            Ser ser = new Ser();
            ser.SauverJoueur(j);
             //ouverture fenetre
         Parent deuxieme  = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeFe = new Scene (deuxieme); //creation scene deuxieme fenetre 
         Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         window.setScene(deuxiemeFe); //on affiche la deuxieme fenetre 
         window.show();
         
        } 
        else {
           saisiePseudo.setStyle("-fx-text-fill: red ;") ; 
            saisieMdp.setStyle("-fx-text-fill: red ;"); 
        }
        
        
        
       
    }
     /**
     * passage à la fenetre inscription si le joueur n'a pas de compte 
     * @param event
     * @throws IOException 
     */
    @FXML 
     public void passageInscription (ActionEvent event ) throws IOException{
        Parent inscription  = FXMLLoader.load(getClass().getResource("Inscription.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene inscr = new Scene (inscription); //creation scene deuxieme fenetre 
         Stage inscriptionF = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         inscriptionF.setScene(inscr); //on affiche la deuxieme fenetre 
         inscriptionF.show();
    }
     public void connexionBdd(ActionEvent event){ 
     }
     
   /**
    * Accès à la page profil
    * @param event
    * @throws IOException 
    */
      @FXML 
     public void passageProfil (ActionEvent event ) throws IOException{
        Parent profil  = FXMLLoader.load(getClass().getResource("Profil.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene prof = new Scene (profil); //creation scene deuxieme fenetre 
         Stage profilP = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         profilP.setScene(prof); //on affiche la deuxieme fenetre 
         profilP.show();
    }
}
