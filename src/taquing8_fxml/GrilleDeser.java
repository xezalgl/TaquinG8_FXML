/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author nci
 */
public class GrilleDeser {
    /***
     * Permet de charger un fichier .ser contenant les informtion d'une grille sauvegarder
     * @return grille la Grille sous forme d'object
     * @throws ClassNotFoundException 
     */
    public Grille ChargerGrille() throws ClassNotFoundException{
        Grille grille = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;      
        
        try{
            final FileInputStream fichierln = new FileInputStream("grille.ser");
            ois = new ObjectInputStream(fichierln);
            grille = (Grille) ois.readObject();

            
            
        }catch (final java.io.IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(oos != null){
                    oos.close();
                }
            }catch (final IOException ex){
                ex.printStackTrace();
            }
        }
    return grille;
    }    
}
