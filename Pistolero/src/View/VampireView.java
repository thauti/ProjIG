package View;

import Controller.VampireController;
import Model.Vampire;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.Rectangle;

public class VampireView extends View
{
	Rectangle gc;
	VampireController vc;
	Vampire vm;
	public VampireView(VampireController c)
	{
		super();
		this.vc = c;
		this.vm = (Vampire) c.getModel();
		gc = new Rectangle(vm.getX(),vm.getY(),32,32);
		gc.setFill(Color.RED);
		System.out.println("Création de la vue vampire");
		getChildren().add(gc);
	}
	public void update()
	{

	}
}