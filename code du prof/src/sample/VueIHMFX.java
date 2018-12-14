package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueIHMFX {

    CommandeTabInt commandeGetEtat;
    public GridPane grid = new GridPane();

    /*
        0: interieur
        1: mur
        2: player
        3: emplacement caisse
        4: caisse not ok
        5: caisse ok

        A faire
     */


    Image mur = (new Image(new FileInputStream("src/sample/mur sokoban.jpg"), 50, 50, false, false));
    Image exterieur = ( new Image(new FileInputStream("src/sample/exterieur sokoban.jpg"), 50,50,false,false));


   private enum Images {
        //Objets directement construits

        MUR("src/sample/mur sokoban.jpg"),

        EXTERIEUR ("src/sample/exterieur sokoban.jpg"),

        INTERIEUR("src/sample/interieur sokoban.jpg"),

        PERSONNAGE( "src/sample/fab.jpg"),

        CAISSE_NOT_OK( "src/sample/caisse not ok sokoban.jpg"),

        CAISSE_OK("src/sample/caisse ok sokoban.jpg"),

        CIBLE("src/sample/emplacement caisse.jpg");

        private Image image;

        //Constructeur
        private Images(String name){
            try {
                this.image = new Image(new FileInputStream(name), 50,50,false,false);
            } catch (FileNotFoundException e) {
                System.out.print(e.getMessage());
            }
        }

        public Image getImage(){
            return this.image;
        }
    }


    public VueIHMFX(Controleur controleur) throws FileNotFoundException {
        commandeGetEtat = controleur.commandeGetEtat();
        dessine();
    }

    public void dessine() {
        int[][] etat = commandeGetEtat.exec();

        Image image = null;

        grid.getChildren().remove(0, grid.getChildren().size());

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
                    case 9:
                        image = Images.PERSONNAGE.getImage();
                    }
                grid.add(new ImageView(image), x, y);
            }

        }
    }
}
