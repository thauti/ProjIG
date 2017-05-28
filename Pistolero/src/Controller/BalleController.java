package Controller;

import Model.Balle;
import View.BalleView;
import javafx.animation.AnimationTimer;


public class BalleController  extends Controller{
    public int direction;
    public int orgx, orgy;


    public BalleController(int orgx, int orgy, int direction)
    {
        super();
        this.orgx = orgx;
        this.orgy = orgy;

        this.direction = direction;
        this.model = new Balle();
        this.view = new BalleView(this);
    }

    public void update(){
        this.view.update();
    }
}
