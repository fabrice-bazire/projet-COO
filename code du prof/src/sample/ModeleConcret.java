package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class ModeleConcret implements Modele {
    public int[][] etat;
    public int[] position_cible;
    int nb_cibles;
    public int [] position_perso;

    public int[][] getEtat() {
        return etat;
    }


    public ModeleConcret (int [][] tab){
        etat = tab;
        position_perso = personnage(etat);
        nb_cibles = nombre_cibles(etat);
        position_cible = pos_cibles(etat, nb_cibles);
    }

    @Override
    public void setEtat(int[][] tab) {
        this.etat = tab;
        position_perso = personnage(etat);
        nb_cibles = nombre_cibles(etat);
        position_cible = pos_cibles(etat, nb_cibles);
    }

    private int[] personnage (int [][]tab){
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[0].length; j++){
                if (tab[i][j] == 3){
                    return (new int[] {i,j});
                }
            }
        }
        return null;
    }

    private int nombre_cibles (int [][] tab){
        int n = 0;
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[0].length; j++){
                if (tab[i][j] == 6){
                    n++;
                }
            }
        }
        return n;
    }

    private int[] pos_cibles (int [][]tab, int nb_cibles){
        int k = 0;
        System.out.println("nb_cibles : " + nb_cibles);
        int [] cibles = new int [nb_cibles*2];
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[0].length; j++){
                if (tab[i][j] == 6){
                    System.out.println("i : " + i);
                    cibles[k] = i;
                    k++;
                    System.out.println("j : " + j + "\n");
                    cibles[k] = j;
                    k++;
                }
            }
        }
        return null;
    }

    public boolean move (int x, int y){
        if (((etat[position_perso[0]+(x*2)] [position_perso[1]+(y*2)] == 2 || etat[position_perso[0]+(x*2)] [position_perso[1]+(y*2)] == 6) && ((etat[position_perso[0]+x] [position_perso[1]+y] == 4) || (etat[position_perso[0]+x] [position_perso[1]+y] == 5))) || (etat[position_perso[0]+x] [position_perso[1]+y] == 2) || etat[position_perso[0]+x] [position_perso[1]+y] == 6) {
            if(etat[position_perso[0]] [position_perso[1]] == 3) {
                position_perso[0] += x;
                position_perso[1] += y;

                if (etat[position_perso[0]][position_perso[1]] == 4 || (etat[position_perso[0]][position_perso[1]] == 5)) {
                    if (etat[position_perso[0] + x][position_perso[1] + y] == 6) {
                        etat[position_perso[0] + x][position_perso[1] + y] = 5;
                    } else {
                        etat[position_perso[0] + x][position_perso[1] + y] = 4;
                    }
                }
                if (etat[position_perso[0]][position_perso[1]] == 6 || etat[position_perso[0]][position_perso[1]] == 5) {
                    etat[position_perso[0]][position_perso[1]] = 9;
                    etat[position_perso[0] + (-1 * x)][position_perso[1] + (-1 * y)] = 2;
                } else {
                    etat[position_perso[0] + (-1 * x)][position_perso[1] + (-1 * y)] = 2;
                    etat[position_perso[0]][position_perso[1]] = 3;
                }
            }else{
                position_perso[0] += x;
                position_perso[1] += y;

                if (etat[position_perso[0]][position_perso[1]] == 4 || (etat[position_perso[0]][position_perso[1]] == 5)) {
                    if (etat[position_perso[0] + x][position_perso[1] + y] == 6) {
                        etat[position_perso[0] + x][position_perso[1] + y] = 5;
                    } else {
                        etat[position_perso[0] + x][position_perso[1] + y] = 4;
                    }
                }

                if (etat[position_perso[0]][position_perso[1]] == 6 || etat[position_perso[0]][position_perso[1]] == 5) {
                    etat[position_perso[0]][position_perso[1]] = 9;
                    etat[position_perso[0] + (-1 * x)][position_perso[1] + (-1 * y)] = 6;
                } else {
                    etat[position_perso[0] + (-1 * x)][position_perso[1] + (-1 * y)] = 6;
                    etat[position_perso[0]][position_perso[1]] = 3;
                }
            }
        }




        return false; //a modifier
    }

    @Override
    public void reset(int[][] tab) {
        setEtat(tab);
    }
}
