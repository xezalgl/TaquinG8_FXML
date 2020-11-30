/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hazal
 */
public class InscriptionController implements Initializable {
    //inscription
    @FXML
    private TextField saisiePseudo;
    @FXML
    private Button accesPage;
    @FXML
    private TextField saisieMdp;
    @FXML
    private TextField saisieMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void Image (){
        
    }
/**
 * Création de multifenetre qui permet de s'inscrire 
 * @param event
 * @throws IOException 
 */
    @FXML
    
        public void passageInscrip (ActionEvent event) throws IOException{
        String ps;
        String mdp ; 
        //conexion à la base de donees 
        String host = "localhost";
        String port = "3309";
        String dbname = "taquin";
        String username = "root";
        String password ="";
        ConnexionBDD c = new ConnexionBDD(host, port,  dbname, username,password); 
        c.openConnexion();
        //recupérer pseudo 
        ps= saisiePseudo.getText(); 
        //recuperer mdp 
        mdp= saisieMdp.getText(); 
        System.out.println("coucoun"+ps+mdp);
       // c.insertUsers(ps, mdp);
       
       if(c.getUsers(ps, mdp)==false){
           c.insertUsers(ps, mdp);
       
             //ouverture fenetre 
        Parent deuxieme  = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeFe = new Scene (deuxieme); //creation scene deuxieme fenetre 
         Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         window.setScene(deuxiemeFe); //on affiche la deuxieme fenetre 
         window.show();
        } 
      
       else {
           //si le pseudo existe déjà on mets les lettre à rouge 
            saisiePseudo.setStyle("-fx-text-fill: red ;") ; 
            saisieMdp.setStyle("-fx-text-fill: red ;"); 
       }
    }
}
        
     
    

