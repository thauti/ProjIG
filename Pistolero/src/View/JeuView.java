package View;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class JeuView  extends View {

	Rectangle r;
	public JeuView()
	{
		super();
		r = new Rectangle(10,10,100,100);
		r.setFill(Color.RED);
		getChildren().add(r);
	}
	public void update()
	{
		System.out.println("Update");
	}

}

