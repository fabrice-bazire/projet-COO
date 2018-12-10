package sample;

public class FacadeModele {
    Modele modele = new ModeleConcret();

    public void move(int x) {
        modele.move(x);
    }

    public void reset() {
        modele.reset();
    }

    public int[][] getEtat() {
        return modele.getEtat();
    }

}
