/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

/**
 *
 * @author hazal
 */
public class Bloc {
     
    private int x; 
    private int y; 
    private int n;
    
    //constructeur de la classe
    public Bloc (int x,int y, int num){ 
        this.x=x; 
        this.y=y; 
        this.n=num;      
    }
    
    
    //Méthode qui permet de modifer la coordonnée x d'un bloc
    public void setCoordx (int x){
        this.x=x; 
    }
    
    //Méthode qui permet de modifier la coordonnée y d'un bloc
    public void setCoordy(int y){
        this.y=y; 
    }
    
    // Méthode qui permet de modifier le numéro d'un bloc 
    public void setNum(int num){
        this.n=num; 
    }
    
    // Méthode qui permet d'obtenir la coordonnée x d'un bloc
    public int  getCoordx (){
        return x; 
    }
    
    // Méthode qui permet d'obtenir la coordonnée y d'un bloc
    public int  getCoordy (){
        return y; 
    }
    
    // Méthode qui permet d'obtenir le numéro d'un bloc
    public int  getNumBloc (){
        return n; 
    }
  
    
    // implémnation pointillé avec fleche simple (non remplis) , MAJUSCULE si static et le final, 
    //plusieurs schéma de diagrammmes d'utilisation en fonction de l'utilisation et du moment 
    //creer un profil -> se connecter
    //couper a deplacer les cases du puzzle 
    //Dans profil insérer le critère de tri soit par le temps soit par le nombre de coups 
    
}
