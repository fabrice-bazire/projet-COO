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
import java.lang.String;
import java.io.*;
import java.util.Scanner;

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

    static int nbcibles = nb_cibles("src/sample/test.xsb");

    static int[] position_cible = new int [nbcibles*2];

    static int[] position_perso = new int [2];

    static int [][] etat = lecture_fichier_xsb("src/sample/test.xsb");

    GridPane grid = new GridPane();

    public Main() throws FileNotFoundException {
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        position_cible = cibles("src/sample/test.xsb");

        for (int i = 0; i < 2 * nb_cibles("src/sample/test.xsb"); i++){
            System.out.print(position_cible[i] + " | ");
        }


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
                        Thread.sleep(2 * 1000);
                        primaryStage.close();
                    } catch (InterruptedException e) {
                        System.out.println("erreur du programme : " + e.getMessage());
                    }
                }
                KeyCode input = event.getCode();
                if (input.equals(KeyCode.LEFT)){
                    move(-1, 0);
                }else if (input.equals(KeyCode.RIGHT)){
                    move(1,0);
                }else if (input.equals(KeyCode.UP)){
                    move(0,-1);
                }else if (input.equals(KeyCode.DOWN)){
                    move(0,1);
                }
            }
        });

        grid.requestFocus();

    }

    public void move (int x, int y){
        if (((etat[position_perso[0]+(x*2)] [position_perso[1]+(y*2)] == 2 || etat[position_perso[0]+(x*2)] [position_perso[1]+(y*2)] == 6) && ((etat[position_perso[0]+x] [position_perso[1]+y] == 4) || (etat[position_perso[0]+x] [position_perso[1]+y] == 5))) || (etat[position_perso[0]+x] [position_perso[1]+y] == 2) || etat[position_perso[0]+x] [position_perso[1]+y] == 6) {
                position_perso[0] += x;
                position_perso[1] += y;
                if (etat[position_perso[0]][position_perso[1]] == 4 || (etat[position_perso[0]][position_perso[1]] == 5)) {

                    if(etat[position_perso[0]][position_perso[1]] == 5){
                        nbcibles++;
                    }


                    if (etat[position_perso[0]+x][position_perso[1]+y] == 6){
                        etat[position_perso[0]+x][position_perso[1]+y] = 5;
                        grid.add(new ImageView(image[5]), position_perso[0]+x, position_perso[1]+y);
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
                        etat[position_perso[0]+x][position_perso[1]+y] = 4;
                        grid.add(new ImageView(image[4]), position_perso[0]+x, position_perso[1]+y);

                    }
                }
            etat[position_perso[0]][position_perso[1]] = 3;

            boolean bool = false;

            if (bool == false){
                etat[position_perso[0]+(-1*x)][position_perso[1]+(-1*y)] = 2;
                grid.add(new ImageView(image[2]), position_perso[0]+(-1*x), position_perso[1]+(-1*y));
            }
            grid.add(new ImageView(image[3]), position_perso[0], position_perso[1]);
            for(int i=0; i<position_cible.length-1; i=i+2) {
                if (position_cible[i] == position_perso[0]+(-1*x) && position_cible[i + 1] == position_perso[1]+(-1*y)) {
                    etat[position_perso[0]+(-1*x)][position_perso[1]+(-1*y)] = 6;

                    nbcibles ++;
                    System.out.print(nbcibles);

                    grid.add(new ImageView(image[6]), position_perso[0]+(-1*x), position_perso[1]+(-1*y));
                }
            }
        }
    }

    public static int[] taille_grille(String fichier) {
        String i;
        char [] x;
        int hauteur = 0;
        int largeur = 0;
        int max_largeur = 0;
        int [] grille = new int [2];
        try{
            FileReader f = new FileReader (fichier);
            BufferedReader b = new BufferedReader(f);
            String s = " ";
            while((s= b.readLine())!=null){
                Scanner sc = new Scanner (s);
                hauteur++;
                sc.useDelimiter("\n");
                try {
                    i = sc.next();
                    x = i.toCharArray();
                    largeur = x.length;
                    if (largeur > max_largeur){
                        max_largeur = largeur;
                    }
                }catch(Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        grille[0] = hauteur;
        grille[1] = max_largeur;
        return grille;
    }


    public static int[][] lecture_fichier_xsb (String fichier) {
        String w;
        char [] x;
        int [] taille_grille = taille_grille("src/sample/test.xsb");
        int [][] etat = new int[taille_grille[0]][taille_grille[1]];
        int i = -1;
        try{
            FileReader f = new FileReader (fichier);
            BufferedReader b = new BufferedReader(f);
            String s = " ";
            while((s= b.readLine())!=null){
                i++;
                Scanner sc = new Scanner (s);
                sc.useDelimiter("\n");
                try {
                    w = sc.next();
                    x = w.toCharArray();
                    for (int j = 0; j < x.length; j++){
                        if (x[j] == '#'){
                            etat[i][j] = 0;
                        }
                        if (x[j] == '@'){
                            etat[i][j] = 3;
                            position_perso[0]=j;
                            position_perso[1]=i;
                        }
                        if (x[j] == '$'){
                            etat[i][j] = 4;
                        }
                        if (x[j] == '.'){
                            etat[i][j] = 6;
                        }
                        if (x[j] == ' '){
                            etat[i][j] = 2;
                        }
                    }
                }catch(Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        int [][] retourner = new int [taille_grille[1]][taille_grille[0]];
        for (int a = 0; a < taille_grille[0]; a++){
            for (int b = 0; b < taille_grille[1]; b++){
                retourner [b][a] = etat[a][b];
            }
        }
        return retourner;
    }


    public static int nb_cibles (String fichier) {
        String w;
        int z = 0;
        char [] x;
        int [] taille_grille = taille_grille(fichier);
        int nb_cibles = 0;
        int i = -1;
        try{
            FileReader f = new FileReader (fichier);
            BufferedReader b = new BufferedReader(f);
            String s = " ";
            while((s= b.readLine())!=null){
                i++;
                Scanner sc = new Scanner (s);
                sc.useDelimiter("\n");
                try {
                    w = sc.next();
                    x = w.toCharArray();
                    for (int j = 0; j < x.length; j++){
                        if (x[j] == '.'){
                            nb_cibles++;
                        }
                    }
                }catch(Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        return nb_cibles;
    }


    public static int[] cibles (String fichier) {
        String w;
        int z = 0;
        char [] x;
        int [] taille_grille = taille_grille(fichier);
        int [] cibles = new int [nb_cibles(fichier) * 2];
        int i = -1;
        try{
            FileReader f = new FileReader (fichier);
            BufferedReader b = new BufferedReader(f);
            String s = " ";
            while((s= b.readLine())!=null){
                i++;
                Scanner sc = new Scanner (s);
                sc.useDelimiter("\n");
                try {
                    w = sc.next();
                    x = w.toCharArray();
                    for (int j = 0; j < x.length; j++){
                        if (x[j] == '.'){
                            cibles[z] = j;
                            z++;
                            cibles[z] = i;
                            z++;
                        }
                    }
                }catch(Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        return cibles;
    }



    public static void main(String[] args) {
        launch(args);
    }
}