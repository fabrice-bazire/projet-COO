package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class ModeleConcret implements Modele {
    public int[][] etat;

    public int [] position_perso;

    public int[][] getEtat() {
        return etat;
    }


    public ModeleConcret (int [][] tab){
        etat = tab;
        position_perso = personnage(etat);
    }

    @Override
    public void setEtat(int[][] tab) {
        this.etat = tab;
        position_perso = personnage(etat);
    }

    private int[] personnage (int [][]etat){
        for (int i = 0; i < etat.length; i++){
            for (int j = 0; j < etat[0].length; j++){
                if (etat[i][j] == 3){
                    return (new int[] {i,j});
                }
            }
        }
        return null;
    }


    public boolean move (int x, int y){
        if (((etat[position_perso[0]+(x*2)] [position_perso[1]+(y*2)] == 2 || etat[position_perso[0]+(x*2)] [position_perso[1]+(y*2)] == 6) && ((etat[position_perso[0]+x] [position_perso[1]+y] == 4) || (etat[position_perso[0]+x] [position_perso[1]+y] == 5))) || (etat[position_perso[0]+x] [position_perso[1]+y] == 2) || etat[position_perso[0]+x] [position_perso[1]+y] == 6) {
            position_perso[0] += x;
            position_perso[1] += y;
            if (etat[position_perso[0]][position_perso[1]] == 4 || (etat[position_perso[0]][position_perso[1]] == 5)) {
                if (etat[position_perso[0]+x][position_perso[1]+y] == 6){
                    etat[position_perso[0]+x][position_perso[1]+y] = 5;
                }
                else{
                    etat[position_perso[0]+x][position_perso[1]+y] = 4;
                }
            }
            etat[position_perso[0]][position_perso[1]] = 3;

            boolean bool = false;

            if (bool == false){
                etat[position_perso[0]+(-1*x)][position_perso[1]+(-1*y)] = 2;
            }
            /*for(int i=0; i<position_cible.length-1; i=i+2) {
                if (position_cible[i] == position_perso[0]+(-1*x) && position_cible[i + 1] == position_perso[1]+(-1*y)) {
                    etat[position_perso[0]+(-1*x)][position_perso[1]+(-1*y)] = 6;}
            }*/
        }
        return false; //a modifier
    }

    @Override
    public void reset() {

    }
}
