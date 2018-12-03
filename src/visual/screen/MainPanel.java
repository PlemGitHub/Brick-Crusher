package visual.screen;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import mech.Constants;
import visual.screen.mainPanel.ButtonsPanel;
import visual.screen.mainPanel.GameField;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Constants{
	private final String exitStr = "exitStr";
	
	private ButtonsPanel buttonsPanel;
	private GameField gameField;
	
	public MainPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
				
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), exitStr);
		getActionMap().put(exitStr, exitAction);
		
		createButtonsPanel();
		createNewGameField();
	}

	private void createButtonsPanel() {
		buttonsPanel = new ButtonsPanel(this);
		add(buttonsPanel);
	}

	public void createNewGameField() {
		for (Component c : getComponents()) {
			if (c instanceof GameField)
				remove(c);
		}
		gameField = new GameField();
		add(gameField);
		repaint();
	}

	private Action exitAction = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
}