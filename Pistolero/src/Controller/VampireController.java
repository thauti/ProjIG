package Controller;
import Model.*;
import View.*;

import java.util.Random;

public class VampireController  extends Controller {


	public VampireController(){
			super();
			Random r = new Random();
			int posx = r.nextInt(600);
			int posy = r.nextInt(500);
			this.model = new Vampire(posx,posy);
			this.view =  new VampireView(this);
	}
	
	public void bouge(){
		

	}
	
	
}
