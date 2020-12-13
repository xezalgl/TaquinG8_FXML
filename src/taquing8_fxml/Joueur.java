/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.util.Scanner;

/**
 *
 * @author hazal
 */
public class Joueur {
    private String pseudo="";  // represente le pseudo du joueur 
    private int deplacement; // le nombre de deplacement 
    private int score; //le score
    private String mot_passe; 
    /**
     * Constructeur de la joueur 
     */
    public Joueur(){
        this.deplacement=0;
        this.score=0;
    }
    /**
     *saisie du pseudo du joueur 
     */
     public void setPseudo(){
  
        Scanner sc = new Scanner(System.in);  //Initialisation du scanner
        System.out.println("\nNouveau Joueur, entre ton pseudo : "); 
        String ps = sc.nextLine();   //Saisie par le joueur
        //Test si la saisie est valide
        if ("".equals(ps.trim())){
            System.out.println("Ton pseudo n'est pas valide, recommence.");
            this.setPseudo(); 
        } 
        this.pseudo = ps;
    }
     /**
      * accès en lecture au pseudo du joueur 
      * @return renvoie la pseudo du joueur 
      */
     public String getPseudo(){
         //getteur du pseudo 
         System.out.println("eqh"+pseudo);
     return pseudo;   
} 
     /**
      * accès en écriture au pseudo du joueur 
      * @param mdp le mot de passe 
      */
     public void setMdp(String mdp){
         this.mot_passe=mdp; 
         
     }
     /**
      * accès en lecture du nombre de déplacement 
      * @return le mot de passe 
      */
     public String getMdp(){
         return mot_passe; 
     }
       /**
        * accès en lecture du nombre de déplacement 
        * @return le nombre de déplacement 
        */
     public int getNbDeplacement(){
         // retourne ne le nombre de deplacement 
         return deplacement; 
     }
     /**
      * accès en écriture au pseudo du joueur 
      * @param ps pseudo du joueur 
      */
     public void setPseudo(String ps){
            this.pseudo=ps;  
         }
    /**
      * accès en écriture au nombre de déplacement
      */
     public void setNbDeplacement (){
         //permet d'incrementer +1
         this.deplacement= deplacement+1; 
     }
     /**
      * 
      */
     public void creationProfil(){
         //mise a jour du profil et appelle saisie pseudo 
     }
    public String toString(){
        return ("pseudo"+this.pseudo +"\n"+ "mot de passe "+this.mot_passe); 
    }
}
