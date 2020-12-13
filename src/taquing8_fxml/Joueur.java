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
    private int temps; // temps pour résoudre le taquin courant
    private int score; //le score
    
    public Joueur(){
        this.deplacement=0;
        this.score=0;
    }
    
    /**
     * affecte un pseudo au joueur courant
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
    
    //getteur/setteur du pseudo 
    public String getPseudo(){
        return pseudo;   
    } 
    public void setPseudo(String ps){
        this.pseudo=ps;  
    }
        
    /**
     * Getteur du nb de deplacement
     * @return int nombre de deplacement du joueur
     */
    public int getNbDeplacement(){
        return deplacement; 
    }
    
    /**
     * Incrémente 1 a l'ttribut deplacament du joueur
     */
    public void setNbDeplacement (){
        this.deplacement=getNbDeplacement()+1; 
    }
    public void creationProfil(){
        //mise a jour du profil et appelle saisie pseudo 
    }
    /**
     * Permet d'actualiser le score du joueur. Temps écoulé du jeu * nombre de déplacements
     * @param temps int temps écoulé pour résoudre le jeu
     */
    public void setScore(int temps){
        this.score=this.deplacement*temps; 
    }
}
