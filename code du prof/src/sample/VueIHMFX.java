package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueIHMFX {

    CommandeTabInt commandeGetEtat;
    GridPane gridPane = new GridPane();

    /*
        0: interieur
        1: mur
        2: player
        3: emplacement caisse
        4: caisse not ok
        5: caisse ok

        A faire
     */
    enum Images {
        //Objets directement construits
        MUR(new Image(new FileInputStream("src/sample/mur sokoban.jpg"), 50, 50, false, false)),

        EXTERIEUR ( new Image(new FileInputStream("src/sample/exterieur sokoban.jpg"), 50,50,false,false)),

        INTERIEUR( new Image(new FileInputStream("src/sample/interieur sokoban.jpg"), 50,50,false,false)),

        PERSONNAGE( new Image(new FileInputStream("src/sample/fab.jpg"), 50,50,false,false)),

        CAISSE_NOT_OK( new Image(new FileInputStream("src/sample/caisse not ok sokoban.jpg"), 50,50,false,false)),

        CAISSE_OK(new Image(new FileInputStream("src/sample/caisse ok sokoban.jpg"), 50,50,false,false)),

        CIBLE(new Image(new FileInputStream("src/sample/emplacement caisse.jpg"), 50,50,false,false));

        private Images image;

        //Constructeur
        Images(Images image){
            this.image = image;
        }

        public Images getImage(){
            return image;
        }
    }


    public VueIHMFX(Controleur controleur) throws FileNotFoundException {
        commandeGetEtat = controleur.commandeGetEtat();
        dessine();
    }

    public void dessine() {
        int[][] etat = commandeGetEtat.exec();

        Image image;

        for (int x = 0; x < etat.length; x++){
            for (int y = 0; y < etat[x].length; y++){
                switch (etat[x][y]) {
                    case 0:
                        image = Images.MUR.getImage();
                        break;
                    case 1:
                        image = Images.EXTERIEUR.getImage();
                        break;
                    case 2:
                        image = Images.INTERIEUR.getImage();
                        break;
                    case 3:
                        image = Images.PERSONNAGE.getImage();
                        break;
                    case 4:
                        image = Images.CAISSE_NOT_OK.getImage();
                        break;
                    case 5:
                        image = Images.CAISSE_OK.getImage();
                        break;
                    case 6:
                        image = Images.CIBLE.getImage();
                        break;

                }
                gridPane.add(image, x, y);
            }

        }
        
        //gridPane.add(new ImageView(chameau[0]),0,0);
    }
}
