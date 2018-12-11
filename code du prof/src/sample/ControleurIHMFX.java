package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

import java.security.Key;

public class ControleurIHMFX {
    Controleur controleur;
    VueIHMFX vue;

    ControleurIHMFX(Controleur controleur, VueIHMFX vue) {
        this.controleur = controleur;
        this.vue = vue;
    }

    class MyAction implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode input = event.getCode();

            if (input.equals(KeyCode.LEFT)){
                controleur.move(-1, 0);
            }else if (input.equals(KeyCode.RIGHT)){
                controleur.move(1, 0);
            }else if (input.equals(KeyCode.UP)){
                controleur.move(0, -1);
            }else if (input.equals(KeyCode.DOWN)){
                controleur.move(0, 1);
            }
        }
    }
}