
public class Vampire {
	private int x;
	private int y;
	private final int sexe;
	private int vitesse;
	
	public Vampire(int x, int y, int sexe, int vitesse){
		this.x = x;
		this.y = y;
		this.sexe = sexe;
		this.vitesse = vitesse;
	}

	public int getX() {return x;}
	public void setX(int x) {this.x = x;}

	public int getY() {return y;}
	public void setY(int y) {this.y = y;}

	public int getVitesse() {return vitesse;}
	public void setVitesse(int vitesse) {this.vitesse = vitesse;}

	public int getSexe() {return sexe;}

	
}
