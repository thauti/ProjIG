package View;

import Controller.BalleController;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BalleView extends View {
    BalleController bc;
    int dir;
    int orgx, orgy;
    Circle c;
    int x, y;
    public BalleView(BalleController balleController) {
        super();
        bc = balleController;
        dir = balleController.direction;
        orgx = balleController.orgx;
        orgy = balleController.orgy;
        x = orgx+16;
        y = orgy+16;
        c = new Circle(x,y,10);
        c.setFill(Color.RED);

        System.out.print(x);
        getChildren().add(c);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (dir)
                {
                    case 0:
                        c.setCenterY(y-1);
                        break;
                    case 1:
                        c.setCenterY(y+1);
                        break;
                    case 2:
                        c.setCenterX(x-1);
                        break;
                    case 3:
                        c.setCenterX(x+1);
                        break;
                }
            }
        }.start();
    }
}
