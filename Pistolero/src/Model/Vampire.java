package Model;

public class Vampire extends Model{
	private int x;
	private int y;
	private int vitesse;
	
	public Vampire(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {return x;}
	public void setX(int x) {this.x = x;}

	public int getY() {return y;}
	public void setY(int y) {this.y = y;}

	public int getVitesse() {return vitesse;}
	public void setVitesse(int vitesse) {this.vitesse = vitesse;}


	
}
