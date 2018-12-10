package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueIHMFX {

    CommandeTabInt commandeGetEtat;
    GridPane gridPane = new GridPane();

    Image[] chameau = new Image[]{ new Image(new FileInputStream(
            "chameau0.gif"),80,80,false,false),
            new Image(new FileInputStream(
                    "chameau1.png"),80,80,false,false),
            new Image(new FileInputStream(
                    "chameau2.jpg"),80,80,false,false)};


    public VueIHMFX(Controleur controleur) throws FileNotFoundException {
        commandeGetEtat = controleur.commandeGetEtat();
        dessine();
    }

    public void dessine() {
        int[][] etat = commandeGetEtat.exec();
        
        gridPane.add(new ImageView(chameau[0]),0,0);
    }
}
