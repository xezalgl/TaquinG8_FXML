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
    final Color colCerisier = new Color (230, 162, 185, 1.0);
    final Color colElectro = new Color (31, 77, 42, 1.0);
    final Color colDragon = new Color (138, 203, 238, 1.0);
    final Color colDefaut = new Color (171, 177, 224, 1.0);
    
    //Nom des images
    final String urlCerisier = "cerisier.png";
    final String urlElectro = "carte_electronique.png";
    final String urlDragon = "dragons.png";
    final String urlDefaut = "lego_lila.png";
    
    //Couleur de la case vide
    final Color colVideCerisier = new Color (169, 59, 114, 1.0);
    final Color colVideElectro = new Color (18, 149, 49, 1.0);
    final Color colVideDragon = new Color (59, 124, 162, 1.0);
    final Color colVideDefaut = new Color (132, 94, 222, 1.0);
    
    //Couleur de texte des labels
    final Color colTextCerisier = new Color (112, 36, 62, 1.0);
    final Color colTextElectro = new Color (136, 149, 139, 1.0);
    final Color colTextDragon = new Color (21, 56, 77, 1.0);
    final Color colTextDefaut = new Color (68, 59, 88, 1.0);
}
