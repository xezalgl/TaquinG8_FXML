/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author nci
 */
public class GrilleSer {
 
    /**
     * Méthode permettant d'enregistrer dans un fichier .ser les information d'une grille passer en paramétre
     * @param grille Grille, grille à enregistrer
     */
    public void SauverGrille(Grille grille) throws ClassNotFoundException{
         ObjectOutputStream oos = null;        
        try{
            final FileOutputStream fichier = new FileOutputStream("grille.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(grille);
            oos.flush();
        }catch (final java.io.IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(oos != null){
                    oos.flush();
                    oos.close();
                }
            }catch (final IOException ex){
                ex.printStackTrace();
            }
        }
    }
    
}
