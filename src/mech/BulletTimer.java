package mech;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import visual.screen.mainPanel.gameField.PaintLayer;

@SuppressWarnings("serial")
public class BulletTimer extends Timer implements ActionListener, Constants{
	
	private BulletCalculation bulletCalculation;
	private PaintLayer paintLayer;
	private double k, b, x, y, dX, shiftedX, shiftedY;
	private int minX, maxX, minY, maxY, dY;
	private int bulletNumber;
	private boolean finished;
	private boolean nextBulletShooted;

	public BulletTimer(int delay, ActionListener listener, BulletCalculation bulletCalculation) {
		super(delay, listener);
		this.bulletCalculation = bulletCalculation;
		paintLayer = bulletCalculation.getPaintLayer();
		k = paintLayer.getLineCalculation().getK();
		b = paintLayer.getLineCalculation().getB();
		minX = BULLET_SIZE/2;
		maxX = paintLayer.getWidth()-BULLET_SIZE/2;
		minY = BULLET_SIZE/2;
		maxY = paintLayer.getHeight();
		x = START_X;
		y = START_Y;
		dX = paintLayer.getLineCalculation().getDX();
		dY = -1;
		calculateActualDX();
		shiftXY();
		bulletCalculation.setBulletCoords(bulletNumber, (int)shiftedX, (int)shiftedY);
	}
	
	private void shiftXY() {
		shiftedX = x - BULLET_SIZE/2;
		shiftedY = y - BULLET_SIZE/2;
	}

	private void calculateActualDX() {
		dX = dX * Math.cos(Math.atan(k))*BULLET_STEP_DISTANCE;
	}

	public void setUpListener(BulletTimer bulletTimer){
		addActionListener(bulletTimer);
	}
	
	public void setUpBulletIndex(int bulletNumber){
		this.bulletNumber = bulletNumber;
	}

//////////////////////////////// ACTION ////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		checkDistanceToShootNewBullet();
		calculateNewCoords();
		checkCollisions();
		bulletCalculation.setBulletCoords(bulletNumber, (int)shiftedX, (int)shiftedY);
		bulletCalculation.repaintFromBulletTimer(bulletNumber);
	}

	private void checkDistanceToShootNewBullet() {
		if (!nextBulletShooted && calculateDistanceToStartPoint() > BULLET_SIZE*2){
			bulletCalculation.shootOneMoreBullet();
			nextBulletShooted = true;
		}
	}

	private int calculateDistanceToStartPoint() {
		return (int)(Math.sqrt(Math.pow(START_X-x, 2)+Math.pow(START_Y-y, 2)));
	}

	private void calculateNewCoords() {
		if (dX != 0){
			x += dX;
			y = k*x + b;
		}else{
			y += dY*BULLET_STEP_DISTANCE;
		}
		shiftXY();
	}

	private void checkCollisions() {
		checkCollisionWithBorder();
		checkCollisionWithRoof();
		checkCollisionWithFloor();
		checkCollisionWithBrick();
	}

		private void checkCollisionWithBorder() {
			if (x < minX || x > maxX){
				x = (x<minX)? minX:maxX;
				dX = -dX;
				calculateNewKB();
			}
		}
		
		private void checkCollisionWithRoof() {
			if (y < minY){
				y = minY;
				dY = 1;
				calculateNewKB();
			}
		}
		
		private void checkCollisionWithFloor() {
			if (y > maxY+BULLET_SIZE){
				dX = 0;
				dY = 0;
				finished = true;
			}
		}

		private void checkCollisionWithBrick() {
			for (Rectangle brick : paintLayer.getBricks()) {
				if (brick.intersects(bulletCalculation.getBullets()[bulletNumber])){
					Rectangle tempBullet = bulletCalculation.getBullets()[bulletNumber];
					double tempX, tempY;
						tempX = x-dX;
						tempY = k*tempX + b;					
					double tempK, tempB;
						tempK = Math.tan(Math.atan(-k));
						tempB = tempY - tempX*tempK;
					
					tempX += dX;
					tempY = tempK*tempX + tempB;
					tempBullet.setLocation((int)tempX-BULLET_SIZE/2, (int)tempY-BULLET_SIZE/2);
					
					if (brick.intersects(tempBullet)){
						dX = -dX;
					}else{
						dY = -dY;
					}
					k = Math.tan(Math.atan(-k));
					b = y - k*x;
				}
			}
		}

		private void calculateNewKB(){
			k = Math.tan(-Math.atan(k));
			b = y - k*x;
		}
	
	public boolean isFinished(){
		return finished;
	}
}
