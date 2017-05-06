package Controller;
import Model.*;
import View.*;

public class VampireController  extends Controller {


	public VampireController(){
			super();
			this.model = new Vampire(0,20);
			this.view =  new VampireView(this);
	}
	
	public void bouge(){
		

	}
	
	
}
