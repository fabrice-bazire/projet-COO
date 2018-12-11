package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ModeleLectFichier{

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
}
