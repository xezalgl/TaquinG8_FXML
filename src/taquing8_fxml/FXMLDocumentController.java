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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.Background;
import java.lang.Cloneable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *
 * @author groupe 8
 */

public class FXMLDocumentController implements Parametres {

    //Initialisation des chrono,grille et joueur
    Chrono chronos = new Chrono(); 
    //deserilize la taille selsctionné par le joueur
    //Grille g = new Grille(4); //Pour faire des tests
    Grille g = new Grille(choixTailleGrille()); //Création de la grille avec pour taille celle choisie dans l'interface
    Joueur j1 = new Joueur();
    int size = g.getTaille();

  @FXML
    private Button Q;
    /**
   * bouton bas  
   */  
    @FXML
    private Button S;
   /**
   * bouton droit   
   */  
    @FXML
    private Button D;
 /**
   * bouton affichage du chrono  
   */  
    @FXML
    private Label chronoAffiche;
 /**
   * bouton debute la partie 
   */  
    @FXML
    private Button start;
 /**
   * bouton haut  
   */  
    @FXML
    private Button Z;
    
   /**
   * grille d'affichage   
   */  
    @FXML
    private GridPane grille;
    

    @FXML 
    private Label deplacementLabel; 
     @FXML
    private MenuButton optionDeJeu;
    
    
    @FXML
    private ComboBox selectTheme;
    
    private ComboBox selectTaille;
    
    //Image à mettre dans l'interface pour jouer
    Class<?> clazz = FXMLDocumentController.class;

    //InputStream input;
    InputStream input = clazz.getResourceAsStream(urlCerisier); //Pour test
    //Image image;
    Image image = new Image(input); //Pour test
    
    private CheckBox checkAffNumero;    //Pour que l'utilisateur affiche ou non les numéro des cases
    
    private boolean affNumero; //TRUE si checkAffNumero est coché
        
    //Thème Graphique
    private Color colFondCase;  //Couleur de fond des cases
    private Color colCaseVide;  //Couleur pour la case vide
    private Color colTexte;     //Couleur des labels
    
    //clic sur fichier "jouer dans la console"
    
    //Onglet Aide
    @FXML
    private Label chronoAffiche11;
    @FXML
    private Label chronoAffiche111;
    @FXML
    private GridPane grille1;
    @FXML
    private Button D1;
    @FXML
    private Button S1;
    @FXML
    private Button Q1;
    @FXML
    private Button Z1;
    @FXML
    private Button start1;
    @FXML
    private Label label;
    @FXML
    private MenuItem console;


    //Getteurs et setteurs
    
    /**
     * Renvoie le booléen pour savoir si le joueur veut afficher les numéros des cases
     * @return le booléen d'affichage des numéros des cases
     */
    public boolean getAffNumero () {
        return this.affNumero;
    }
    /**
     * Renvoie la couleur correspondant à l'élément souhaité
     * @param objet le type d'élément pour lequel on veut connaitre la couleur
     * @return la couleur correspondant à l'élément
     */
    public Color getCol (String objet) {
        if (objet == "FondCase"){
            return this.colFondCase;
        }
        else if (objet == "CaseVide"){
            return this.colCaseVide;
        }
        else if (objet == "Texte"){
            return this.colTexte;
        }
        else {
            return null;
        }
    }
    
    
    /**
     * Changement de la valeur du booléen pour l'affichage des numéros des cases
     * @param newValue La nouvelle valeur a attribué au booléen
     */
    public void setAffNumero (boolean newValue){
        this.affNumero = newValue;
    }
    
    /**
     * Modifie la couleur d'un élément 
     * @param objet l'élément pour lequel on veut changer la couleur
     * @param newColor la nouvelle couleur a attribué à l'élément
     */
    public void setCol (String objet, Color newColor){
        if (objet == "FondCase"){
            this.colFondCase = newColor;
        }
        else if (objet == "CaseVide"){
            this.colCaseVide = newColor;
        }
        else if (objet == "Texte"){
            this.colTexte = newColor;
        }
    }
    
