package mech;

import java.awt.GraphicsEnvironment;
public interface Constants {
	
	int START_BRICKS_Q = 10;
	int MIN_BRICKS_TOUGHNESS = 100;
	
	double MINIMUM_ANGLE = Math.PI/6;
	
	int WINDOW_WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	int WINDOW_HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	
	int BUTTON_WIDTH = WINDOW_HEIGHT/5;
	int BUTTON_HEIGHT = BUTTON_WIDTH/2;
	
	int BRICK_WIDTH = (WINDOW_WIDTH-BUTTON_WIDTH) / (START_BRICKS_Q*2);
	int BRICK_HEIGHT = BRICK_WIDTH/2;
	
	int GAMEFIELD_WIDTH = BRICK_WIDTH*START_BRICKS_Q;
	int GAMEFIELD_HEIGHT = WINDOW_HEIGHT;
	int GAMEFIELD_X = BUTTON_WIDTH + (WINDOW_WIDTH-BUTTON_WIDTH)/2 - GAMEFIELD_WIDTH/2;
	
	double DX_PARTITION = 0.004;

	int BULLET_Q = 3;
	int BULLET_SIZE = BRICK_HEIGHT/2;
	int ITERATIONS_BETWEEN_BULLETS = BULLET_SIZE;
	int _BULLET_DISTANCE_PER_SECOND = 2000;
	int BULLET_STEP_DISTANCE = 3;
	int BULLET_DELAY = 1000/(_BULLET_DISTANCE_PER_SECOND/BULLET_STEP_DISTANCE);

	int START_X = GAMEFIELD_WIDTH/2;
	int START_Y = GAMEFIELD_HEIGHT;
}
