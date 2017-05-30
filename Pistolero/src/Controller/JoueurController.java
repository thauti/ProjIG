package Controller;

import Model.Joueur;
import View.JoueurView;

public class JoueurController extends Controller {
	JoueurView jv;
	
    public JoueurController()
    {
        super();
        this.model = new Joueur();
        jv = new JoueurView(this);
        this.view = jv;
    }
    public void update(){
        this.view.update();
    }
    public JoueurView getView(){
    	return jv;
    }
}
