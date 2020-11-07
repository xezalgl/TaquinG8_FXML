/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
            MainsansGUI.main(args);
            System.out.println("Lancement du programme sans GUI");
            Grille g  = new Grille (4);
            
            System.out.println(g);
            Chrono chronos = new Chrono();
            Timer chrono = new Timer();
            chrono.schedule(new TimerTask(){
                @Override
                public void run(){
                    chronos.setCmpt(chronos.getCmpt()+1);
                }
            },1000,1000);
        
        
       
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
                System.out.println("Chrono :"+chronos.getCmpt()+" secondes.");
                System.out.println(g); 
        }
        
        if(g.verifVictoire()){
            chronos.setFini(true);
            int tempsTot=chronos.getCmpt();
            System.out.println("finish !!!!");
            System.out.println(tempsTot+" secondes");
        }
        }
    }
    
}
