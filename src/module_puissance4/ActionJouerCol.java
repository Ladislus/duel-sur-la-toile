package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class ActionJouerCol implements EventHandler<ActionEvent> {

    private Puissance4 jeu;

    public ActionJouerCol(Puissance4 puissance4) {
        this.jeu = puissance4;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        int col = (int) source.getUserData();
        int lig = jeu.getPlateau().jouerUnCoup(col);
        this.jeu.majAffichage(lig,col);
    }
}