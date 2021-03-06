package visual;

import java.awt.Frame;
import javax.swing.JFrame;

import visual.screen.MainPanel;

public class Screen {
	
	private JFrame fr;
	private MainPanel mp;
	
	public Screen() {
		mp = new MainPanel();
		
		fr = new JFrame("Brick Crusher");
		fr.setContentPane(mp);
		fr.setExtendedState(Frame.MAXIMIZED_BOTH);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Screen scr = new Screen();
	}
}