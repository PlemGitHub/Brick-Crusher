package visual.screen.mainPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import mech.Constants;
import mech.GameFieldMouse;
import visual.screen.mainPanel.gameField.PaintLayer;

@SuppressWarnings("serial")
public class GameField extends JPanel implements Constants{
	
	private GameFieldMouse gameFieldMouse = new GameFieldMouse(this);
	private PaintLayer paintLayer;
	
	public GameField() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(GAMEFIELD_X, 0, GAMEFIELD_WIDTH, GAMEFIELD_HEIGHT);
		enableMouseListeners();
		createPaintLayer();
	}
	
	public void enableMouseListeners(){
		addMouseListener(gameFieldMouse);
		addMouseMotionListener(gameFieldMouse);
		if (getMousePosition() != null)
			paintLayer.setMousePoint(getMousePosition());
	}
	
	public void disableMouseListeners(){
		removeMouseListener(gameFieldMouse);
		removeMouseMotionListener(gameFieldMouse);
	}

	private void createPaintLayer() {
		paintLayer = new PaintLayer(this);
		add(paintLayer);
	}

	public void setMousePoint(Point p){
		paintLayer.setMousePoint(p);
	}
	
	public void doShot(){
		paintLayer.doShot();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
}
