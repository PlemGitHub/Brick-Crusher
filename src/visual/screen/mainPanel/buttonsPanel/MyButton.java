package visual.screen.mainPanel.buttonsPanel;

import javax.swing.JButton;

import mech.ButtonsActions;
import mech.Constants;
import visual.screen.MainPanel;

@SuppressWarnings("serial")
public class MyButton extends JButton implements Constants{
	
	private ButtonsActions buttonsActions;

	public MyButton(int q, MainPanel mainPanel) {
		buttonsActions = new ButtonsActions(mainPanel);
		setBounds(0, q*BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		setAction(buttonsActions.getAction(q));
		setFocusable(false);
	}
}
