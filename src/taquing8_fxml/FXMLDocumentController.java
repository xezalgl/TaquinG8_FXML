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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author hazal
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
     @FXML 
     private GridPane grille; //grille 
    @FXML
    private Button button;
    @FXML
    private Button butto;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.out.println("You clicked me!");
        label.setText("eogrd!");
    }
    
}
