package View;

import Controller.JoueurController;
import Model.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class JoueurView extends View {
    JoueurController jc;
    Joueur jm;
    public Image droite, gauche, haut, bas;
    ImageView cr;

    Text vie;
    public JoueurView(JoueurController jc)
    {
        this.setFocusTraversable(true);
        this.setFocused(true);
        this.jc = jc;
        jm = (Joueur) jc.getModel();

        vie = new Text();
        vie.textProperty().bind(jm.getSante().asString());
        vie.setX(5);
        vie.setY(30);
        droite = new Image("soldat_droite.png");
        if(droite.isError())
        {
            System.out.println("Erreur au chargement de l'image "+droite.getException().toString() );
        }
        gauche = new Image("soldat_gauche.png");
        if(gauche.isError())
        {
            System.out.println("Erreur au chargement de l'image "+gauche.getException().toString() );
        }
        haut = new Image("soldat_haut.png");
        if(haut.isError())
        {
            System.out.println("Erreur au chargement de l'image "+haut.getException().toString() );
        }
        bas = new Image("soldat_bas.png");
        if(bas.isError())
        {
            System.out.println("Erreur au chargement de l'image "+bas.getException().toString() );
        }

        cr = new ImageView(bas);
        cr.setX(jm.getX());
        cr.setY(jm.getY());
        getChildren().add(cr);
        getChildren().add(vie);
    }
    public void update()
    {
        switch(jm.getPos())
        {
            case 0: cr.setImage(bas);
                break;
            case 1: cr.setImage(haut);
                break;
            case 2: cr.setImage(droite);
                break;
            case 3: cr.setImage(gauche);
                break;
        }
        cr.setX(jm.getX());
        cr.setY(jm.getY());
    }
    public double getX(){
    	return cr.getX();
    }
    public double getY(){
    	return cr.getY();
    }
    public Joueur getJoueur(){
    	return jm;
    }
}
