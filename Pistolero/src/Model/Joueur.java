package Model;

import Controller.BalleController;

public class Joueur extends Model {
    public static double x;
    public static double y;
    private int sante = 300;
    int pos;
    public Joueur()
    {
        x = 200;
        y = 200;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setSante(int sante) {
        this.sante = sante;
    }
    public int getSante() {
     return sante;
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
