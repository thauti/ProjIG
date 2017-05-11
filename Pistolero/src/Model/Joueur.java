package Model;

public class Joueur extends Model {
    private int x;
    private int y;
    private int santé = 100;
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
}
