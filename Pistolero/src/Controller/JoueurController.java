package Controller;

import Model.Joueur;
import View.JoueurView;

public class JoueurController extends Controller {

    public JoueurController()
    {
        super();
        this.model = new Joueur();
        this.view = new JoueurView(this);
    }
    public void update(){
        this.view.update();
    }
}
