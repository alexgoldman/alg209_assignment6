package edu.pitt.is1017.main;

import edu.pitt.is1017.controller.Controller;
import edu.pitt.is1017.model.Model;
import edu.pitt.is1017.view.View;


public class Main {

	public static void main(String[] args) {
		View frame = new View();
		Model model = new Model();
		Controller controller = new Controller(frame, model);
		controller.getView().fillDB();
		

	}

}
