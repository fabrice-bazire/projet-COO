package sample;

public class ModeleConcret implements Modele {
    public int[][] etat = {{0}};

    public int[][] getEtat() {
        return etat;
    }

    public void move(int indice) {
    }

    @Override
    public void reset() {
    }
}
