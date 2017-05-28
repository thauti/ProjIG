package Controller;

import Model.Balle;
import View.BalleView;


public class BalleController  extends Controller{
    public int direction;
    public double orgx, orgy;

    public static int balle = 5;
    public BalleController(double orgx, double orgy, int direction)
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
