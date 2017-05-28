package Controller;

import View.*;
import Model.*;

import javafx.animation.AnimationTimer;
import javafx.event.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static java.lang.Thread.sleep;

public class JeuController  extends Controller {

	Model m;
	View v;
	VampireController vc;
	MapController mc;
	JoueurController jc;
	Joueur jm;
	int dir;

	public static int score = 0;
	public static Text score_t;



	int shoot = 0;
	int bouge = 0;
	public JeuController() {
		super(null, new JeuView());
		jc = new JoueurController();
		vc = new VampireController();
		mc = new MapController();
		JeuController.score_t = new Text("Score : " + JeuController.score);
		JeuController.score_t.setX(5);
		JeuController.score_t.setY(15);


		getView().getChildren().add(mc.getView());
		getView().getChildren().add(vc.getView());
		getView().getChildren().add(jc.getView());
		getView().getChildren().add(score_t);
		jm = (Joueur) jc.getModel();
		new AnimationTimer() {
			@Override
			public void handle(long now) {

			}
		}.start();

		getView().setOnKeyPressed(
				e -> {
					switch (e.getCode()) {
						case LEFT:
							dir = 3;
							bouge = 1;
							break;
						case RIGHT:
							dir = 2;
							bouge = 1;
							break;
						case UP:
							dir = 1;
							bouge = 1;
							break;
						case DOWN:
							dir = 0;
							bouge = 1;
							break;
						case SPACE:
							if (shoot == 0) {
								shoot = 1;
								getView().getChildren().add(new BalleController(jm.getX(), jm.getY(), jm.getPos()).getView());
							}
							break;
					}
					jc.update();
				}
		);
		getView().setOnKeyReleased(
				event -> {
					switch (event.getCode()) {
						case SPACE:
							shoot = 0;
							break;
						default:
							bouge = 0;
							break;

					}
				}
		);
		new AnimationTimer() {
			@Override
			public void handle(long now) {


				if (bouge == 1) {
						switch (dir) {
						case 0:
							if (jm.getY() < 580) {
								jm.setPos(0);
								jm.setY(jm.getY() + 0.2);
							}
							break;
						case 1:
							if (jm.getY() > 0) {
								jm.setPos(1);
								jm.setY(jm.getY() - 0.2);
							}
							break;
						case 2:
							if (jm.getX() < 780) {
								jm.setPos(2);
								jm.setX(jm.getX() + 0.2);
							}
							break;
						case 3:
							if (jm.getX() > 0) {
								jm.setPos(3);
								jm.setX(jm.getX() - 0.2);
								break;
							}
					}
					jc.update();
				}
			}

			;

		}.start();
	}

	public void bouge(){

	}
	public static void updateScore()
	{
		JeuController.score_t = new Text("Score : "+JeuController.score);

	}
	
}
