package Controller;

import View.*;
import Model.*;

import javafx.event.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class JeuController  extends Controller {

	Model m;
	View v;
	VampireController vc;
	MapController mc;
	JoueurController jc;
	Joueur jm;
	public JeuController(){
		super(null,new JeuView());
		jc = new JoueurController();
		vc  = new VampireController();
		mc = new MapController();
		getView().getChildren().add(mc.getView());
		getView().getChildren().add(vc.getView());
		getView().getChildren().add(jc.getView());
		jm=(Joueur)jc.getModel();
		getView().addEventHandler(KeyEvent.KEY_PRESSED,
				e -> {
					switch(e.getCode())
					{
						case LEFT:
							jm.setX(jm.getX()-5);
							break;
						case RIGHT:
							jm.setX(jm.getX()+5);
							break;
						case UP:
							jm.setY(jm.getY()-5);
							break;
						case DOWN:
							jm.setY(jm.getY()+5);
							break;

					}
			jc.update();
		}
		);
	}
	public void bouge(){

	}
	
	
}
