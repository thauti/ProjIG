package View;

import Controller.BalleController;
import Controller.JeuController;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BalleView extends View {
    BalleController bc;
    int dir;
    double orgx, orgy;
    Circle c;
    double x, y;
    boolean elimine=false;
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

        //System.out.print(x);
        getChildren().add(c);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(elimine==false) {

                    if (c.getCenterX() > 800 || c.getCenterX() < 0) {
                        getChildren().remove(c);
                        JeuController.balleliste.remove(bc);
                    }
                    if (c.getCenterY() < 0 || c.getCenterY() > 600) {
                        getChildren().remove(c);
                        JeuController.balleliste.remove(bc);
                    }
                    switch (dir) {
                        case 0:
                            y = y + 5;
                            c.setCenterY(y);
                            break;
                        case 1:
                            y = y - 5;
                            c.setCenterY(y);
                            break;
                        case 2:
                            x = x + 5;
                            c.setCenterX(x);
                            break;
                        case 3:
                            x = x - 5;
                            c.setCenterX(x);
                            break;
                    }
                }
            }
        }.start();
    }
    public void detruire()
    {
        elimine =true;
        getChildren().remove(c);
        JeuController.balleliste.remove(bc);
    }
}
