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

    /**
     * Constructeur de la classe TaquinG8_FXML 
     */      
   
    public TaquinG8_FXML() {
        
    }
       /**
        * 
        * @param stage
        * @throws Exception 
        */     
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DeuxiemeFenetre.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.setResizable(false); //Bloque le redimensionnement du stage

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
