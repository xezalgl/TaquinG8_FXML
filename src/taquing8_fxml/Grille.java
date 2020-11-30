/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hazal
 */
public class Grille {
      //Attributs
    int taille=0;
    Case [][] ensCase;    
    //HashSet<Integer> ensNumBloc = new HashSet();
    List <Integer> listeBloc = new ArrayList<Integer>();
  
    
    /**
     * constructeur de la classe grille 
     * @param t 
     */
    public Grille(int t){
             
        this.taille = t;
        this.ensCase = new Case[taille][taille];
        remplissageListeBloc(this.listeBloc);
        int num = 0;
        
        
        //Création du tableau de Cases
        for (int i=0 ; i<this.taille ; i++){
            for (int j=0 ; j<this.taille ; j++){
                //Initialisation du booléen vide pour mettre à vide la case en bas à droite du puzzle
                boolean vide = false;
                if (i==this.taille-1 && j==this.taille-1){
                    vide = true;                    
                }
                
                //Tirage au sort du num du bloc
                if(!listeBloc.isEmpty()){
                    int cmpt = aleatoire(this.listeBloc.size());
                      
                    num= this.listeBloc.get(cmpt);
                    this.listeBloc.remove(cmpt);
                }
                ensCase[i][j] = new Case (i, j, vide, num);
            }
        }
    }
    /**
     * Melange les 
     * @param taille taille de la grille 
     * @return un nombre 
     */
    
    public int aleatoire(int taille){
        int max = taille-1;
        return (int)(Math.random() * (max));
        
    }
    /**
     * accès en écriture à la taille de la grille 
     * @param newTaille nouvelle taille de la grille 
     */
    
    public void setTaille (int newTaille) {
    //Initialisation de l'attribut taille
        //Si la valeur de taille est nulle, cela veut dire qu'on ne l'a jamais initialisé donc il faut le faire
        if (this.taille == 0){
            this.taille = newTaille;
        }
    }
    /**
     * accès en lecture à la taille de la grille 
     * @return la taille de la grille 
     */
    
    public int getTaille () {
        return this.taille;
    }
    
