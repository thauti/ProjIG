package View;

import Controller.JeuController;
import Controller.VampireController;
import Model.Joueur;
import Model.Vampire;
import javafx.animation.AnimationTimer;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.Rectangle;

public class VampireView extends View
{
	Image img1;
	Image img2;
	Image img3;
	ImageView iv;
	VampireController vc;
	Vampire vm;
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
	public void update()
	{

		if(Joueur.x < vm.getX())
		{
			//vm.setX(vm.getX()-.05);
			vm.setX(vm.getX()-.20);

		}
		if(Joueur.x > vm.getX())
		{
			//vm.setX(vm.getX()+.05);
			vm.setX(vm.getX()+.20);

		}
		 if(Joueur.y < vm.getY())
		{
			//vm.setY(vm.getY()-.05);
			 vm.setY(vm.getY()-.20);

		}
		if(Joueur.y > vm.getY())
		{
			//vm.setY(vm.getY()+.05);
			vm.setY(vm.getY()+.20);

		}
		iv.setX(vm.getX());
		iv.setY(vm.getY());
	}
}