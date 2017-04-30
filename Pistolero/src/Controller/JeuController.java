package Controller;

import View.*;
import Model.*;

public class JeuController  extends Controller {

	Model m;
	View v;
	VampireController vc;
	MapController mc;
	public JeuController(){
		super(null,new JeuView());
		vc  = new VampireController();
		mc = new MapController();
		getView().getChildren().add(mc.getView());
		//getView().getChildren().add(vc.getView());
	}
	public void bouge(){

	}
	
	
}
