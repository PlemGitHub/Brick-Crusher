package mech;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import visual.screen.mainPanel.GameField;

public class GameFieldMouse implements MouseMotionListener, MouseListener {
	
	private GameField gameField;
	
	public GameFieldMouse(GameField gameField) {
		this.gameField = gameField;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		gameField.setMousePoint(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gameField.setMousePoint(e.getPoint());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gameField.disableMouseListeners();
		gameField.doShot();
	}

	@Override			
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
