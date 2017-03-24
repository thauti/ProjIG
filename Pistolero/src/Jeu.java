import Controller.Controller;
import Model.*;
import View.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Jeu extends Application{

	Model m;
	View v;
	Controller c;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Group root = new Group();
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}

}
