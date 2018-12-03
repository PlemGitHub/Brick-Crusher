package mech;

import java.awt.Point;
import java.awt.Rectangle;
import visual.screen.mainPanel.gameField.PaintLayer;

public class LineCalculation implements Constants{
	
	private class BrickAndQuantity{
		Rectangle brick;
		int quantity;
	}
	private PaintLayer paintLayer;
	private int dX;
	private int maxX;
	// (x1, y1) - first point in the middle of the bottom gamefield's side.
	// (x2, y2) - second point of the line, at Mouse.
	private double x1, x2, y1, y2, b, k;

	public LineCalculation(PaintLayer paintLayer) {
		this.paintLayer = paintLayer;
		maxX = paintLayer.getWidth();
	}
	
	public void setMousePoint(Point p){
		setUpStartXY();
		x2 = p.x;
		y2 = p.y;
		defineDX();
		if (dX != 0){
			calculateLineProperties();
			calculateLine();
		}else{
			y2 = 0;
			checkVerticalCollisionWithBrick();
		}
	}
	
	private void setUpStartXY() {
		x1 = START_X;
		y1 = START_Y;
	}
	
		private void defineDX() {
			if (x2 == x1)
				dX = 0;
			if (x2 < x1)
				dX = -1;
			if (x2 > x1)
				dX = 1;
		}

	private void calculateLineProperties() {
		b = (double)(y1*x2 - y2*x1)/(x2-x1);
		k = (double)(y1-b)/x1;
		
		if (Math.atan(k) >= -MINIMUM_ANGLE
			&& Math.atan(k) < 0){
			k = Math.tan(-MINIMUM_ANGLE);
			b = y1 - k*x1;
			return;
		}
		if (Math.atan(k) <= MINIMUM_ANGLE
			&& Math.atan(k) > 0){
			k = Math.tan(MINIMUM_ANGLE);
			b = y1 - k*x1;
			return;
		}

	}

	private void calculateLine() {
			findLineSegmentFromX1Y1toBorder();
			checkCollisionWithBrick();
	}

	private void findLineSegmentFromX1Y1toBorder() {
		for (int i = 1; i <= maxX; i++) {
			calculateY2WithNewX2(x1 + dX*i);
			// Line hits CORNER
			if ((x2 <= 0 || x2 >= maxX) && y2 <= 0){
				x2 = (x2 <= 0)? 0:maxX;
				y2 = 0;
				return;
			}
			// Line hits LEFT wall
			if (x2 <= 0){
				calculateY2WithNewX2(0);
				return;
			}
			// Line hits TOP wall
			if (y2 <= 0){
				y2 = 0;
				return;
			}
			// Line hits RIGHT wall
			if (x2 >= maxX){
				calculateY2WithNewX2(maxX);
				return;
			}
		}
	}
	
	private void calculateY2WithNewX2(double newX2){
		x2 = newX2;
		y2 = k*x2 + b;
	}

	private void checkCollisionWithBrick() {
		BrickAndQuantity brickAndQuantity = findBricksIntersected();
		Rectangle collidedBrick = null;

		while (brickAndQuantity.quantity >= 1){
			collidedBrick = brickAndQuantity.brick;
			calculateY2WithNewX2(x2-dX);
			brickAndQuantity = findBricksIntersected();
		}
		
		if (collidedBrick != null){
			while (brickAndQuantity.quantity == 0){
				calculateY2WithNewX2(x2 + dX*DX_PARTITION);
				if (x2 <= 0 || x2 >= maxX || y2 <= 0){
					findLineSegmentFromX1Y1toBorder();
					break;
				}					
				brickAndQuantity = findBricksIntersected();
			}
		}
	}
	
		private BrickAndQuantity findBricksIntersected(){
			BrickAndQuantity brickAndQuantity = new BrickAndQuantity();
			for (Rectangle brick : paintLayer.getBricks()) {
				if (brick.intersectsLine(x1, y1, x2, y2)){
					brickAndQuantity.quantity++;
					brickAndQuantity.brick = brick;
				}
			}
			return brickAndQuantity;
		}
		
	private void checkVerticalCollisionWithBrick() {
		while (findBricksIntersected().quantity >= 1){
			y2++;
		}
	}
	
	public double getX1(){
		return x1;
	}
	
	public double getX2(){
		return x2;
	}
	
	public double getY1(){
		return y1;
	}
	
	public double getY2(){
		return y2;
	}
	
	public double getK(){
		return k;
	}
	
	public double getB(){
		return b;
	}
	
	public int getDX(){
		return dX;
	}	
}
