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
    Joueur j = new Joueur();
    //deserialisation du joueur
    Deser deser = new Deser();
    
  
     
 
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
    public void changementMdp(ActionEvent event ) throws ClassNotFoundException{
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

        //System.out.println("recupération mdp"+j.toString());
        //recupérer pseudo 



        //recuperer mdp 
        mdp= saisieNvMDP.getText(); 
        if( j.getMdp()!=mdp){
            
           c.setPseudoBDD(j.getPseudo(),j.getMdp(),mdp);
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

        //System.out.println("recupération mdp"+j.toString());
        //recupérer pseudo 


        ps= saisieNvPs.getText(); 
        
        if( j.getPseudo()!=ps){
            System.out.println("passage boucle"+ps);
            c.setPseudoBDD(j.getPseudo(),j.getMdp(),ps);
           j.setPseudo(ps);

        }



    }
}
