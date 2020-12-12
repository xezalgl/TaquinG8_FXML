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
 

    public static void main(String[] args){
        Grille grille = new Grille(4);
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
