package Controller;

import View.*;
import Model.*;

public class JeuController  extends Controller {

	Model m;
	View v;
	VampireController vc;

	public JeuController(){
		super(null,new JeuView());
		vc  = new VampireController();
		getView().getChildren().add(vc.getView());
	}
	public void bouge(){

	}
	
	
}
