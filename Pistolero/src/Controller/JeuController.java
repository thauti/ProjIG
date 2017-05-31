package Controller;

import View.*;
import Model.*;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.StringConverter;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class JeuController  extends Controller {

	static int vampireSize = 5;
	Model m;
	static View v;
	static ArrayList<VampireController> arrayVamp;
	MapController mc;
	public static JoueurController jc;
	Joueur jm;
	public static BalleView current;
	int dir;

	public static IntegerProperty score_property;
	public  Text score_t, bestscore_t;
	public Text score_t2;

	public static ArrayList<BalleController> balleliste;
	public Text balle;
	public Text BalleHolder;
	public static boolean collisionVamp;
	boolean rafale = false;
	static boolean touche = false;
	static public boolean partieTerminee = false;
	int shoot = 0;
	int bouge = 0;

	int bestscore;
	public JeuController() {
		super(null, new JeuView());
		jc = new JoueurController();
		arrayVamp = new ArrayList<VampireController>();
		for(int i = 0; i < vampireSize; i++){
			arrayVamp.add(new VampireController());
		}

		bestscore = ScoreController.loadScore();
		mc = new MapController();
		JeuController.balleliste  = new ArrayList<BalleController>();
		JeuController.score_property = new SimpleIntegerProperty(0);
		score_t = new Text();
		score_t.setTextAlignment(TextAlignment.LEFT);
		score_t.setX(80);
		score_t.setY(15);

		bestscore_t = new Text(" Meilleur : "+bestscore);
		bestscore_t.setX(110);
		bestscore_t.setY(15);
		score_t2 = new Text("Score :");
		score_t2.setX(5);
		score_t2.setY(15);

		score_t.textProperty().bind(JeuController.score_property.asString());
		BalleController.balle = new SimpleIntegerProperty(5);
		balle = new Text();
		balle.setX(60);
		balle.setY(45);
		balle.textProperty().bind(BalleController.balle.asString());

		BalleHolder = new Text("Balles : ");
		BalleHolder.setX(5);
		BalleHolder.setY(45);

		getView().getChildren().add(mc.getView());
		for(int i = 0; i < vampireSize; i++){
			getView().getChildren().add(arrayVamp.get(i).getView());
		}
		getView().getChildren().add(jc.getView());
		getView().getChildren().add(score_t);
		getView().getChildren().add(score_t2);
		getView().getChildren().add(balle);
		getView().getChildren().add(BalleHolder);
		getView().getChildren().add(bestscore_t);
		v = getView();
		jm = (Joueur) jc.getModel();
		this.collisionVamp = false;
		


		getView().setOnKeyPressed(

				e -> {
					bouge = 1;
					switch (e.getCode()) {
						case LEFT:
							dir = 3;
							break;
						case RIGHT:
							dir = 2;
							break;
						case UP:
							dir = 1;
							break;
						case DOWN:
							dir = 0;
							break;
						case SPACE:
							bouge =0;
							if(BalleController.balle.getValue() > 0) {
								BalleController bc;
								if(rafale){
									bc = new BalleController(jm.getX(), jm.getY(), jm.getPos());
									JeuController.balleliste.add(bc);

									current = bc.getView();
									getView().getChildren().add(bc.getView());
									BalleController.balle.setValue(BalleController.balle.getValue()-1);
									
									if(BalleController.balle.getValue() == 0)
									{
										Thread t = new Thread(new ThreadBalles());
										t.start();
									}
								}else if (shoot == 0) {
									if(!partieTerminee) {
										shoot = 1;
										bc = new BalleController(jm.getX(), jm.getY(), jm.getPos());
										JeuController.balleliste.add(bc);

										current = bc.getView();
										getView().getChildren().add(bc.getView());
										BalleController.balle.setValue(BalleController.balle.getValue() - 1);

										if (BalleController.balle.getValue() == 0) {
											Thread t = new Thread(new ThreadBalles());
											t.start();
										}
									}
								}
								
							}
							break;
						case A:
							bouge = 0;
							System.out.println("rafale = : "+rafale);
							rafale = !rafale;
							System.out.println("rafale = : "+rafale);
							break;

					}
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

				if(partieTerminee)
				{
					gameOver();
				}
				boolean vivant = false;
				for(VampireController v: arrayVamp)
				{
					Vampire m = (Vampire) v.getModel();
					if(!m.isMort())
					{
						vivant = true;
					}
				}
				if(!vivant)
				{
					partieTerminee = true;
				}
				if(jm.getSante().getValue() == 0)
				{
					jc.getView().setImgViewDead();
					partieTerminee = true;
				}
				if (bouge == 1) {
						switch (dir) {
						case 0:
							if (jm.getY() < 580) {
								jm.setPos(0);
								//jm.setY(jm.getY() + 0.2);
								jm.setY(jm.getY() + 5);
							}
							break;
						case 1:
							if (jm.getY() > 0) {
								jm.setPos(1);
								//jm.setY(jm.getY() - 0.2);
								jm.setY(jm.getY() - 5);
							}
							break;
						case 2:
							if (jm.getX() < 780) {
								jm.setPos(2);
								//jm.setX(jm.getX() + 0.2);
								jm.setX(jm.getX() + 5);
							}
							break;
						case 3:
							if (jm.getX() > 0) {
								jm.setPos(3);
								//jm.setX(jm.getX() - 0.2);
								jm.setX(jm.getX() - 5);

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

	public void gameOver(){
		//System.exit(0);

		Text tv = new Text("Game Over");
		tv.setFont(Font.font(35));

		tv.setFill(Color.WHITE);
		if(bestscore < score_property.getValue()) {
			Text ms = new Text("Nouveau meilleur score");
			ms.setFont(Font.font(20));
			ms.setFill(Color.WHITE);
			ms.setX(295);
			ms.setY(250);
			getView().getChildren().add(ms);
			ScoreController.saveScore(score_property.getValue());
		}
		tv.setX(300);
		tv.setY(200);
		getView().getChildren().add(tv);

		//try {
		//	sleep(2000);
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}
	}

	public static boolean getTouche(){
		return touche;
	}
	public static void setTouche(boolean t){
		touche = t;
	}
	
}
