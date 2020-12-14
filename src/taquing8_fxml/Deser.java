package taquing8_fxml;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import taquing8_fxml.Grille;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nci
 */
public class Deser {
    
    
    public Joueur ChargerJoueur() throws ClassNotFoundException{
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Joueur j1 = new Joueur();
        
        try{
            
        FileInputStream fichierl = new FileInputStream("joueur.ser");
            
            ois = new ObjectInputStream(fichierl);
            j1 = (Joueur) ois.readObject();        
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
        return j1;
    }    
        
        
    public void ChargerGrille(Joueur j1, Grille grille) throws ClassNotFoundException{
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;    
        
        try{
            
            FileInputStream fichierln = new FileInputStream("grille.ser");
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
    }    
    
}