    /**
     * Modifie le booléen d'affichage des numéro des cases en fonction de la checkBox
     */
    public void setAffNumero () {
        this.affNumero = checkAffNumero.isSelected();
    }
    
    
    
     /**
      * clic sur fichier "jouer dans la console"
      * @param event action 
      */

    @FXML
    public void playConsole(ActionEvent event) {
        start.setDisable(true);
        MainsansGUI m = new MainsansGUI();
        m.sansGUI();
    }
    
    @FXML
    /**
     * rend visible/invible les boutons de navigation
     * @param visible boolean true si bouton visible, else sinon
     */
    private void navigationBouton(boolean visible){
            Z.setVisible(visible);
            Q.setVisible(visible);
            S.setVisible(visible);
            D.setVisible(visible);
    }
    @FXML
    public void handleButtonAction(ActionEvent event) {

    }

    
   
     /**
      * creation multifenetre qui permet de se connecter 
      * @param event clique sur le bouton 
      * @throws IOException 
      */
    public void changementPage ( ActionEvent event ) throws IOException{
        Parent deuxiemeFenetre  = FXMLLoader.load(getClass().getResource("DeuxiemeFenetre.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeF = new Scene (deuxiemeFenetre); //creation scene deuxieme fenetre 
         Stage fenetre = (Stage) optionDeJeu.getScene().getWindow(); // creation stage fenetre  
         fenetre.setScene(deuxiemeF); //on affiche la deuxieme fenetre 
         fenetre.show(); //ouverture de la
    }
    /**
     * Creation multifenetre qui permet de s'inscrire 
     * @param event
     * @throws IOException 
     */
    public void passageInscription (ActionEvent event) throws IOException{
         Parent deuxieme  = FXMLLoader.load(getClass().getResource("Inscription.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeFe = new Scene (deuxieme); //creation scene deuxieme fenetre 
         Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         window.setScene(deuxiemeFe); //on affiche la deuxieme fenetre 
         window.show();
    }
    
    /**
     * retourne l'index dans le gridPane de la cese vide
     * @param g la grille 
     * @return la grille 
     */
   
    private int trouveCaseVideGrid(Grille g){
       Case caseVide = g.trouveCaseVide();
        int xVide = caseVide.getCoordx();
        int yVide = caseVide.getCoordy();
        return xVide+1+(yVide*g.taille);
    }
    
    /**
     * affiche la grille dans le GridPane de l'interface
     * @param g grille 
     * @param grid grille 
     */
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
                    //Récupération des coordonnées du bloc pour insérer l'image
                    int coordxBloc = g.ensCase[j][i].getCoordx();
                    int coordyBloc = g.ensCase[j][i].getCoordy();
                    
                    int numBlocTemp = 0;    //Stockage du numéro de la case sous forme de int
                    
                    Pane p = new Pane(); //Création d'un pane
                    p.getChildren().add(ajoutImagePane(numBlocTemp, coordxBloc, coordyBloc, grid, g)); //Ajout de la portion de l'image correspondance au bloc sur le pane
                    
                    //Si le joueur veut afficher les numéros des cases
                    if (getAffNumero()) {
                        String value = String.valueOf(g.ensCase[j][i].getBloc().getNumBloc()); //Récupération du numéro du bloc
                        Label label = new Label(value); //Récupération du numéro du bloc dans un label
                        label.setTextFill(Color.web("#ffffff"));  //Changement de style du label
                        p.getChildren().addAll(label); //Ajout du texte sur le pane
                        numBlocTemp = Integer.parseInt(value);  //Numéro du bloc en int
                    }
                    
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
             
    /**
     * Ajout de l'image à l'interface graphique 
     * @param numBloc int, numéro du bloc à ajouter
     * @return Image retourne l'image
     */    
    private ImageView ajoutImagePane (int numBloc) { 
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
                //img = ajoutImageTaille3(img, numBloc, hauteurCase, largCase);
                break;
            case 4 :
                //La grille est en taille 4
                img = ajoutImageTaille4(img, numBloc, hauteurCase, largCase);
                break;
            case 5 :
                //La grille est en taille 5
                //img = ajoutImageTaille5(img, numBloc, hauteurCase, largCase);
                break;
        }
             
        //Redimensionne la portion de l'image à la taille de la case
        img.setFitWidth(largCase);
        img.setFitHeight(hauteurCase);
                
        //Retourne la portion de l'image du bloc
        return img;
    }
    
    @FXML
    /**
     * Effectue un déplacement sur la grille dans l'interface selon direction
     * @param direction char, lettre du déplacement
     * @param j Joueur, joueur effectuant le déplacement
     */
    private void deplacementFXML(char direction, Joueur j){
        g.deplacement(direction, j);        
        deplacementLabel.setText(Integer.toString(j.getNbDeplacement()-2));
        grille.getChildren().clear();
        grilleToGrid(g, grille);
    }

    
    /**
     * Clic sur Start
     * @param event
     * @throws InterruptedException 
     */
    @FXML 
    void run(ActionEvent event) throws InterruptedException {
        start.setVisible(false);
        initComboBoxTheme();    //Initialisation de la combobox A METTRE AILLEURS
        grille.setPrefSize(476, 476); //Redimensionnement de la grille
        grille.setGridLinesVisible(false); //Visibilité des ligne de la grille
        navigationBouton(true);
        grilleToGrid(g,grille); 
        chronos.setFini(false);
        //deux mouvement consécutif pour eviter une erreur d'affichage
        deplacementLabel.setText(Integer.toString(this.j1.getNbDeplacement()));
        g.deplacement("d".charAt(0), this.j1);
        g.deplacement("q".charAt(0), this.j1);

        grille.getChildren().clear();
        grilleToGrid(g, grille);
        Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
        @Override
        public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
            while (chronos.isFini()==false){
                if(g.verifVictoire()==true){
                    chronos.setFini(true);
                    //c.creationPartie(j1.getPseudo,j1.getMdp,j1.getScore,int mode de jeu)
                }
                Platform.runLater(new Runnable() { // classe anonyme
                    @Override
                    public void run(){ 
                            chronos.setCmpt(chronos.getCmpt()+1);
                            chronoAffiche.setText(Integer.toString(chronos.getCmpt())+" s.");
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
    protected int choixTailleGrille () {
        String tailleString = selectTaille.getSelectionModel().getSelectedItem().toString(); //Récupération de l'item sélectionné
        System.out.println(tailleString);  //TTTEEESSSTTT Pour savoir si ça prend le bon item
        int tailleChoisie = Integer.parseInt(tailleString); //Passage sous forme de int
        return tailleChoisie;
    }
    
    /**
     * Initialise la combobox qui permet de choisir le thème graphique
     */
    public void initComboBoxTheme () {
        selectTheme.getItems().addAll("Thème par défaut", "Cerisier", "Electronique", "Dragons", "Une image de mon pc");
        selectTheme.getSelectionModel().select("Thème par défaut");
    }
    
    /**
     * Initialise la combobox qui permet de choisir la taille de la grille
     */
    public void initComboBoxTaille () {
        selectTheme.getItems().addAll("3x3", "4x4", "5x5");
        selectTheme.getSelectionModel().select("4x4");
    }
    
    /**
     * Permet de définir des paramètres en fonction du thème choisi
     */
    @FXML
    protected void choixTheme () {
        //Déclaration des couleurs
        Color colFond = null;
        Color colTexte = null;
        Color colVide = null;
        //Récupération de l'item choisi
        String theme = selectTheme.getSelectionModel().getSelectedItem().toString();
        System.out.println(theme);  //test
        //Modification des paramètres graphiques 'couleurs et nom de l'image)
        switch (theme){
            case "Thème par défaut":
                colFond = colDefaut;
                colTexte = colTextDefaut;
                colVide = colVideDefaut;
                this.input = clazz.getResourceAsStream(urlDefaut);
                break;
            case "Cerisier" : 
                colFond = colCerisier;
                colTexte = colTextCerisier;
                colVide = colVideCerisier;
                this.input = clazz.getResourceAsStream(urlCerisier);
                break;
            case "Electronique" :
                colFond = colElectro;
                colTexte = colTextElectro;
                colVide = colVideElectro;
                this.input = clazz.getResourceAsStream(urlElectro);
                break;
            case "Dragons" :
                colFond = colDragon;
                colTexte = colTextDragon;
                colVide = colVideDragon;
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
        
        setCol("FondCase", colFond); //Changement de la couleur de fond du jeu
        setCol("Texte", colTexte);  //Changement de la couleur de texte
        setCol("CaseVide", colVide);    //Changement de la couleur de fond de la case vide
        
        //Changement de l'image en fonction de l'image choisie
        this.image = new Image(input);
    }
      

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
/**
     * saisie clavier sur le touche z qui permet de monter 
     * @param event action du clavier 
     */
    @FXML
    void touche(KeyEvent event) {
        if (event.getCode()==KeyCode.D){
           deplacementFXML("d".charAt(0), j1);
        }if (event.getCode()==KeyCode.S){
           deplacementFXML("s".charAt(0), j1);
        }if (event.getCode()==KeyCode.Q){
           deplacementFXML("q".charAt(0), j1);
        }if (event.getCode()== KeyCode.Z){
           deplacementFXML("z".charAt(0), j1);
        }
    } 
    
    /**
     * Déplacement avec les boutons de l'interface
     * @param event 
     */
    @FXML
    void Zclic(ActionEvent event) {        
        deplacementFXML("z".charAt(0), j1);;
    }
    
    public void passageProfi (ActionEvent event ) throws IOException{
        Parent profil  = FXMLLoader.load(getClass().getResource("Profil.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene prof = new Scene (profil); //creation scene deuxieme fenetre 
         Stage profilP = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
         profilP.setScene(prof); //on affiche la deuxieme fenetre 
         profilP.show();

    }
    
    
     @FXML
    void Qclic(ActionEvent event) {
        deplacementFXML("q".charAt(0), j1);
    }
    
    @FXML
    void Dclic(ActionEvent event) {
        deplacementFXML("d".charAt(0), j1);
    }

    @FXML
    void Sclic(ActionEvent event) {
        deplacementFXML("s".charAt(0), j1);  
    }
    

    /**
     * Clic sur Pause, arrete le chronos et enregistre la grille dans un fichier
     * @param event 
     */
    @FXML
    void SaveAndQuit(ActionEvent event) throws IOException, ClassNotFoundException {
        chronos.setFini(true);       
        //enregistrement serialisation
//        GrilleSer ser = new GrilleSer();
//        ser.SauverGrille(g);
        Ser ser = new Ser();
        ser.SauverJeu(g,j1);

        changementPage(event);
        //retour fenetre menu
        
    }

    /**
     * Clic sur Quitter, arrete le chronos et ferme la fenêtre du jeu
     * @param event
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @FXML
    void Quit(ActionEvent event) throws IOException, ClassNotFoundException {
        chronos.setFini(true);       
        //fermeture de la fenetre
        changementPage(event);

//        Deser deser = new Deser();         
//        Joueur j2 = deser.ChargerJoueur();
        
    }
    /**
     * Clic sur Nouvelle partie, arrete la partie en cours et en demarre une nouvelle
     * @param event 
     */
    @FXML
    void QuitAndNew(ActionEvent event) {
        chronos.setFini(true);
        start.setVisible(true);
        grille.getChildren().clear();
        chronos.setCmpt(0);
        chronoAffiche.setText("0");
        deplacementLabel.setText("0");
        navigationBouton(false);
        j1.initNbDeplacement();
        g=new Grille(size);
        
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

