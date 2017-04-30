import Controller.*;
import Model.*;
import View.*;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Jeu extends Application{


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		JeuController jc = new JeuController();
		primaryStage.setScene(new Scene(jc.getView(), 800, 600));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
