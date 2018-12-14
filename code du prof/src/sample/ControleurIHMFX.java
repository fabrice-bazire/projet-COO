package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;

public class ControleurIHMFX {
    Controleur controleur;
    VueIHMFX vue;

    ControleurIHMFX(Controleur controleur, VueIHMFX vue) {
        this.controleur = controleur;
        this.vue = vue;
        vue.grid.setOnKeyPressed(new MyAction());

    }


    class MyAction implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode input = event.getCode();
            if (input.equals(KeyCode.LEFT)){
                Controleur.getControleur().move(-1, 0);
            }else if (input.equals(KeyCode.RIGHT)){
                Controleur.getControleur().move(1,0);
            }else if (input.equals(KeyCode.UP)){
                Controleur.getControleur().move(0,-1);
            }else if (input.equals(KeyCode.DOWN)){
                Controleur.getControleur().move(0,1);
            }
        }

    }
}

