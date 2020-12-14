/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquing8_fxml;

import javafx.scene.paint.Color;

/**
 *
 * @author loeli
 */
public interface Parametres {
    //Couleur de fond pour chaque thème
    final Color colCerisier = Color.rgb(229, 162, 185);
    final Color colElectro = Color.rgb (31, 77, 42);
    final Color colDragon = Color.rgb (138, 203, 238);
    final Color colDefaut = Color.rgb (171, 177, 224);
    
    //Nom des images
    final String urlCerisier = "cerisier.png";
    final String urlElectro = "carte_electronique.png";
    final String urlDragon = "dragons.png";
    final String urlDefaut = "lego_lila.png";
    
    //Couleur de la case vide
    final Color colVideCerisier = Color.rgb (169, 59, 114);
    final Color colVideElectro = Color.rgb (18, 149, 49);
    final Color colVideDragon = Color.rgb (59, 124, 162);
    final Color colVideDefaut = Color.rgb (132, 94, 222);
    
    //Couleur de texte des labels
    final Color colTextCerisier = Color.rgb (112, 36, 62);
    final Color colTextElectro = Color.rgb (136, 149, 139);
    final Color colTextDragon = Color.rgb (21, 56, 77);
    final Color colTextDefaut = Color.rgb (68, 59, 88);
}
