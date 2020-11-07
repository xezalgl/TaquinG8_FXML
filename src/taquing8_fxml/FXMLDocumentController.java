/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


/**
 *
 * @author hazal
 */

public class FXMLDocumentController {
    
    Chrono chronos = new Chrono();
    
    
    @FXML
    private MenuItem console;

    @FXML
    private Label chronoAffiche;

    @FXML
    private Button start;

    @FXML
    private Label label;

    @FXML
    private GridPane grille;

    @FXML
    void playConsole(ActionEvent event) {
        start.setDisable(true);
        System.out.println("Lancement du programme sans GUI");
            Grille g  = new Grille (4);
            
            System.out.println(g);
            Chrono chronos = new Chrono();
            Timer chrono = new Timer();
            chrono.schedule(new TimerTask(){
                @Override
                public void run(){
                    chronos.setCmpt(chronos.getCmpt()+1);
                }
            },1000,1000);      
        
       
            Scanner sc = new Scanner(System.in);
            //Initialisation du scanner
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
                System.out.println("Chrono :"+chronos.getCmpt()+" secondes.");
                System.out.println(g); 
        }
        
        if(g.verifVictoire()){
            chronos.setFini(true);
            int tempsTot=chronos.getCmpt();
            System.out.println("finish !!!!");
            System.out.println(tempsTot+" secondes");
        }
    }

     @FXML
    void handleButtonAction(ActionEvent event) {

    }
    
    @FXML
    void run(ActionEvent event) {
        Grille g = new Grille(4);
        Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
        @Override
        public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
            while (g.verifVictoire()==false){
                Platform.runLater(new Runnable() { // classe anonyme
                    @Override
                    public void run(){ 
                            chronos.setCmpt(chronos.getCmpt()+1);
                            chronoAffiche.setText(Integer.toString(chronos.getCmpt())+" sec.");
                    }
                }
                );

                Thread.sleep(1000);
            }
            return null;
        } // end call

        };
        Thread th = new Thread(task); // on crée un contrôleur de Thread
        th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
    }

}

