import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class grille extends Application {

    Image mur = new Image(new FileInputStream("mur sokoban.jpg"),80,80,false,false);
    Image ext = new Image(new FileInputStream("exterieur sokoban.jpg"), 80, 80, false, false);
    Image inside = new Image(new FileInputStream("interieur sokoban.jpg"), 80, 80, false, false);         

    Image[] image = {mur,ext,inside};

    public grille() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

         int[][] etat = {{2,2,2,2,2,2,2},{2,0,0,0,0,0,2}, {2,0,1,1,1,0,2}, {2,0,1,1,1,0,2}, {2,0,1,1,1,0,2}, {2,0,0,0,0,0,2}, {2,2,2,2,2,2,2}};

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < etat.length; i++){
             for (int j = 0; j < etat[0].length; j++){
		        grid.add(new ImageView(image[etat[i][j]]),i,j);
		    }
		}

        Button btn2 = new Button("Fermer");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 8, 8);

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event2) {
                primaryStage.close();
            }
        });

        primaryStage.setScene(new Scene(grid, 960, 700));
        primaryStage.setTitle("Sokoban");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}