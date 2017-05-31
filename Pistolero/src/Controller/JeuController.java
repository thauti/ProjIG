package Controller;

import View.*;
import Model.*;

import javafx.animation.AnimationTimer;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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

	public static int score = 0;
	public static Text score_t;

	public static ArrayList<BalleController> balleliste;
	public static Text balle;
	
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
		JeuController.score_t = new Text("Score : " + JeuController.score);
		JeuController.score_t.setX(5);
		JeuController.score_t.setY(15);

		JeuController.balle = new Text(5,35,"Balles : "+ BalleController.balle);
		getView().getChildren().add(mc.getView());
		for(int i = 0; i < vampireSize; i++){
			getView().getChildren().add(arrayVamp.get(i).getView());
		}
		getView().getChildren().add(jc.getView());
		getView().getChildren().add(score_t);
		getView().getChildren().add(balle);
		v = getView();
		jm = (Joueur) jc.getModel();
		this.collisionVamp = false;
		

		new AnimationTimer() {
			@Override
			public void handle(long now) {

			}
		}.start();

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
							if(BalleController.balle > 0) {
								BalleController bc;
								if(rafale){
									bc = new BalleController(jm.getX(), jm.getY(), jm.getPos());
									JeuController.balleliste.add(bc);

									current = bc.getView();
									getView().getChildren().add(bc.getView());
									BalleController.balle--;
									
									JeuController.updateBalle();
									if(BalleController.balle == 0)
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
									BalleController.balle--;
									
									JeuController.updateBalle();
									if(BalleController.balle == 0)
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
						case R:
							bouge = 0;
							BalleController.balle = 10;
							JeuController.updateBalle();
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
	public boolean mordu(){
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
								JeuController.score = JeuController.score-20;	
							}
							JeuController.updateScore();
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
	}

	public static void updateScore()
	{
		JeuController.score_t.setText("Score : "+JeuController.score);

	}
	public static void updateBalle()
	{
		JeuController.balle.setText("Balles : "+ BalleController.balle);

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
