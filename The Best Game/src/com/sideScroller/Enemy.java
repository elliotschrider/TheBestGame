package com.sideScroller;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	double x;
	double dx;
	double y;
	double dy;
	
	int width;
	int height;
	
	boolean isMovingRight;
	boolean isMovingLeft = false;
	boolean isJumping;
	boolean isFalling;
	boolean isCrouching;
	
	double moveSpeed;
	double maxSpeed;
	double jumpSpeed;
	double stopSpeed;
	double maxFallingSpeed;
	double gravity;

	
	Map map;
	
	boolean topLeft;
	boolean topRight;
	boolean bottomLeft;
	boolean bottomRight;
	
	int health;
	int attackDamage;

	public Enemy(Map m) {
		map = m;
		
		width = 20;
		height = 20;
		
		moveSpeed = 2.0;
		maxFallingSpeed = 12;
		jumpSpeed = -11.0;
		stopSpeed = 0.30;
		gravity = 0.64;
		health = 100;
		attackDamage = 15;
		
		dx = moveSpeed;
		dy = gravity;
	}
	

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(int i) {
		x = i;	
	}
	
	public void setY(int i) {
		y = i;
	}
	
	public void setDirectionLeft(boolean b) {
		isMovingLeft = b;
	}
	
	public void setDirectionRight(boolean b) {
		isMovingRight = b;
		
	}
	
	/*
	 * Determines if the tiles near the object are blocked or not
	 * @param x,y The x and y coordinates of the object
	 */
	 private void nearTiles(double x, double y) {
	    	int leftTile = map.getColTile((int) (x - width / 2));
	    	int rightTile = map.getColTile((int) (x + width / 2 - 1));
	    	int topTile = map.getRowTile((int) (y - height/2));
	    	int bottomTile = map.getRowTile((int) (y + height/ 2 - 1));
	    	topLeft = map.getTile(topTile, leftTile) == 0;
	    	topRight = map.getTile(topTile, rightTile) == 0;
	    	bottomLeft = map.getTile(bottomTile, leftTile) == 0;
	    	bottomRight = map.getTile(bottomTile, rightTile) == 0;
	    	
	    }
	
	//Determines next position of the enemy and checks collisions with map
	public void update() {
		//determines next position
		
		if (isMovingLeft == true) {
			dx = -moveSpeed;
		}
		
		else if (isMovingRight == true) {
			
			dx = moveSpeed;
		}
		
		int currTileCol = map.getColTile((int) x);
		int currTileRow = map.getRowTile((int) y);
		
		double nextX = x + dx;
		double nextY = y + dy;
		
		double tempX = x;
		double tempY = y;
		
		//checks collision with map
		nearTiles(x, nextY);
			
		if(dy >= 0) {
			if (bottomLeft || bottomRight) {
				dy = 5;
				isFalling = true;
				tempY = (currTileRow + 1) * map.getTileSize() - height / 2;
			//	System.out.println("bottomLeft");
			}
				else {
					if (dx > 0) {
						tempX -= 10;
						isMovingLeft = true;
					}
					
					else if (dx < 0) {
						tempX += 10;
						isMovingRight = true;
						isMovingLeft = false;
					}
					
			}	
	}
		
		nearTiles(nextX, y); 
		if (dx < 0) {
			if (bottomLeft) {
				isMovingLeft = false;
				dx = moveSpeed;
				
			}
		
		    else {
			tempX += dx;
		    }
		}
	if (dx > 0 ) {
		if (bottomRight) {
			isMovingRight = false;
			dx = -moveSpeed;
		}
		else {
			tempX += dx;
		}
		
	}
		
		
	if (!isFalling) {
		nearTiles(x, y + 1);
		if(!bottomLeft && !bottomRight) {
			isFalling = true;
		}
	}

	x = tempX;
	y = tempY;
		
	}
	
	
	/*
	 * Draws the enemy and its health bar
	 * @param g The graphics object
	 */
	public void drawEnemy(Graphics g) {
		
		int camX = map.getX();
		int camY = map.getY();
		
		
		g.setColor(Color.red);
		
		if (health > 0) {
			g.fillRect((int) (camX + x - width/2),(int) (camY + y - height/2), width, height);
			g.setColor(Color.green);
			g.fillRect((int) (camX + x - width/2),(int) (camY + y - height/2 - 7), (int) ((health/100.0)*width), height / 6);
		}
	}
	
	
	
}
