package View;

import Controller.BalleController;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BalleView extends View {
    BalleController bc;
    int dir;
    double orgx, orgy;
    Circle c;
    double x, y;
    public BalleView(BalleController balleController) {
        super();
        bc = balleController;
        dir = balleController.direction;
        orgx = balleController.orgx;
        orgy = balleController.orgy;
        x = orgx+16;
        y = orgy+16;
        c = new Circle(x,y,3);
        c.setFill(Color.BLACK);

        System.out.print(x);
        getChildren().add(c);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (dir)
                {
                    case 0:
                        y =y +1;
                        c.setCenterY(y);
                        break;
                    case 1:
                        y=y-1;
                        c.setCenterY(y);
                        break;
                    case 2:
                        x=x+1;
                        c.setCenterX(x);
                        break;
                    case 3:
                        x=x-1;
                        c.setCenterX(x);
                        break;
                }
            }
        }.start();
    }
}
