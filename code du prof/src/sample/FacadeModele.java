package sample;

public class FacadeModele {
    Modele modele = new ModeleConcret(ModeleLectFichier.lecture_fichier_xsb("src/sample/test.xsb"));

    public void move(int x, int y) {
        modele.move(x,y);
    }

    public void reset() {
        modele.reset(ModeleLectFichier.lecture_fichier_xsb("src/sample/test.xsb"));
    }

    public int[][] getEtat() {
        return modele.getEtat();
    }

}
