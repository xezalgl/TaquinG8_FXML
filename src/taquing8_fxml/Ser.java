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
public class Ser {
 
    /**
     * Méthode permettant d'enregistrer dans un fichier .ser les information d'une grille passer en paramétre
     * @param grille Grille, grille à enregistrer
     */
    public void SauverJeu(Grille grille, Joueur j1) throws ClassNotFoundException{
         ObjectOutputStream oos = null;        
        try{
            final FileOutputStream fichier = new FileOutputStream("grille.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(grille);
            final FileOutputStream fichier2 = new FileOutputStream("joueur.ser");
            oos = new ObjectOutputStream(fichier2);
            oos.writeObject(j1);
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
