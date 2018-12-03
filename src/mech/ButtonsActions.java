package mech;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import visual.screen.MainPanel;

public class ButtonsActions {
	
	private MainPanel mainPanel;
	
	public ButtonsActions(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	@SuppressWarnings("serial")
	private Action startAction = new AbstractAction() {
				@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.createNewGameField();
		}
	};
	
	public Action getAction(int q){
		switch (q) {
		case 0: 
			return startAction;
		case 1:
			break;
		}
		return null;
	}
	
}
