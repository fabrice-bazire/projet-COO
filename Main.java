package sample;

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
import java.lang.String;
import javafx.scene.text.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    Image mur = new Image(new FileInputStream("src/sample/mur sokoban.jpg"),50,50,false,false);
    Image ext = new Image(new FileInputStream("src/sample/exterieur sokoban.jpg"), 50, 50, false, false);
    Image inside = new Image(new FileInputStream("src/sample/interieur sokoban.jpg"), 50, 50, false, false);
    Image perso = new Image(new FileInputStream("src/sample/fab.jpg"), 50, 50, false, false);
    Image caisse_not_ok = new Image(new FileInputStream("src/sample/caisse not ok sokoban.jpg"), 50, 50, false, false);
    Image caisse_ok = new Image(new FileInputStream("src/sample/caisse ok sokoban.jpg"), 50, 50, false, false);
    Image emplacement_caisse = new Image(new FileInputStream("src/sample/emplacement caisse.jpg"), 50, 50, false, false);

    Image[] image = {mur,ext,inside, perso, caisse_not_ok, caisse_ok, emplacement_caisse};

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        int[] position_perso = {2,5};
        int[][] etat = {{1,1,1,1,1,1,1,1,1,1},{1,0,0,0,0,0,0,0,0,1},{1,0,2,2,2,2,2,2,0,1},{1,0,2,2,2,2,2,2,0,1},{1,0,2,2,2,2,2,2,0,1},{1,0,2,2,2,2,2,2,0,1},{1,0,2,2,2,2,2,2,0,1},{1,0,2,2,2,2,2,2,0,1},{1,0,0,0,0,0,0,0,0,1},{1,1,1,1,1,1,1,1,1,1}};
        etat [position_perso[0]] [position_perso[1]] = 3;
        etat [5][5] = 4;
        etat [2][7] = 6;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < etat.length; i++){
             for (int j = 0; j < etat[0].length; j++){
		        grid.add(new ImageView(image[etat[i][j]]),i,j);
		    }
		}

        Button btn2 = new Button("Exit");
        grid.add(btn2, 8, 13);

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event2) {
                primaryStage.close();
            }
        });


        Button btn3 = new Button("<-");
        grid.add(btn3, 0, 12);

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event3) {
                System.out.println("gauche");
                if (((etat[position_perso[0]-2] [position_perso[1]] == 2 || etat[position_perso[0]-2] [position_perso[1]] == 6) && (etat[position_perso[0]-1] [position_perso[1]] == 4)) || (etat[position_perso[0]-1] [position_perso[1]] == 2)){
                    position_perso[0]--;
                    if (etat[position_perso[0]][position_perso[1]] == 4) {
                        if (etat[position_perso[0]-1][position_perso[1]] == 6){
                            etat[position_perso[0]-1][position_perso[1]] = 5;
                            grid.add(new ImageView(image[5]), position_perso[0]-1, position_perso[1]);
                            Text scenetitle = new Text("Vous avez gagné !!!");
                            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                            grid.add(scenetitle, 16, 11);
                        }else{
                            etat[position_perso[0]-1][position_perso[1]] = 4;
                            grid.add(new ImageView(image[4]), position_perso[0]-1, position_perso[1]);
                        }
                    }
                    etat[position_perso[0]][position_perso[1]] = 3;
                    etat[position_perso[0] + 1][position_perso[1]] = 2;
                    grid.add(new ImageView(image[2]), position_perso[0] + 1, position_perso[1]);
                    grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                }
            }
        });


        Button btn4 = new Button("->");
        grid.add(btn4, 2, 12);

        btn4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event4) {
                System.out.println("droite");
                if (((etat[position_perso[0]+2] [position_perso[1]] == 2 || etat[position_perso[0]+2] [position_perso[1]] == 6) && (etat[position_perso[0]+1] [position_perso[1]] == 4)) || (etat[position_perso[0]+1] [position_perso[1]] == 2)) {
                    position_perso[0]++;
                    if (etat[position_perso[0]][position_perso[1]] == 4) {
                        if (etat[position_perso[0]+1][position_perso[1]] == 6){
                            etat[position_perso[0]+1][position_perso[1]] = 5;
                            grid.add(new ImageView(image[5]), position_perso[0]+1, position_perso[1]);
                            Text scenetitle = new Text("Vous avez gagné !!!");
                            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                            grid.add(scenetitle, 16, 11);
                        }else{
                            etat[position_perso[0]+1][position_perso[1]] = 4;
                            grid.add(new ImageView(image[4]), position_perso[0]+1, position_perso[1]);
                        }
                    }
                    etat[position_perso[0]][position_perso[1]] = 3;
                    etat[position_perso[0] - 1][position_perso[1]] = 2;
                    grid.add(new ImageView(image[2]), position_perso[0] - 1, position_perso[1]);
                    grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                }
            }
        });


        Button btn5 = new Button("^\n|");
        grid.add(btn5, 1, 11);

        btn5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event4) {
                System.out.println("haut");
                if (((etat[position_perso[0]] [position_perso[1]-2] == 2 || etat[position_perso[0]] [position_perso[1]-2] == 6) && (etat[position_perso[0]] [position_perso[1]-1] == 4)) || (etat[position_perso[0]] [position_perso[1]-1] == 2)) {
                    position_perso[1]--;
                    if (etat[position_perso[0]][position_perso[1]] == 4) {
                        if (etat[position_perso[0]][position_perso[1]-1] == 6){
                            etat[position_perso[0]][position_perso[1] - 1] = 5;
                            grid.add(new ImageView(image[5]), position_perso[0], position_perso[1] - 1);
                            Text scenetitle = new Text("Vous avez gagné !!!");
                            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                            grid.add(scenetitle, 16, 11);
                        }else{
                            etat[position_perso[0]][position_perso[1] - 1] = 4;
                            grid.add(new ImageView(image[4]), position_perso[0], position_perso[1] - 1);
                        }
                    }
                    etat[position_perso[0]][position_perso[1]] = 3;
                    etat[position_perso[0]][position_perso[1] + 1] = 2;
                    grid.add(new ImageView(image[2]), position_perso[0], position_perso[1] + 1);
                    grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                }
            }
        });


        Button btn6 = new Button(" |\n\\/");
        grid.add(btn6, 1, 13);


        btn6.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event4) {
                System.out.println("bas");
                if (((etat[position_perso[0]] [position_perso[1]+2] == 2 || etat[position_perso[0]] [position_perso[1]+2] == 6) && (etat[position_perso[0]] [position_perso[1]+1] == 4)) || (etat[position_perso[0]] [position_perso[1]+1] == 2)) {
                    position_perso[1]++;
                    if (etat[position_perso[0]][position_perso[1]] == 4) {
                        if (etat[position_perso[0]][position_perso[1]+1] == 6){
                            etat[position_perso[0]][position_perso[1] + 1] = 5;
                            grid.add(new ImageView(image[5]), position_perso[0], position_perso[1] + 1);
                            Text scenetitle = new Text("Vous avez gagné !!!");
                            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                            grid.add(scenetitle, 16, 11);
                        }else{
                            etat[position_perso[0]][position_perso[1] + 1] = 4;
                            grid.add(new ImageView(image[4]), position_perso[0], position_perso[1] + 1);
                        }
                    }
                    etat[position_perso[0]][position_perso[1]] = 3;
                    etat[position_perso[0]][position_perso[1] - 1] = 2;
                    grid.add(new ImageView(image[2]), position_perso[0], position_perso[1] - 1);
                    grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                }
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