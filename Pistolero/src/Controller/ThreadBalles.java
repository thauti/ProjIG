package Controller;

import static java.lang.Thread.sleep;

/**
 * Created by thom on 28/05/17.
 */
public class ThreadBalles implements Runnable {
    @Override
    public void run() {
        try {
            sleep(5000);
            BalleController.balle = 5;
            JeuController.updateBalle();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
