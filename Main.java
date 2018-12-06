package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.lang.model.type.NullType;
import java.lang.String;

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

    Image win = new Image(new FileInputStream("src/sample/win.png"), 200, 200, false, false);

    Image[] image = {mur,ext,inside, perso, caisse_not_ok, caisse_ok, emplacement_caisse};

    static int nbcibles = 2;

    GridPane grid = new GridPane();

    public Main() throws FileNotFoundException {
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        int[] position_perso = {2,7};
        int[][] etat = { {0,0,0,0,0,1,1,1,1,1},{0,2,2,2,0,0,0,0,0,0},{0,2,2,2,0,2,2,2,2,0},{0,2,2,2,2,2,2,2,2,0},{0,0,0,0,0,2,2,2,2,0},{1,1,1,1,0,2,0,0,0,0},{1,1,0,0,0,2,2,2,0,1},{1,1,0,2,2,2,2,2,0,1},{1,1,0,2,2,2,2,2,0,1},{1,1,0,0,0,0,0,0,0,1} };
        etat [position_perso[0]] [position_perso[1]] = 3;
        //etat [3][5] = 4;
        //etat [3][8] = 4;
        etat [7][4] = 4;
        etat [5][5] = 6;
        etat [4][6] = 4;
        etat [7][3] = 6;
        int[] position_cible = {5, 5, 7, 3};

        grid.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(grid, 960, 700));
        primaryStage.setTitle("Sokoban");
        primaryStage.show();


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


        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (nbcibles == 0){
                    try{
                        Thread.sleep(4 * 1000);
                        primaryStage.close();
                    } catch (InterruptedException e) {
                        System.out.println("erreur du programme : " + e.getMessage());
                    }
                }
                KeyCode input = event.getCode();
                if (input.equals(KeyCode.LEFT)){
                    if (((etat[position_perso[0]-2] [position_perso[1]] == 2 || etat[position_perso[0]-2] [position_perso[1]] == 6) && ((etat[position_perso[0]-1] [position_perso[1]] == 4) || (etat[position_perso[0]-1] [position_perso[1]] == 5))) || (etat[position_perso[0]-1] [position_perso[1]] == 2) || etat[position_perso[0]-1] [position_perso[1]] == 6) {
                        position_perso[0]--;
                        if (etat[position_perso[0]][position_perso[1]] == 4 || (etat[position_perso[0]][position_perso[1]] == 5)) {

                            if(etat[position_perso[0]][position_perso[1]] == 5){
                                nbcibles++;
                            }


                            if (etat[position_perso[0] - 1][position_perso[1]] == 6) {
                                etat[position_perso[0] - 1][position_perso[1]] = 5;
                                grid.add(new ImageView(image[5]), position_perso[0] - 1, position_perso[1]);
                                nbcibles--;
                                System.out.println(nbcibles);
                                if (nbcibles == 0) {
                                    grid.add(new ImageView(win), 5,0,11, 4);
                                    grid.add(new ImageView(win), 5,6,11, 4);
                                    grid.add(new ImageView(win), 0,0,11, 4);
                                    grid.add(new ImageView(win), 0,6,11, 4);
                                }
                            }else {
                                etat[position_perso[0] - 1][position_perso[1]] = 4;
                                grid.add(new ImageView(image[4]), position_perso[0] - 1, position_perso[1]);

                            }
                        }
                        etat[position_perso[0]][position_perso[1]] = 3;

                        boolean bool = false;

                        if (bool == false) {
                            etat[position_perso[0] + 1][position_perso[1]] = 2;
                            grid.add(new ImageView(image[2]), position_perso[0] + 1, position_perso[1]);
                        }

                        grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                        for (int i = 0; i < position_cible.length - 1; i = i + 2) {
                            if (position_cible[i] == position_perso[0] + 1 && position_cible[i + 1] == position_perso[1]) {
                                etat[position_perso[0] + 1][position_perso[1]] = 6;


                                grid.add(new ImageView(image[6]), position_perso[0] + 1, position_perso[1]);
                            }
                        }
                    }
                }else if (input.equals(KeyCode.RIGHT)){
                    if (((etat[position_perso[0]+2] [position_perso[1]] == 2 || etat[position_perso[0]+2] [position_perso[1]] == 6) && ((etat[position_perso[0]+1] [position_perso[1]] == 4) || (etat[position_perso[0]+1] [position_perso[1]] == 5))) || (etat[position_perso[0]+1] [position_perso[1]] == 2) || etat[position_perso[0]+1] [position_perso[1]] == 6) {
                        position_perso[0]++;
                        if ((etat[position_perso[0]][position_perso[1]] == 4) || (etat[position_perso[0]][position_perso[1]] == 5)) {

                            if(etat[position_perso[0]][position_perso[1]] == 5){
                                nbcibles++;
                            }


                            if (etat[position_perso[0]+1][position_perso[1]] == 6){
                                etat[position_perso[0]+1][position_perso[1]] = 5;
                                grid.add(new ImageView(image[5]), position_perso[0]+1, position_perso[1]);
                                nbcibles--;
                                System.out.println(nbcibles);
                                if (nbcibles == 0){
                                    grid.add(new ImageView(win), 5,0,11, 4);
                                    grid.add(new ImageView(win), 5,6,11, 4);
                                    grid.add(new ImageView(win), 0,0,11, 4);
                                    grid.add(new ImageView(win), 0,6,11, 4);
                                }
                            }
                            else{
                                etat[position_perso[0]+1][position_perso[1]] = 4;
                                grid.add(new ImageView(image[4]), position_perso[0]+1, position_perso[1]);

                            }
                        }
                        etat[position_perso[0]][position_perso[1]] = 3;

                        boolean bool = false;

                        if (bool == false){
                            etat[position_perso[0] - 1][position_perso[1]] = 2;
                            grid.add(new ImageView(image[2]), position_perso[0] - 1, position_perso[1]);
                        }

                        grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                        for(int i=0; i<position_cible.length-1; i=i+2) {
                            if (position_cible[i] == position_perso[0] - 1 && position_cible[i + 1] == position_perso[1]) {
                                etat[position_perso[0] - 1][position_perso[1]] = 6;


                                grid.add(new ImageView(image[6]), position_perso[0] - 1, position_perso[1]);
                            }
                        }
                    }
                }else if (input.equals(KeyCode.UP)){
                    if (((etat[position_perso[0]] [position_perso[1]-2] == 2 || etat[position_perso[0]] [position_perso[1]-2] == 6) && ((etat[position_perso[0]] [position_perso[1]-1] == 4) || (etat[position_perso[0]] [position_perso[1]-1] == 5))) || (etat[position_perso[0]] [position_perso[1]-1] == 2) || etat[position_perso[0]] [position_perso[1]-1] == 6) {
                        position_perso[1]--;
                        if (etat[position_perso[0]][position_perso[1]] == 4 || (etat[position_perso[0]][position_perso[1]] == 5)) {

                            if(etat[position_perso[0]][position_perso[1]] == 5){
                                nbcibles++;
                            }


                            if (etat[position_perso[0]][position_perso[1]-1] == 6){
                                etat[position_perso[0]][position_perso[1] - 1] = 5;
                                grid.add(new ImageView(image[5]), position_perso[0], position_perso[1] - 1);
                                nbcibles--;
                                System.out.println(nbcibles);
                                if (nbcibles == 0){
                                    grid.add(new ImageView(win), 5,0,11, 4);
                                    grid.add(new ImageView(win), 5,6,11, 4);
                                    grid.add(new ImageView(win), 0,0,11, 4);
                                    grid.add(new ImageView(win), 0,6,11, 4);
                                }
                            }
                            else{
                                etat[position_perso[0]][position_perso[1] - 1] = 4;
                                grid.add(new ImageView(image[4]), position_perso[0], position_perso[1] - 1);

                            }
                        }
                        etat[position_perso[0]][position_perso[1]] = 3;

                        boolean bool = false;

                        if (bool == false){
                            etat[position_perso[0]][position_perso[1]+1] = 2;
                            grid.add(new ImageView(image[2]), position_perso[0], position_perso[1]+1);
                        }
                        grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                        for(int i=0; i<position_cible.length-1; i=i+2) {
                            if (position_cible[i] == position_perso[0] && position_cible[i + 1] == position_perso[1]+1) {
                                etat[position_perso[0]][position_perso[1]+1] = 6;

                                nbcibles ++;
                                System.out.print(nbcibles);

                                grid.add(new ImageView(image[6]), position_perso[0], position_perso[1]+1);
                            }
                        }
                    }

                }else if (input.equals(KeyCode.DOWN)){
                    if (((etat[position_perso[0]] [position_perso[1]+2] == 2 || etat[position_perso[0]] [position_perso[1]+2] == 6) && ((etat[position_perso[0]] [position_perso[1]+1] == 4) || (etat[position_perso[0]] [position_perso[1]+1] == 5))) || (etat[position_perso[0]] [position_perso[1]+1] == 2) || etat[position_perso[0]] [position_perso[1]+1] == 6) {
                        position_perso[1]++;
                        if (etat[position_perso[0]][position_perso[1]] == 4 || (etat[position_perso[0]][position_perso[1]] == 5)) {


                            if(etat[position_perso[0]][position_perso[1]] == 5){
                                nbcibles++;
                            }


                            if (etat[position_perso[0]][position_perso[1]+1] == 6){
                                etat[position_perso[0]][position_perso[1] + 1] = 5;
                                grid.add(new ImageView(image[5]), position_perso[0], position_perso[1] + 1);
                                nbcibles--;
                                System.out.println(nbcibles);
                                if (nbcibles == 0){
                                    grid.add(new ImageView(win), 5,0,11, 4);
                                    grid.add(new ImageView(win), 5,6,11, 4);
                                    grid.add(new ImageView(win), 0,0,11, 4);
                                    grid.add(new ImageView(win), 0,6,11, 4);
                                }
                            }
                            else{
                                etat[position_perso[0]][position_perso[1] + 1] = 4;
                                grid.add(new ImageView(image[4]), position_perso[0], position_perso[1] + 1);

                            }
                        }
                        etat[position_perso[0]][position_perso[1]] = 3;

                        boolean bool = false;

                        if (bool == false){
                            etat[position_perso[0]][position_perso[1]-1] = 2;
                            grid.add(new ImageView(image[2]), position_perso[0], position_perso[1]-1);
                        }
                        grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
                        for(int i=0; i<position_cible.length-1; i=i+2) {
                            if (position_cible[i] == position_perso[0] && position_cible[i + 1] == position_perso[1]-1) {
                                etat[position_perso[0]][position_perso[1]-1] = 6;


                                grid.add(new ImageView(image[6]), position_perso[0], position_perso[1]-1);
                            }
                        }
                    }
                }
            }
        });

        grid.requestFocus();

    }



    public static void main(String[] args) {
        launch(args);
    }
}