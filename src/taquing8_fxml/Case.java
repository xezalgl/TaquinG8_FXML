/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.io.Serializable;

/**
 *
 * @author hazal
 */
public class Case implements Serializable{
  private int x; 
    private int y;
    private boolean vide;
    private Bloc b;
    
    /**
     * consctructeur d'une case
     * @param abs int abscisse de la case
     * @param ord int ordonnée de la case
     * @param v boolean, case vide=true, false sinon
     * @param num int numéro de la case
     */ 
    public Case(int abs, int ord, boolean v, int num){
        this.x=abs; 
        this.y=ord; 
        this.vide=v;
        Bloc bloc =new Bloc(this.x,this.y,num);
        this.b=bloc;
    }

    /**
   * accès en lecture au vide 
    @return la coordonée en x 
    */
    public int getCoordx(){    
       return (int) this.x; 
    }
   /**
    * accès en lecture au coord y 
    @return la coordonée en y
    */
    public int getCoordy(){    
       return this.y; 
    }
    
    /**
     * accès en lecture au vide 
     * @return vide si la case est vide 
     */
    public boolean getVide(){
        return this.vide; 
    }
    
    /**
     *  accès en écriture à la valeur  x
     * @param newx nouvelle coord en x 
     */
    public void setCoordx (int newx){
        this.x=newx; 
    }
    
    /**
     * accès en écriture à la valeur  y 
     * @param new y nouvelle coord en y 
     */
    public void setCoordy(int newy){
        this.y=newy; 
    }
    
    /**
     * accès en ecriture au booleen  vide 
     * @param v nouvelle valeur 
    */
    public void setVide(boolean v){
        this.vide=v;
    }
    /**
     * accès en écriture au numéro du bloc  
     * @param newBloc nouveau bloc 
    */
    
    public void setBloc(Bloc newBloc){
        this.b=newBloc;
    }
    
    /**
     * accès en lecture au numéro du bloc  
     * @return le bloc  
    */
    public Bloc getBloc(){
        return this.b;
    }
    
  
 /**
  * redéfinition de la méthode toString de la classe Object qui permet l’affichage de l’objet Case 
  * @return affichage de la case 
  */
    @Override
    public String toString() {
        if (this.getVide()){
            return "[ ]";
        }
        else{
        return "["+ this.getBloc().getNumBloc() +']';
        }
    }     
}
