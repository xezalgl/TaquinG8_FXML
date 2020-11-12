/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
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
import javafx.scene.Node;
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

    //clic sur fichier "jouer dans la console"
    @FXML
    void playConsole(ActionEvent event) {
        start.setDisable(true);
        MainsansGUI m = new MainsansGUI();
        m.sansGUI();
    }

     @FXML
    void handleButtonAction(ActionEvent event) {

    }
    
    //retourne l'index dans le gridPane de la cese vide
    private int trouveCaseVideGrid(Grille g){
       Case caseVide = g.trouveCaseVide();
        int xVide = caseVide.getCoordx();
        int yVide = caseVide.getCoordy();
        return xVide+1+(yVide*g.taille);
    }
    
    //affiche la grille dans le GrrdPane de l'interface
    private void grilleToGrid(Grille g, GridPane grid){
        for(int i =0; i<g.getTaille(); i++){
            for (int j = 0;j<g.getTaille();j++){                
                String value = String.valueOf(g.ensCase[j][i].getBloc().getNumBloc());
                Label label = new Label(value);
                grid.add(label, i, j);
            }
        }
        grid.getChildren().remove(trouveCaseVideGrid(g));
        
        
              
    }
    
    //clic sur play
    @FXML
    void run(ActionEvent event) {
        start.setDisable(true);
        Grille g = new Grille(4);
        grilleToGrid(g,grille);        
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

