package View;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class VampireView extends View
{
	GraphicsContext gc;
	public VampireView()
	{
		super();
		gc.setFill(Color.BLUE);
		gc.fillRect(0,0,64,64);
	}
	public void update()
	{

	}
}