package View;

import Controller.BalleController;
import Controller.JeuController;
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

import java.util.ArrayList;

public class VampireView extends View
{
	Image img1;
	Image img2;
	Image img3;
	ImageView iv;
	VampireController vc;
	Vampire vm;
	ArrayList<BalleController> balleremove;

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
				JeuController.balleliste.remove(balle);
			}
			if (Joueur.x < vm.getX()) {
				vm.setX(vm.getX() - .05);

			}
			if (Joueur.x > vm.getX()) {
				vm.setX(vm.getX() + .05);

			}
			if (Joueur.y < vm.getY()) {
				vm.setY(vm.getY() - .05);

			}
			if (Joueur.y > vm.getY()) {
				vm.setY(vm.getY() + .05);

			}
			iv.setX(vm.getX());
			iv.setY(vm.getY());
		}
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
}