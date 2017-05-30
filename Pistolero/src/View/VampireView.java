package View;

import Controller.BalleController;
import Controller.JeuController;
import Controller.JoueurController;
import Controller.VampireController;
import Model.Balle;
import Model.Joueur;
import Model.Vampire;
import javafx.animation.AnimationTimer;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class VampireView extends View
{
	Image img1;
	Image img2;
	Image img3;
	ImageView iv;
	VampireController vc;
	public Vampire vm;
	ArrayList<BalleController> balleremove;
	double vitesse;

	boolean touche = false;
	public VampireView(VampireController c)
	{
		super();
		this.vc = c;
		this.vm = (Vampire) c.getModel();
		img1 = new Image("demon_vert.png");
		img2 = new Image("demon_rouge.png");
		img3 = new Image("demon_mort.png");

		iv = new ImageView(img1);
		iv.setX(vm.getX());
		iv.setY(vm.getY());
		System.out.println("Creation de la vue vampire");
		getChildren().add(iv);
		vitesse = Math.random();
		
		new AnimationTimer()
		{

			@Override
			public void handle(long now) {
				update();
				
			}
		}.start();
	}
	public void update() {
		if (vm.getVie() != 0) {
			BalleController balle = null;
			for (BalleController b : JeuController.balleliste) {
				BalleView v = b.getView();

				double x = vm.getX();
				double y = vm.getY();
				if (v.x < x + 32 && v.x > x) {
					if (v.y < y + 32 && v.y > y) {

						vm.setVie(vm.getVie() - 1);
						updateSkin();
						JeuController.score = JeuController.score+10;
						JeuController.updateScore();
						balle = b;
					}
				}
			}
			if (balle != null) {
				balle.getView().detruire();
			}
			if (Joueur.x < vm.getX()) {
				//vm.setX(vm.getX() - 0.5);
				vm.setX(vm.getX() - vitesse);
				mordre();
			}
			if (Joueur.x > vm.getX()) {
				//vm.setX(vm.getX() + 0.5);
				vm.setX(vm.getX() + vitesse);
				mordre();
			}
			if (Joueur.y < vm.getY()) {
				//vm.setY(vm.getY() - 0.5);
				vm.setY(vm.getY() - vitesse);
				mordre();
			}
			if (Joueur.y > vm.getY()) {
				//vm.setY(vm.getY() + 0.5);
				vm.setY(vm.getY() + vitesse);
				mordre();
			}
			iv.setX(vm.getX());
			iv.setY(vm.getY());
		}
		JeuController.vampire(vm);
	}
	public boolean mordre(){
		JoueurController jc= JeuController.jc;
		JoueurView jv = jc.getView();

		double x = vm.getX();
		double y = vm.getY();
		double testx = jv.getX();
		double testy = jv.getY();
		
		if(!JeuController.getTouche()){
			if (testx <= x + 32 && testx >= x) {
				if (testy <= y + 32 && testy >= y) {					
					System.out.println("collision vampire");
					Joueur joueur = jv.getJoueur();
					joueur.setSante(joueur.getSante()-100);
					if(joueur.getSante() == 0){
						jv.droite = new Image("demon_mort.png");
						jv.gauche = new Image("demon_mort.png");
						jv.haut = new Image("demon_mort.png");
						jv.bas = new Image("demon_mort.png");
						//System.exit(0);
					}else{
						JeuController.score = JeuController.score-20;	
					}
					JeuController.updateScore();
					JeuController.setTouche(true);
					(new Thread() {
						  public void run() {
							  try {
								sleep(3000);
								JeuController.setTouche(false);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							    
							  }
							 }).start();
		
				}
			}
		}
		return true;
	}
	
	private void updateSkin() {
		System.out.println(vm.getVie());
		if(vm.getVie() <= 0)
		{
			iv.setImage(img3);
		}
		if(vm.getVie() == 1)
		{
			iv.setImage(img2);
		}
		if(vm.getVie() == 2)
		{
			iv.setImage(img2);
		}
		if(vm.getVie() == 3)
		{
			iv.setImage(img1);
		}
	}
	public Vampire getVm(){
		return vm;
	}
}