package Controller;

import Model.Model;

import View.View;

public class Controller {

	private Model model;
	private View view;
	
	public Controller(Model m, View v){
		this.model = m;
		this.view = v;
	}
	
	public void bouge(){
		

	}
	public Model getModel()
	{
		return this.model;
	}
	public View getView()
	{
		return this.view;
	}
	
}
