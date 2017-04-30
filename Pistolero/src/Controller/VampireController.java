package Controller;
import Model.*;
import View.*;

public class VampireController  extends Controller {

	Model m;
	View v;
	public VampireController(){
			super(new Vampire(0,0,0,0), new VampireView());
	}
	
	public void bouge(){
		

	}
	
	
}
