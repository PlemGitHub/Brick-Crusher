package visual.screen.mainPanel;

import java.awt.Color;
import javax.swing.JPanel;
import mech.Constants;
import visual.screen.MainPanel;
import visual.screen.mainPanel.buttonsPanel.MyButton;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel implements Constants{
	
	private MyButton startBtn;
	
	public ButtonsPanel(MainPanel mainPanel) {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, BUTTON_WIDTH, WINDOW_HEIGHT);
		
		createButtons(mainPanel);
	}

	private void createButtons(MainPanel mainPanel) {
		startBtn = new MyButton(0, mainPanel);
		startBtn.setText("Start");
		add(startBtn);
	}
	
	
}
