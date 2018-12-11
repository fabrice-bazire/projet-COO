package sample;

public interface Modele {
    public int[][] getEtat();
    public void setEtat(int [][] tab);
    public boolean move(int x, int y);
    public void reset();
}
