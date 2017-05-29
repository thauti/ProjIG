package Controller;
import Model.*;
import View.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;

import java.util.Random;
import java.util.concurrent.Callable;

public class VampireController  extends Controller {

	protected VampireView vampireView;
	
	public VampireController(){
			super();
			Random r = new Random();
			int posx = r.nextInt(600);
			int posy = r.nextInt(500);
			this.model = new Vampire(posx,posy);
			vampireView = new VampireView(this);
			this.view =  vampireView;
	}
	
		
	public void bouge(){
		

	}
	public VampireView getView(){
		return this.vampireView;
	}
	
}
