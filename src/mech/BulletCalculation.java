package mech;

import java.awt.Rectangle;

import visual.screen.mainPanel.gameField.PaintLayer;

public class BulletCalculation implements Constants{

	private PaintLayer paintLayer;
	private BulletTimer[] bulletTimers = new BulletTimer[BULLET_Q];
	private Rectangle[] bullets = new Rectangle[BULLET_Q];
	private int bulletsGenerated;

	public BulletCalculation(PaintLayer paintLayer) {
		this.paintLayer = paintLayer;
		generateBulletRectangles();
	}
	
	public void generateBulletRectangles(){
		for (int i = 0; i < BULLET_Q; i++) {
			bullets[i] = new Rectangle(-BULLET_SIZE, -BULLET_SIZE, BULLET_SIZE, BULLET_SIZE);
		}
	}
	
	public void doShot(){
		generateNewBullet();
	}

	public void generateNewBullet(){
		BulletTimer bulletTimer = new BulletTimer(BULLET_DELAY, null, this);
		bulletTimer.setUpListener(bulletTimer);
		bulletsGenerated++;
		bulletTimers[getLastBulletIndex()] = bulletTimer;
		bulletTimer.setUpBulletIndex(getLastBulletIndex());
		bulletTimer.start();
	}
	
		public int getLastBulletIndex(){
			return bulletsGenerated-1;
		}

	public void setBulletCoords(int i, int x, int y){
		bullets[i].setLocation(x, y);
	}
	
	public void shootOneMoreBullet() {
		if (bulletTimers[BULLET_Q-1] == null)
			generateNewBullet();
	}
	
	public void repaintFromBulletTimer(int bulletNumber){
		paintLayer.repaint(bullets[bulletNumber].x-BULLET_SIZE*10, bullets[bulletNumber].y-BULLET_SIZE*10,
				BULLET_SIZE*30, BULLET_SIZE*30);
		if (bulletNumber == getLastBulletIndex()){
			checkEndOfShooting();
		}
	}

		private void checkEndOfShooting() {
			boolean atLeastOneBulletTimerNotFinished = false;
			for (BulletTimer bulletTimer : bulletTimers) {
				if (bulletTimer != null && !bulletTimer.isFinished()){
					atLeastOneBulletTimerNotFinished = true;
					break;
				}
			}
			
			if (!atLeastOneBulletTimerNotFinished){
				for (int i = 0; i < bulletsGenerated; i++) {
					if (bulletTimers[i] != null)
						bulletTimers[i].stop();
				}
				generateBulletRectangles();
				bulletTimers = new BulletTimer[BULLET_Q];
				bulletsGenerated = 0;
				paintLayer.enableMouseListenersAtGamefield();
				paintLayer.showLine();
			}
		}
		
	public PaintLayer getPaintLayer(){
		return paintLayer;
	}
	
	public Rectangle[] getBullets(){
		return bullets;
	}
}
