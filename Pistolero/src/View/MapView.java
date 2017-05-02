package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MapView extends View{

    GridPane gp;
    Image floor;
    public MapView()
    {
        floor = new Image("sol.png");
        if(floor.isError())
        {
            System.out.println("Erreur au chargement de l'image "+floor.getException().toString() );
        }
        gp = new GridPane();
        for(int i=0;i<25;i++)
        {
            for(int j=0;j<19;j++)
            {
                ImageView iv;
                iv = new ImageView();
                iv.setImage(floor);
                iv.setFitHeight(32);
                iv.setFitWidth(32);
                gp.add(iv, i, j);
             //   System.out.println("Map");
            }
        }

        getChildren().add(gp);
    }
}
