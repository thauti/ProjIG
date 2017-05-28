package Model;

import Controller.BalleController;

public class Joueur extends Model {
    private int x;
    private int y;
    private int santé = 100;
    int pos;
    public Joueur()
    {
        x = 200;
        y = 200;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setSanté(int santé) {
        this.santé = santé;
    }
    public int getPos()
    {
        return this.pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }

    public void tirer(int x, int y, int d) {
        new BalleController(x, y, d);
    }
}
