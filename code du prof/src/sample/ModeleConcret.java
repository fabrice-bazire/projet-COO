package sample;

public class ModeleConcret implements Modele {
    public int[][] etat = {{0}};

    public int[][] getEtat() {
        return etat;
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

    @Override
    public void reset() {
    }
}
