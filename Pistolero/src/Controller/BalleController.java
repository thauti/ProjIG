package Controller;

import Model.Balle;
import View.BalleView;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;


public class BalleController  extends Controller{
    public int direction;
    public double orgx, orgy;

    protected BalleView balleView;
    
    public static IntegerProperty balle;
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
