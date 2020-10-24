/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hazal
 */
public class TaquinG8_FXML extends Application {
    //Attributs
    Grille g  = new Grille (4);
          
    //Constructeur
    public TaquinG8_FXML() {
        System.out.println(g);
        Scanner sc = new Scanner(System.in);  //Initialisation du scanner
        while(!g.verifVictoire()){   
            System.out.println("Saisissez une lettre"
                    + "\n d = droite"
                    + "\n s = bas"
                    + "\n q = gauche"
                    + "\n z = haut");
            char d = sc.next().charAt(0);
            System.out.println("Vous avez saisie la direction : " + d); //Saisie par le joueur
            //Test si la saisie est valide
            g.deplacement(d); 
            g.toString(); 
            System.out.println(g); 
        }
    }
            
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
       
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaquinG8_FXML jeu = new TaquinG8_FXML();
        
        //launch(args);
    }
    
}
