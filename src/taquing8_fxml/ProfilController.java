/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

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
   Deser deser = new Deser(); 
  
   Joueur j1; 
   
    public ProfilController() throws ClassNotFoundException {
        this.j1 = deser.ChargerJoueur();
    }
  
     
 
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
    public void changementMdp(ActionEvent event ){
        String ps;
        String asPs; 
        String mdp ; 
        //conexion base de donees 
        String host = "localhost";
        String port = "3309";
        String dbname = "taquin";
        String username = "root";
        String password ="";
        ConnexionBDD c = new ConnexionBDD(host, port,  dbname, username,password); 
        c.openConnexion();
      
        //System.out.println("recupération mdp"+j.toString());
        //recupérer pseudo 
       
       
        ps= saisieNvPs.getText(); 
        //recuperer mdp 
        mdp= saisieNvMDP.getText(); 
        if( j1.getMdp()!=mdp){
            System.out.println("passage boucle"+ps+mdp);
            c.updateMdpBDD(ps,j1.getMdp(),mdp);
           j1.setMdp(mdp);
        }
        
     
        
    }
    /**
     * changement du pseudo dans l'onglet modification de profil 
     * @param event 
     */

    @FXML
    private void changementPseudo(ActionEvent event) {
    }
}
