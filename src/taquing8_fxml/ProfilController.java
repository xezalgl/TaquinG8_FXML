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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hazal
 */
public class ProfilController implements Initializable  {
    @FXML
    private TextField saisieNvPs;
    @FXML
    private PasswordField saisieNvMDP;
    @FXML
    private Button nouveauPs;
    @FXML
    private Button nouveauMdp;
    Joueur j = new Joueur();
    //deserialisation du joueur
    Deser deser = new Deser();
    @FXML
    private Button precedent;
    
  
     
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 

    /**
     * changement du mot de passe dans l'onglet modification de profil 
     * @param event 
     */
    @FXML
    private void changementMdp(ActionEvent event ) throws ClassNotFoundException{
        j=deser.ChargerJoueur();
        String ps;
        String asPs; 
        String mdp ; 
        //conexion base de donees 
        String host = "localhost";
        String port = "3306";
        String dbname = "taquin";
        String username = "root";
        String password ="";
        ConnexionBDD c = new ConnexionBDD(host, port,  dbname, username,password); 
        c.openConnexion();
        //recuperer mdp 
        mdp= saisieNvMDP.getText(); 
        if( j.getMdp()!=mdp){            
            c.setMdpBDD(j.getPseudo(),j.getMdp(),mdp);
           j.setMdp(mdp);

        }
    }
    /**
     * changement du pseudo dans l'onglet modification de profil 
     * @param event 
     */

    @FXML
    private void changementPseudo(ActionEvent event) throws ClassNotFoundException {
        j=deser.ChargerJoueur();
        String ps;
        String asPs; 
        String mdp ; 
        //conexion base de donees 
        String host = "localhost";
        String port = "3306";
        String dbname = "taquin";
        String username = "root";
        String password ="";
        ConnexionBDD c = new ConnexionBDD(host, port,  dbname, username,password); 
        c.openConnexion();
        ps= saisieNvPs.getText(); 
        if( j.getPseudo()!=ps){
            c.setPseudoBDD(j.getPseudo(),j.getMdp(),ps);
           j.setPseudo(ps);

        }



    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
        Parent deuxiemeFenetre  = FXMLLoader.load(getClass().getResource("FXMLDocumentController.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeF = new Scene (deuxiemeFenetre); //creation scene deuxieme fenetre 
         Stage fenetre = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         fenetre.setScene(deuxiemeF); //on affiche la deuxieme fenetre 
         fenetre.show();
    }
}
