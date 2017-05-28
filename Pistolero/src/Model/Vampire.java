package Model;

public class Vampire extends Model{
	private double x;
	private double y;
	private double vitesse;
	int vie = 3;
	
	public Vampire(int x, int y){
		this.x = x;
		this.y = y;
	}

	public double getX() {return x;}
	public void setX(double x) {this.x = x;}

	public void setVie(int v){this.vie = v;}
	public int getVie(){return this.vie;}

	public double getY() {return y;}
	public void setY(double y) {this.y = y;}

	public double getVitesse() {return vitesse;}
	public void setVitesse(double vitesse) {this.vitesse = vitesse;}


	
}
