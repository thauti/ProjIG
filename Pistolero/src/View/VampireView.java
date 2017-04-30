package View;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.Rectangle;

public class VampireView extends View
{
	Rectangle gc;
	public VampireView()
	{
		super();
		gc = new Rectangle(50,50,200,200);
		gc.setFill(Color.BLUE);
		System.out.println("Création de la vue vampire");
		getChildren().add(gc);
	}
	public void update()
	{

	}
}