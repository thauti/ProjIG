package Controller;

import View.*;
import Model.*;

import javafx.animation.AnimationTimer;
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
		new AnimationTimer() {
			@Override
			public void handle(long now) {

			}
		}.start();
		getView().setOnKeyPressed(
				e -> {
					switch(e.getCode())
					{
						case LEFT:
							jm.setPos(3);
							jm.setX(jm.getX()-5);
							break;
						case RIGHT:
							jm.setPos(2);
							jm.setX(jm.getX()+5);
							break;
						case UP:
							jm.setPos(1);
							jm.setY(jm.getY()-5);
							break;
						case DOWN:
							jm.setPos(0);
							jm.setY(jm.getY()+5);
							break;
						case SPACE:
							getView().getChildren().add(new BalleController(jm.getX(), jm.getY(), jm.getPos()).getView());
							break;
					}
			jc.update();
		}
		);
	}
	public void bouge(){

	}
	
	
}
