package handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainMenu;

public class StartButtonEventHandler implements EventHandler<ActionEvent>{

	private MainMenu container;

	public StartButtonEventHandler(MainMenu mainContainer) {
		
		this.container = mainContainer;
		
	}
	
	public void handle (ActionEvent event) {
		
		this.container.changeButtons();
	}

}
