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
public class Chrono {
    private int cmpt=0;
    private boolean fini;
    /**
     * constructeur de la classe Chrono 
     */
    public Chrono(){
        this.fini=false;
    }
   /**
     * accès en lecture au compteur 
     * @return le compteur   
    */
    public int getCmpt() {
        return cmpt;
    }
 /**
     * accès en écriture au compteur 
     * @param cmpt  le compteur   
    */
    public void setCmpt(int cmpt) {
        this.cmpt = cmpt;
    }
/**
 * permet d'arreter le chrono 
 * @return fini 
 */
    public boolean isFini() {
        return fini;
    }
 /**
     * accès en écriture au compteur  
     * @param fini fin du chrono 
    */
    public void setFini(boolean fini) {
        this.fini = fini;
    }
    
    
}
