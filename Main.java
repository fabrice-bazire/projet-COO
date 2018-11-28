

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main extends Application {

    Image mur = new Image(new FileInputStream("src/sample/mur sokoban.jpg"),80,80,false,false);
    Image ext = new Image(new FileInputStream("src/sample/exterieur sokoban.jpg"), 80, 80, false, false);
    Image inside = new Image(new FileInputStream("src/sample/interieur sokoban.jpg"), 80, 80, false, false);

    Image[] image = {mur,ext,inside};

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

         int[][] etat = {{2,2,2,2,2,2,2},{2,0,0,0,0,0,2}, {2,0,1,1,1,0,2}, {2,0,1,1,1,0,2}, {2,0,1,1,1,0,2}, {2,0,0,0,0,0,2}, {2,2,2,2,2,2,2}};

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < etat.length; i++){
             for (int j = 0; j < etat[0].length; j++){
		        grid.add(new ImageView(image[etat[i][j]]),i,j);
		    }
		}

        Button btn2 = new Button("Fermer");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 8, 8);

        Image imagegauche = new Image(new FileInputStream("src/sample/fleche gauche.jpg"),30,30,false,false);
        ImageView imageViewgauche = new ImageView(imagegauche);
        Button btn3 = new Button("");
        btn3.setGraphic(imageViewgauche);
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn3.getChildren().add(btn3);
        grid.add(hbBtn3, 1, 11);

        Image imagedroite = new Image(new FileInputStream("src/sample/fleche droite.jpg"),30,30,false,false);
        ImageView imageViewdroite = new ImageView(imagedroite);
        Button btn4 = new Button("");
        btn3.setGraphic(imageViewdroite);
        HBox hbBtn4 = new HBox(10);
        hbBtn4.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn4.getChildren().add(btn4);
        grid.add(hbBtn4, 2, 10);

        Image imagehaut = new Image(new FileInputStream("src/sample/fleche haut.jpg"),30,30,false,false);
        ImageView imageViewhaut = new ImageView(imagehaut);
        Button btn5 = new Button("");
        btn3.setGraphic(imageViewhaut);
        HBox hbBtn5 = new HBox(10);
        hbBtn5.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn5.getChildren().add(btn5);
        grid.add(hbBtn5, 1, 9);

        Image imagebas = new Image(new FileInputStream("src/sample/fleche bas.jpg"),30,30,false,false);
        ImageView imageViewbas = new ImageView(imagebas);
        Button btn6 = new Button("");
        btn3.setGraphic(imageViewbas);
        HBox hbBtn6 = new HBox(10);
        hbBtn6.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn6.getChildren().add(btn6);
        grid.add(hbBtn6, 0, 10);

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event2) {
                primaryStage.close();
            }
        });

        primaryStage.setScene(new Scene(grid, 960, 700));
        primaryStage.setTitle("Sokoban");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}