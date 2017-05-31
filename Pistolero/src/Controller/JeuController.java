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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
	public  Text score_t;
	public Text score_t2;

	public static ArrayList<BalleController> balleliste;
	public Text balle;
	
	public static boolean collisionVamp;
	boolean rafale = false;
	static boolean touche = false;
	int shoot = 0;
	int bouge = 0;
	public JeuController() {
		super(null, new JeuView());
		jc = new JoueurController();
		arrayVamp = new ArrayList<VampireController>();
		for(int i = 0; i < vampireSize; i++){
			arrayVamp.add(new VampireController());
		}

		mc = new MapController();
		JeuController.balleliste  = new ArrayList<BalleController>();
		JeuController.score_property = new SimpleIntegerProperty(0);
		score_t = new Text();
		score_t.setX(80);
		score_t.setY(15);

		score_t2 = new Text("Score :");
		score_t2.setX(5);
		score_t2.setY(15);

		score_t.textProperty().bind(JeuController.score_property.asString());
		BalleController.balle = new SimpleIntegerProperty(5);
		balle = new Text();
		balle.setX(5);
		balle.setY(45);
		balle.textProperty().bind(BalleController.balle.asString());
		getView().getChildren().add(mc.getView());
		for(int i = 0; i < vampireSize; i++){
			getView().getChildren().add(arrayVamp.get(i).getView());
		}
		getView().getChildren().add(jc.getView());
		getView().getChildren().add(score_t);
		getView().getChildren().add(score_t2);
		getView().getChildren().add(balle);

		v = getView();
		jm = (Joueur) jc.getModel();
		this.collisionVamp = false;
		


		getView().setOnKeyPressed(

				e -> {
					bouge = 1;
					switch (e.getCode()) {
						case LEFT:
							dir = 3;
							mordu();
							break;
						case RIGHT:
							dir = 2;
							mordu();
							break;
						case UP:
							dir = 1;
							mordu();
							break;
						case DOWN:
							dir = 0;
							mordu();
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
									shoot = 1;
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
	public boolean mordu(){/*
		if(!touche){
			for(int i = 0; i < arrayVamp.size(); i++){
				double x = arrayVamp.get(i).getView().vm.getX();
				double y = arrayVamp.get(i).getView().vm.getY();
				double testx = jc.jv.getX();
				double testy = jc.jv.getY();
				if(arrayVamp.get(i).vm.mort == false){	
					if (testx <= x + 32 && testx >= x) {
						if (testy <= y + 32 && testy >= y) {
							System.out.println("collision joueur");
							Joueur joueur = jc.jv.getJoueur();
							joueur.setSante(joueur.getSante()-100);
							if(joueur.getSante() == 0){
								jc.jv.droite = new Image("demon_mort.png");
								jc.jv.gauche = new Image("demon_mort.png");
								jc.jv.haut = new Image("demon_mort.png");
								jc.jv.bas = new Image("demon_mort.png");
								//System.exit(0);
							}else{
								if(JeuController.score_property.getValue()-20 >= 0)
									JeuController.score_property.setValue(JeuController.score_property.getValue()-20);
								else
									JeuController.score_property.setValue(0);

							}
							touche = true;
							(new Thread() {
								  public void run() {
									  try {
										sleep(3);
										touche = false;
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									    
									  }
									 }).start();
						}
					}
				}
			}
			
		}
		return true;
		*/
		return false;
	}

	public static void gameOver(){
		System.exit(0);
		Text tv = new Text("Game Over");
		tv.setFont(Font.font(25));
		v.getChildren().add(tv);
	}
	public static boolean getTouche(){
		return touche;
	}
	public static void setTouche(boolean t){
		touche = t;
	}
	
}
