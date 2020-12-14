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
import javafx.geometry.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.Background;
import java.lang.Cloneable;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *
 * @author hazal
 */

public class FXMLDocumentController implements Parametres {
    
    Chrono chronos = new Chrono();    
    //Grille g = new Grille(choixTailleGrille()); //Création de la grille avec pour taille celle choisie dans l'interface
    Grille g = new Grille (4); //Pour choisir manuellement la taille de la grille pour des tests
    Joueur j1 = new Joueur();
    
  @FXML
    private Button Q;

    @FXML
    private Button S;
    
    @FXML
    private Button D;

    @FXML
    private Label chronoAffiche;

    @FXML
    private Button start;

    @FXML
    private Button Z;

    @FXML
    private Label label;

    @FXML
    private GridPane grille;
    @FXML
    private MenuItem console;
    
    @FXML
    private ComboBox selectTheme;
    
    @FXML
    private ComboBox selectTaille;
    
    //Image à mettre dans l'interface pour jouer
    Class<?> clazz = FXMLDocumentController.class;
    //InputStream input;
    InputStream input = clazz.getResourceAsStream(urlCerisier); //Pour test
    //Image image;
    Image image = new Image(input); //Pour test
    
    
    //clic sur fichier "jouer dans la console"
    @FXML
    void playConsole(ActionEvent event) {
        start.setDisable(true);
        MainsansGUI m = new MainsansGUI();
        m.sansGUI();
    }

     void handleButtonAction(ActionEvent event) {

    }
    
