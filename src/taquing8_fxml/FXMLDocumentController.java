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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.Background;


/**
 *
 * @author hazal
 */


public class FXMLDocumentController {
    //Initialisation des chrono,grille et joueur
    Chrono chronos = new Chrono();    
    Grille g = new Grille(4);
    Joueur j1 = new Joueur();
  /**
   * bouton gauche  
   */  

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
    
    @FXML
    private Label label;
   /**
   * grille d'affichage   
   */  
    @FXML
    private GridPane grille;
    
    @FXML
     /**
   * barre   
   */  
    private MenuItem console;
    @FXML
    private Button profil3;

    @FXML 
    private Label deplacementLabel; 
     @FXML
    private MenuButton optionDeJeu;
    
    //Image à mettre dans l'interface pour jouer
    Class<?> clazz = FXMLDocumentController.class;
 
    InputStream input = clazz.getResourceAsStream("carte_electronique.png");
 
    Image image = new Image(input);

    // à ajouter dans la fenetre choix je pense
//    //clic sur fichier "jouer dans la console"
//    @FXML
//    void playConsole(ActionEvent event) {
//        start.setDisable(true);
//        MainsansGUI m = new MainsansGUI();
//        m.sansGUI();
//    }

     /**
      * clic sur fichier "jouer dans la console"
      * @param event action 
      */
  
    @FXML
    void playConsole(ActionEvent event) {
        start.setDisable(true);
        MainsansGUI m = new MainsansGUI();
        m.sansGUI();
    }

     void handleButtonAction(ActionEvent event) {

    }

    
   
     /**
      * creation multifenetre qui permet de se connecter 
      * @param event clique sur le bouton 
      * @throws IOException 
      */
    @FXML
    public void changementPage ( ActionEvent event ) throws IOException{
        Parent deuxiemeFenetre  = FXMLLoader.load(getClass().getResource("DeuxiemeFenetre.fxml")); //creation de fenêtre 2 qui va etre relier à celle ci 
         Scene deuxiemeF = new Scene (deuxiemeFenetre); //creation scene deuxieme fenetre 
         Stage fenetre = (Stage) ((Node)event.getSource()).getScene().getWindow(); // creation stage fenetre  
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
        for(int i =0; i<g.getTaille(); i++){
            for (int j = 0;j<g.getTaille();j++){
                if (g.ensCase[j][i].getVide()){
                    Pane p = new Pane();
                    p.setStyle("-fx-background-color:pink");
                    grid.add(p, i, j);
                }
                else {
                    String value = String.valueOf(g.ensCase[j][i].getBloc().getNumBloc());
                    System.out.println(value);
                    Label label = new Label(value);
                    Pane p = new Pane();
                    p.getChildren().add(new ImageView (image));  //On met l'image
                    p.getChildren().addAll(label);
                    //p.setStyle("-fx-background-color:black");
                    grid.add(p, i, j);
                }
                }
            }
        //Affichage de la grille
        grid.setHgap(3.0);
        grid.setVgap(3.0);
        }
             
    /**
     * Ajout de l'image à l'interface graphique 
     * @param numBloc int, numéro du bloc à ajouter
     * @return Image retourne l'image
     */    
    private Image ajoutImagePane (int numBloc) { 
        Image img = null;
    
        return img;
    }
    
    /**
     * Effectue un déplacement sur la grille dans l'interface selon direction
     * @param direction char, lettre du déplacement
     * @param j Joueur, joueur effectuant le déplacement
     */
    private void deplacementFXML(char direction, Joueur j){
        g.deplacement(direction, j);        
        deplacementLabel.setText(Integer.toString(j1.getNbDeplacement()-2));
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
        
        grilleToGrid(g,grille); 
        chronos.setFini(false);
        grille.getChildren().remove(g.taille*g.taille);
        //deux mouvement consécutif pour eviter une erreur d'affichage
        g.deplacement("d".charAt(0), j1);
        g.deplacement("q".charAt(0), j1);

        grille.getChildren().clear();
        grilleToGrid(g, grille);
        Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
        @Override
        public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
            while (chronos.isFini()==false){
                if(g.verifVictoire()==true){
                    chronos.setFini(true);
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
     * clique sur le bouton z qui permet de monter 
     * @param event action 
     */
  
/**
     * saisie clavier sur le touche z qui permet de monter 
     * @param event action 
     */
    void Zpress(ActionEvent event) {

    }
/**
     * clique sur le bouton q qui permet d'aller à gauche  
     * @param event action 
     */
   
/**
     * saisie clavier sur le touche q qui permet d'aller à gauche  
     * @param event action 
     */
    void Qpress(ActionEvent event) {

    }
    /**
     * clique  sur le bouton d qui permet de monter 
     * @param event action 
     */
    
/**
     * saisie clavier sur le touche d qui permet d'aller à droite 
     * @param event action 
     */
    void Dpress(ActionEvent event) {
        
    }
/**
     * clique sur le bouton s qui permet de descendre 
     * @param event action 
     */
    
    /**
     * saisie clavier sur le touche s qui permet de descendre 
     * @param event action 
     */

    void Spress(ActionEvent event) {
        

    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
/**
     * saisie clavier sur le touche z qui permet de monter 
     * @param event action du clavier 
     */
    @FXML
    private void Zpress(KeyEvent event) {
        if (event.getCode()==KeyCode.Z){
           deplacementFXML("z".charAt(0), j1);
        }
        if (event.getCode()==KeyCode.Q){
           deplacementFXML("q".charAt(0), j1);
        }
        if (event.getCode()==KeyCode.S){
           deplacementFXML("s".charAt(0), j1);
        }
        if (event.getCode()==KeyCode.D){
           deplacementFXML("d".charAt(0), j1);
        }
    }
/**
     * saisie clavier sur le touche q qui permet d'aller à gauche  
     * @param event action du clavier 
     */
    @FXML
    private void Qpress(KeyEvent event) {        
    }
/**
     * saisie clavier sur le touche s qui permet de descendre  
     * @param event action du clavier 
     */
    @FXML
    private void Spress(KeyEvent event) {        
    }
/**
     * saisie clavier sur le touche d qui permet d'aller à droite 
     * @param event action du clavier 
     */
    @FXML
    private void Dpress(KeyEvent event) {
    } 
    
    /**
     * Déplacement avec les boutons de l'interface
     * @param event 
     */
    @FXML
    void Zclic(ActionEvent event) {        
        deplacementFXML("z".charAt(0), j1);;
    }
 @FXML 
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
    void SaveAndQuit(ActionEvent event) {
        chronos.setFini(true);       
        //enregistrement serialisation
        GrilleSer ser = new GrilleSer();
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
    }
    /**
     * Clic sur Nouvelle partie, arrete la partie en cours et en demarre une nouvelle
     * @param event 
     */
    @FXML
    void QuitAndNew(ActionEvent event) {
        chronos.setFini(true);
        start.setVisible(true);
        chronos.setCmpt(0);
        g=new Grille(4);
    }
    
   
}

