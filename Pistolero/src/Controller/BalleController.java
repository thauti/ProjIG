package Controller;

import Model.Balle;
import View.BalleView;


public class BalleController  extends Controller{
    public int direction;
    public double orgx, orgy;

    protected BalleView balleView;
    
    public static int balle = 500;
    public BalleController(double orgx, double orgy, int direction)
    {
        super();
        this.orgx = orgx;
        this.orgy = orgy;

        this.direction = direction;
        this.model = new Balle();
        this.balleView = new BalleView(this);
        this.view = balleView;
    }

    public void update(){
        this.view.update();
    }
    public BalleView getView(){
    	return this.balleView;
    }
}