    //creation multifenetre 
    public void changementPage ( ActionEvent event ) throws IOException{
        Parent deuxiemeFenetre  = FXMLLoader.load(getClass().getResource("DeuxiemeFenetre.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeF = new Scene (deuxiemeFenetre); //creation scene deuxieme fenetre 
         Stage fenetre = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         fenetre.setScene(deuxiemeF); //on affiche la deuxieme fenetre 
         fenetre.show(); //ouverture de la
    }
    public void passageInscription (ActionEvent event) throws IOException{
         Parent deuxieme  = FXMLLoader.load(getClass().getResource("Inscription.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeFe = new Scene (deuxieme); //creation scene deuxieme fenetre 
         Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         window.setScene(deuxiemeFe); //on affiche la deuxieme fenetre 
         window.show();
    }
    
    
    
    //affiche la grille dans le GrrdPane de l'interface
    private void grilleToGrid(Grille g, GridPane grid){
        //parcours de la grille
        for(int i =0; i<g.getTaille(); i++){
            for (int j = 0;j<g.getTaille();j++){
                //Si on est sur une case vide
                if (g.ensCase[j][i].getVide()){
                    Pane p = new Pane();   //Création d'un nouveau pane
                    p.setStyle("-fx-background-color:pink");    //Couleur de fond
                    grid.add(p, i, j);  //Ajout du pane à gridpane
                }
                //Si la case n'est pas vide
                else {
                    String value = String.valueOf(g.ensCase[j][i].getBloc().getNumBloc()); //Récupération du numéro du bloc
                    //Récupération des coordonnées du bloc pour insérer l'image
                    int coordxBloc = g.ensCase[j][i].getCoordx();
                    int coordyBloc = g.ensCase[j][i].getCoordy();
                    Label label = new Label(value); //Récupération du numéro du bloc dans un label
                    label.setTextFill(Color.web("#ffffff"));  //Changement de style du label
                    Pane p = new Pane(); //Création d'un pane
                    int numBlocTemp = Integer.parseInt(value);  //Numéro du bloc en int
                    p.getChildren().add(ajoutImagePane(numBlocTemp, coordxBloc, coordyBloc, grid, g)); //Ajout de la portion de l'image correspondance au bloc sur le pane
                    p.getChildren().addAll(label); //Ajout du texte sur le pane
                    //p.setStyle("-fx-background-color:black"); //couleur de fond si pas d'image
                    grid.add(p, i, j); //Ajout du pane au grid pane
                }
            }
        }
        //Affichage de la grille avec des espaces entre les colonnes et lignes
        grid.setHgap(3.0);
        grid.setVgap(3.0);
    }
      
    /**
     * Ajout d'une portion de l'image au bloc souhaité
     * @param numBloc   le numéro du bloc sur lequel placer un morceau de l'image
     * @param grid la grille dans l'interface graphique
     * @param g la grille du jeu
     * @return la portion de l'image correspondant au bloc
     */
    private ImageView ajoutImagePane (int numBloc, int x, int y, GridPane grid, Grille g) { 
        //On récupère l'image chargé sous forme de ImageView
        ImageView img = new ImageView (image);
        //Redimensionnement de l'image à la taille de la grille
        
        //Récupréation des dimensions d'une case
        float hauteurCase = (float) 476 / (float) g.getTaille();  //Récupération de la dimension d'une case
        float largCase = (float) 476 / (float) g.getTaille();  //Récupération de la dimension d'une case

        //Appel d'une fonction pour avoir la portion de l'image en fonction de la taille de la grille
        switch (g.taille){
            case 3 : 
                //La grille est en taille 3
                img = ajoutImageTaille3(img, numBloc, hauteurCase, largCase);
                break;
            case 4 :
                //La grille est en taille 4
                img = ajoutImageTaille4(img, numBloc, hauteurCase, largCase);
                break;
            case 5 :
                //La grille est en taille 5
                img = ajoutImageTaille5(img, numBloc, hauteurCase, largCase);
                break;
        }
             
        //Redimensionne la portion de l'image à la taille de la case
        img.setFitWidth(largCase);
        img.setFitHeight(hauteurCase);
                
        //Retourne la portion de l'image du bloc
        return img;
    }
    
    private ImageView ajoutImageTaille4(ImageView img, int numBloc, float hauteurCase, float largCase){
        //Déclaration de la découpe
        Rectangle2D imgDecoupe = null;
        //En fonction du numéro du bloc
        switch (numBloc) {
            case 1 : 
                //Découpage de la 2e zone de l'image
                imgDecoupe = new Rectangle2D(0, 0, largCase, hauteurCase);
                break;
            case 2 : 
                //Découpage de la 2e zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, 0, largCase, hauteurCase);
                break;
            case 3: 
                //Découpage de la 3e zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, 0, largCase, hauteurCase);
                break;
            case 4:
                //Découpage de la 4 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, 0, largCase, hauteurCase);
                break;
            case 5:
                //Découpage de la 5 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase, largCase, hauteurCase);
                break;
            case 6:
                //Découpage de la 6 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, hauteurCase, largCase, hauteurCase);
                break;
            case 7:
                //Découpage de la 7 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, hauteurCase, largCase, hauteurCase);
                break;
            case 8:
                //Découpage de la 8 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, hauteurCase, largCase, hauteurCase);
                break;
            case 9:
                //Découpage de la 9 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase*2, largCase, hauteurCase);
                break;
            case 10:
                //Découpage de la 10 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, hauteurCase*2, largCase, hauteurCase);
                break;
            case 11:
                //Découpage de la 11 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, hauteurCase*2, largCase, hauteurCase);
                break;
            case 12:
                //Découpage de la 12 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, hauteurCase*2, largCase, hauteurCase);
                break;
            case 13:
                //Découpage de la 13 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase*3, largCase, hauteurCase);
                break;
            case 14:
                //Découpage de la 14 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, hauteurCase*3, largCase, hauteurCase);
                break;
            case 15:
                //Découpage de la 15 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, hauteurCase*3, largCase, hauteurCase);
                break;
            
        }
        
        img.setViewport(imgDecoupe);
        
        return img;
    }
    
    private ImageView ajoutImageTaille3(ImageView img, int numBloc, float hauteurCase, float largCase){
        //Déclaration de la découpe
        Rectangle2D imgDecoupe = null;
        //En fonction du numéro du bloc
        switch (numBloc) {
            case 1 : 
                //Découpage de la 2e zone de l'image
                imgDecoupe = new Rectangle2D(0, 0, largCase, hauteurCase);
                break;
            case 2 : 
                //Découpage de la 2e zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, 0, largCase, hauteurCase);
                break;
            case 3: 
                //Découpage de la 3e zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, 0, largCase, hauteurCase);
                break;
            case 4:
                //Découpage de la 4 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase, largCase, hauteurCase);
                break;
            case 5:
                //Découpage de la 5 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, hauteurCase, largCase, hauteurCase);
                break;
            case 6:
                //Découpage de la 6 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, hauteurCase, largCase, hauteurCase);
                break;
            case 7:
                //Découpage de la 7 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase*2, largCase, hauteurCase);
                break;
            case 8:
                //Découpage de la 8 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, hauteurCase*2, largCase, hauteurCase);
                break;
            
        }
        
        img.setViewport(imgDecoupe);
        
        return img;
    }
    
    private ImageView ajoutImageTaille5(ImageView img, int numBloc, float hauteurCase, float largCase){
        //Déclaration de la découpe
        Rectangle2D imgDecoupe = null;
        //En fonction du numéro du bloc
        switch (numBloc) {
            case 1 : 
                //Découpage de la 2e zone de l'image
                imgDecoupe = new Rectangle2D(0, 0, largCase, hauteurCase);
                break;
            case 2 : 
                //Découpage de la 2e zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, 0, largCase, hauteurCase);
                break;
            case 3: 
                //Découpage de la 3e zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, 0, largCase, hauteurCase);
                break;
            case 4:
                //Découpage de la 4 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, 0, largCase, hauteurCase);
                break;
            case 5:
                //Découpage de la 5 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*4, 0, largCase, hauteurCase);
                break;
            case 6:
                //Découpage de la 6 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase, largCase, hauteurCase);
                break;
            case 7:
                //Découpage de la 7 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, hauteurCase, largCase, hauteurCase);
                break;
            case 8:
                //Découpage de la 8 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, hauteurCase, largCase, hauteurCase);
                break;
            case 9:
                //Découpage de la 9 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, hauteurCase, largCase, hauteurCase);
                break;
            case 10:
                //Découpage de la 10 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*4, hauteurCase, largCase, hauteurCase);
                break;
            case 11:
                //Découpage de la 6 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase*2, largCase, hauteurCase);
                break;
            case 12:
                //Découpage de la 7 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase, hauteurCase*2, largCase, hauteurCase);
                break;
            case 13:
                //Découpage de la 8 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, hauteurCase*2, largCase, hauteurCase);
                break;
            case 14:
                //Découpage de la 9 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, hauteurCase*2, largCase, hauteurCase);
                break;
            case 15:
                //Découpage de la 10 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*4, hauteurCase*2, largCase, hauteurCase);
                break;
            case 16:
                //Découpage de la 6 zone de l'image
                imgDecoupe = new Rectangle2D(0, hauteurCase*3, largCase, hauteurCase);
                break;
            case 17:
                //Découpage de la 7 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, hauteurCase*3, largCase, hauteurCase);
                break;
            case 18:
                //Découpage de la 8 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*2, hauteurCase*3, largCase, hauteurCase);
                break;
            case 19:
                //Découpage de la 9 zone de l'image
                imgDecoupe = new Rectangle2D(hauteurCase*3, hauteurCase*3, largCase, hauteurCase);
                break;
        }
        
        img.setViewport(imgDecoupe);
        
        return img;
    }
    
    //clic sur play
    @FXML
    void run(ActionEvent event) {
        start.setDisable(true);
        
        initComboBoxTheme();    //Initialisation de la combobox A METTRE AILLEURS
        
        //Redimensionnement de la grille
        grille.setPrefSize(476, 476);
        
        //Visibilité de la grille
        grille.setGridLinesVisible(false);
        
        grilleToGrid(g,grille);    //remplissage de la grille
        
        
        
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
    
    /**
     * Analyse du choix de la taille de la grille
     * @return la taille choisie sous forme de int
     */
    @FXML
    protected int choixTailleGrille () {
        String tailleString = selectTaille.getSelectionModel().getSelectedItem().toString(); //Récupération de l'item sélectionné
        System.out.println(tailleString);  //TTTEEESSSTTT Pour savoir si ça prend le bon item
        int tailleChoisie = Integer.parseInt(tailleString); //Passage sous forme de int
        return tailleChoisie;
    }
    
    /**
     * Initialise la combobox qui permet de choisir le thème graphique
     */
    @FXML
    public void initComboBoxTheme () {
        selectTheme.getItems().addAll("Thème par défaut", "Cerisier", "Electronique", "Dragons", "Une image de mon pc");
        selectTheme.getSelectionModel().select("Thème par défaut");
    }
    
    /**
     * Initialise la combobox qui permet de choisir la taille de la grille
     */
    @FXML
    public void initComboBoxTaille () {
        selectTheme.getItems().addAll("3x3", "4x4", "5x5");
        selectTheme.getSelectionModel().select("4x4");
    }
    
    /**
     * Permet de définir des paramètres en fonction du thème choisi
     */
    @FXML
    protected void choixTheme () {
        Color colFond;
        String theme = selectTheme.getSelectionModel().getSelectedItem().toString();
        System.out.println(theme);  //test
        switch (theme){
            case "Cerisier" : 
                colFond = colCerisier;
                this.input = clazz.getResourceAsStream(urlCerisier);
                break;
            case "Electronique" :
                colFond = colElectro;
                this.input = clazz.getResourceAsStream(urlElectro);
                break;
            case "Dragons" :
                colFond = colDragon;
                this.input = clazz.getResourceAsStream(urlDragon);
                break;
            case "Une image de mon pc" :
            /*
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choisir une image pour jouer au Taquin");
                fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
                File selectedFile = fileChooser.showOpenDialog(mainStage);
                if (selectedFile != null) {
                   stage.display(selectedFile);
                }
            */
                break;
            default :
                break;
        }
        
        //Changement de l'image en fonction de l'image choisie
        this.image = new Image(input);
    }
    
    @FXML
    void Zclic(ActionEvent event) {
        
        g.deplacement("z".charAt(0), j1);
        System.out.println(g);
        grille.getChildren().clear();
        grilleToGrid(g, grille);
        
    }

    void Zpress(ActionEvent event) {

    }

    @FXML
    void Qclic(ActionEvent event) {
        g.deplacement("q".charAt(0), j1);
        System.out.println(g);
        grille.getChildren().clear();
        grilleToGrid(g, grille);
    }

    void Qpress(ActionEvent event) {

    }
    
    @FXML
    void Dclic(ActionEvent event) {
        g.deplacement("d".charAt(0), j1);
        System.out.println(g);
        grille.getChildren().clear();
        grilleToGrid(g, grille);
    }

    void Dpress(ActionEvent event) {
        
    }

    @FXML
    void Sclic(ActionEvent event) {

        g.deplacement("s".charAt(0), j1);
        System.out.println(g);
        grille.getChildren().clear();
        grilleToGrid(g, grille);
        
    }

    void Spress(ActionEvent event) {
        

    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void Zpress(KeyEvent event) {
    }

    @FXML
    private void Qpress(KeyEvent event) {
    }

    @FXML
    private void Spress(KeyEvent event) {
    }

    @FXML
    private void Dpress(KeyEvent event) {
    }


}

