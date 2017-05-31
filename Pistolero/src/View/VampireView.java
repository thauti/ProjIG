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
		vitesse = (Math.random()+0.5);
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
						JeuController.score_property.set(JeuController.score_property.getValue()+10);
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
				VampireController.mordre(vm);
			}
			if (Joueur.x > vm.getX()) {
				//vm.setX(vm.getX() + 0.5);
				vm.setX(vm.getX() + vitesse);
				VampireController.mordre(vm);
			}
			if (Joueur.y < vm.getY()) {
				//vm.setY(vm.getY() - 0.5);
				vm.setY(vm.getY() - vitesse);
				VampireController.mordre(vm);
			}
			if (Joueur.y > vm.getY()) {
				//vm.setY(vm.getY() + 0.5);
				vm.setY(vm.getY() + vitesse);
				VampireController.mordre(vm);
			}
			iv.setX(vm.getX());
			iv.setY(vm.getY());
		}
		VampireController.vampire(vm);
	}
		
	public void updateSkin() {
		System.out.println(vm.getVie());
		if(vm.getVie() <= 0)
		{
			vc.vm.mort = true;
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
	public ImageView getIV(){
		return iv;
	}
}