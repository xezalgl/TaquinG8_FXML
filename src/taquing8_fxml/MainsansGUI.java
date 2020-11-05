/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.util.Scanner;

/**
 *
 * @author nci
 */
public class MainsansGUI {
    public static void main(String[] args){
        if (args.length==0) {
            TaquinG8_FXML.main(args);
        }
        else{
            System.out.println("Lancement du programme sans GUI");
            Grille g  = new Grille (4);
            System.out.println(g);
        
        
       
        Scanner sc = new Scanner(System.in);
        //Initialisation du scanner
        while(!g.verifVictoire()){   
            System.out.println("Saisissez une lettre"
                    + "\n d = droite"
                    + "\n s = bas"
                    + "\n q = gauche"
                    + "\n z = haut");
            char d = sc.next().charAt(0);
            System.out.println("Vous avez saisie la direction : " + d); //Saisie par le joueur
            //Test si la saisie est valide
            g.deplacement(d); 
            g.toString(); 
            System.out.println(g); 
        }
        
        if(g.verifVictoire()){
            System.out.println("finish !!!!");
        }
        }
    }
    
}
