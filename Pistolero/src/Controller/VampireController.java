package Controller;
import Model.*;
import View.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.concurrent.Callable;

public class VampireController  extends Controller {

	protected VampireView vampireView;
	public Vampire vm;
	
	public VampireController(){
			super();
			Random r = new Random();
			int posx = r.nextInt(600);
			int posy = r.nextInt(500);
			vm = new Vampire(posx, posy);
			this.model = vm;
			vampireView = new VampireView(this);
			this.view =  vampireView;
	}
	
		
	public void bouge(){
		

	}
	public VampireView getView(){
		return this.vampireView;
	}
	
	public static boolean mordre(Vampire vm){
		JoueurController jc= JeuController.jc;
		JoueurView jv = jc.getView();

		double x = vm.getX();
		double y = vm.getY();
		double testx = jv.getX();
		double testy = jv.getY();
		
		if(!JeuController.getTouche()){
			if(vm.mort == false){
					
				if (testx <= x + 32 && testx >= x) {
					if (testy <= y + 32 && testy >= y) {					
						System.out.println("collision vampire");
						Joueur joueur = jv.getJoueur();
						joueur.setSante(joueur.getSante()-100);
						if(joueur.getSante() == 0){
							jv.droite = new Image("demon_mort.png");
							jv.gauche = new Image("demon_mort.png");
							jv.haut = new Image("demon_mort.png");
							jv.bas = new Image("demon_mort.png");
							//System.exit(0);
						}else{
							JeuController.score = JeuController.score-20;	
						}
						JeuController.updateScore();
						JeuController.setTouche(true);
						(new Thread() {
							  public void run() {
								  try {
									sleep(1000);
									JeuController.setTouche(false);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								  }
								 }).start();
			
					}
				}
			}
		}
		return true;
	}
	
	public static boolean vampire(Vampire vm){
		if(!JeuController.collisionVamp){
			double x = vm.getX();
			double y = vm.getY();
			for(int i = 0; i < JeuController.arrayVamp.size(); i++){
				Vampire vamp = JeuController.arrayVamp.get(i).vampireView.getVm();
				VampireView vv= JeuController.arrayVamp.get(i).vampireView;
				if(vamp != vm && vamp.mort == false && vm.mort == false){
					double testx = vamp.getX();
					double testy = vamp.getY();
					if (testx <= x + 32 && testx >= x) {
						if (testy <= y + 32 && testy >= y) {
							int test = (int)(Math.random() * 2);
							System.out.println("test : "+ test);
							JeuController.collisionVamp = true;
							if(test == 0){
								vamp.setVie(0);
								vm.mort = true;
								vv.updateSkin();
								
							}else{
								VampireController tampon = new VampireController();
								JeuController.arrayVamp.add(tampon);
								JeuController.v.getChildren().add(tampon.getView());
							}
							double rand = Math.random()*50-25;
							vm.setX(x + rand);
							vm.setY(y + rand);
							(new Thread() {
								  public void run() {
									  try {
										sleep(1000);
										JeuController.collisionVamp = false;
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									    
									  }
									 }).start();
							
						}
					}
					
				}
				
			}
		}
		
		return true;
	}


	
}
