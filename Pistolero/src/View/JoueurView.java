package View;

import Controller.JoueurController;
import Model.Joueur;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JoueurView extends View {
    JoueurController jc;
    Joueur jm;
    Rectangle gc;
    public JoueurView(JoueurController jc)
    {
        this.setFocusTraversable(true);
        this.setFocused(true);
        this.jc = jc;
        jm = (Joueur) jc.getModel();
        gc = new Rectangle(jm.getX(),jm.getY(),32,32);
        gc.setFill(Color.BLUE);
        getChildren().add(gc);

    }
    public void update()
    {
        gc.setX(jm.getX());
        gc.setY(jm.getY());
    }
}