    /**
     * Remplissage de l'ensemble avec tous les numéros des blocs possibles
     * @param List liste contenant les bloc 
    */
    private List remplissageListeBloc(List l) {
        for (int i=0 ; i<this.taille*taille-1; i++){
            int num = this.taille*taille-1 -i;
            
                l.add(num);

        }
        return l;
    }
    /**
     * Permet d'inverser les coordonées entre la case vide et le bloc 
     * @param num_bloc_x recupère la coordonee du bloc en x 
     * @param num_bloc_y recupère la coordonée du bloc en y 
     * @param caseVide   recupère la case vide 
     * @param d 
     */
    private void inversionCoord(int num_bloc_x, int num_bloc_y, Case caseVide, char d){
        //Récupérer les coordonnées du bloc et de la case vide  dans des variables
        
        int tempx = num_bloc_x; //du bloc qui va être déplacé 
        int  tempy = num_bloc_y;   // du bloc qui va être déplacé 
        int videx= caseVide.getCoordx(); 
        int videy= caseVide.getCoordy(); 
        
        Case caseAdep = trouveCaseByCoord(tempx,tempy);
        System.out.println(caseAdep);
      
        
        Bloc tempB = caseAdep.getBloc();
       
        
        //Le bloc prend de nouvelles coordonnées
          
        caseAdep.setBloc(null);
        caseAdep.setVide(true); 
        caseVide.setBloc(tempB);
        caseVide.setVide(false);
        
        
        /*
        on prend la case avec les coordonnées initial du bloc qu'on a déplacé
        et à ses coordonnées on met le booléen VIDE = TRUE
        on met au nouvelle coordonnée du bloc déplacé VIDE = FALSE
        */
        
    }
    /**
     * Permet de trouver la case vide dans la grille 
     * @param x coordonnée en x 
     * @param y coordonée en y 
     * @return recupère la case vide avec ses coordonées en x et y 
     */
    private Case trouveCaseByCoord(int x, int y ){
        Case c = null;
        //Parcours de l'ensemble des cases
        for (int i=0 ; i<taille ; i++){
            for (int j=0 ; j<taille ; j++){
                //Si la case a les coord passé en paramètre 
                if (this.ensCase[i][j].getCoordx()==x && this.ensCase[i][j].getCoordy()==y){
                    c = this.ensCase[i][j];
                }
            }
        }
        //Renvoie la case cherchée
        return c; 
    }
    /**
     * Permet le deplacement d'une case dans la direction souhaitée 
     * @param direction direction saisie 
     * @param j  joueur 
     * @return 
     */
    protected boolean deplacement (char direction, Joueur j){
    //permet le déplacement d'une case dans la direction souhaitée si c'est possible
        //Récupération des coordonnées de la case vide
        Case caseVide = (Case) trouveCaseVide();
        int casevide_x = caseVide.getCoordx();
        int casevide_y = caseVide.getCoordy();
        
        //Initialisation du num_bloc
        int num_bloc_x = -1;    //Initialisation à une coordonnée impossible
        int num_bloc_y = -1;    //Initialisation à une coordonnée impossible
        
        //Initialisation du booléen pour savoir si on peut vérifier le déplacement
        boolean deplacement_ok = true;
        
        //Déplacement vers la gauche
        if (direction=='q' && casevide_y!=taille-1) {
            //this.getNumBlocByCoord(casevide_x, casevide_y-1);
            num_bloc_x = casevide_x;
            num_bloc_y = casevide_y+1;
            j.setNbDeplacement();
        }
        //Déplacement vers le bas
        else if (direction=='s' && casevide_x!=0) {
            //this.getNumBlocByCoord(casevide_x+1, casevide_y);
            num_bloc_x = casevide_x-1;
            num_bloc_y = casevide_y;
            j.setNbDeplacement();
        }
        //Déplacement vers la droite
        else if (direction=='d' && casevide_y!=0) {
            //this.getNumBlocByCoord(casevide_x, casevide_y+1);
            num_bloc_x = casevide_x;
            num_bloc_y = casevide_y-1;
            j.setNbDeplacement();
        }
        //Déplacement vers le haut
        else if (direction=='z' && casevide_x!=taille-1) {
            //this.getNumBlocByCoord(casevide_x-1, casevide_y);
            num_bloc_x = casevide_x+1;
            num_bloc_y = casevide_y;
            j.setNbDeplacement();
        }
        else {
            //le déplacement n'est pas possible
            deplacement_ok = false;
        }
        
        
        //Vérifie si le déplacement est possible
        if (deplacement_ok){
            //Si oui alors on inverse les coordonnées des cases adjacentes
            inversionCoord(num_bloc_x, num_bloc_y, caseVide, direction); 
        }
        
        return deplacement_ok; 
    }
    /**
     * Verifie la fin de la fin de la partie 
     * @return un booleen qui va nous permettre de verifier la victoire 
     */
       protected boolean verifVictoire () {
        int temp_num_bloc = 0;  //variable temp pour le num du bloc précédent
        boolean ordonne = true; //TRUE si les blocs sont dans l'ordre

        Case case_Vide= trouveCaseVide(); 
       while (ordonne) {
           //si la case vide est au bon endroit on fait le test 
           if(case_Vide.getCoordx()==this.taille-1 && case_Vide.getCoordy()==this.taille-1){
        for (int i=0 ; i<taille && ordonne; i++) {
            for (int j=0 ; j<taille && ordonne ; j++) {
                    Case c = trouveCaseByCoord(i, j);
                    //on regarde si c'est une case vide qui n'est pas en bas à droite
                    if(j!=this.taille-1 || i!=this.taille-1){
                       if (c.getBloc().getNumBloc() != temp_num_bloc+1  ){
                        ordonne = false;
                    }
                       temp_num_bloc = c.getBloc().getNumBloc();
                       }                       
                    }
                }break;
            }
           else {
                ordonne = false;
           }
        }
        return ordonne;    
    }
    
    /**
     * Trouve la case vide et en renvoie une copie
     * @return la case vide 
    */
    public Case trouveCaseVide() {
        Case c = null;
        //Parcours de l'ensemble des cases
        for (int i=0 ; i<taille ; i++){
            for (int j=0 ; j<taille ; j++){
                //Si la case est vide on la récupère
                if (this.ensCase[i][j].getVide()){
                    c = this.ensCase[i][j];
                }
            }
        }
        //Renvoie la case vide
        return (Case) c;
      
    }
    /**
     * Parcours le HashSet de Bloc pour trouver le bloc qui a pour coord x et y et renvoie son numéro
     * @param x coordonnée en x du bloc voulu
     * @param y coordonnée en y du bloc voulu
     * @return renvoie le bloc 
     */
   
    private int getNumBlocByCoord(int x, int y) {
        /*
        //Déclaration et Initialisation
        int numbloc = 0;
        //Copie du HashSet de Bloc
        HashSet copieEnsBloc = ensBloc;
        //Parcours du HashSet
        Iterator it = copieEnsBloc.iterator();
        while (it.hasNext()){
            //Copie du bloc courant
            Bloc bloctemp = (Bloc) it.next();
            //Si c'est le bloc qu'on cherche alors on récupère son numéro
            if (bloctemp.getCoordx()==x && bloctemp.getCoordy()==y){
                numbloc = bloctemp.getNumBloc();
            }
        }
        
        //Retourne le numéro du bloc
        return numbloc;
        //pomme 
        */
        return 0;
    }
    
   /**
  * redéfinition de la méthode toString de la classe Object qui permet l’affichage de l’objet Grille  
  * @return affichage de la grille 
  */
    @Override
    public String toString () {
        String grilleString = "";
        //Parcours de l'ensemble de cases
        for (int i=0 ; i<this.taille ; i++){            
            for (int j=0 ; j<this.taille ; j++){
                grilleString = grilleString + "  " + ensCase[i][j].toString();
            }
            grilleString = grilleString + "\n";
        }
        return grilleString;
    }
    

}
