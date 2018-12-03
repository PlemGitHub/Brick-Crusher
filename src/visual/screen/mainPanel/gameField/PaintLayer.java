package visual.screen.mainPanel.gameField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;
import mech.BulletCalculation;
import mech.Constants;
import mech.LineCalculation;
import visual.screen.mainPanel.GameField;

@SuppressWarnings("serial")
public class PaintLayer extends JPanel implements Constants{
	
	private LineCalculation lineCalculation;
	private BulletCalculation bulletCalculation = new BulletCalculation(this);
	private GameField gameField;
	private ArrayList<Rectangle> bricks = new ArrayList<>();
	private boolean bulletsFired;

	public PaintLayer(GameField gameField) {
		this.gameField = gameField;
		setOpaque(false);
		setSize(gameField.getWidth(), gameField.getHeight());		
		setUpStartBricks();
		lineCalculation = new LineCalculation(this);
	}

	private void setUpStartBricks() {
		boolean match;
		int x = 0;
		int y = 0;
		for (int i = 0; i < START_BRICKS_Q; i++) {
			do {
				match = false;
				x = (int)(Math.random()*START_BRICKS_Q)*BRICK_WIDTH;
				y = (int)(Math.random()*START_BRICKS_Q)*BRICK_HEIGHT;
				for (Rectangle r : bricks) {
					if (r.getX() == x && r.getY() == y)
						match = true;
				}
			} while (match);
			bricks.add(new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT));
		}
	}
	
		public ArrayList<Rectangle> getBricks(){
			return bricks;
		}

	public void setMousePoint(Point p){
		lineCalculation.setMousePoint(p);
		repaint();
	}

	public void doShot() {
		hideLine();
		bulletCalculation.doShot();
	}
	
	public LineCalculation getLineCalculation(){
		return lineCalculation;
	}

	private void hideLine() {
		bulletsFired = true;
		repaint();
	}
	
	public void showLine() {
		bulletsFired = false;
		repaint();
	}
	
	public void enableMouseListenersAtGamefield(){
		gameField.enableMouseListeners();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (!bulletsFired){
			g.setColor(Color.ORANGE);
			g.drawLine(	(int)lineCalculation.getX1(), (int)lineCalculation.getY1(),
						(int)lineCalculation.getX2(), (int)lineCalculation.getY2());
		}

		for (Rectangle r : bricks) {
			g.setColor(Color.YELLOW);
			g.fillRect(r.x, r.y, r.width, r.height);
			g.setColor(Color.BLACK);
			g.drawRect(r.x, r.y, r.width, r.height);
		}		
		
		for (Rectangle r : bulletCalculation.getBullets()) {
			g.setColor(Color.RED);
			g.fillRect(r.x, r.y, r.width, r.height);
		}
	}
}
