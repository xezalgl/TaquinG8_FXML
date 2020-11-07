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
    
    public Chrono(){
        this.fini=false;
    }

    public int getCmpt() {
        return cmpt;
    }

    public void setCmpt(int cmpt) {
        this.cmpt = cmpt;
    }

    public boolean isFini() {
        return fini;
    }

    public void setFini(boolean fini) {
        this.fini = fini;
    }
    
    
}
