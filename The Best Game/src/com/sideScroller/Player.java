package com.sideScroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * @author elliot
 *
 */
public class Player {
	
	double x;
	double dx;
	double y;
	double dy;

	double pUpX;
	double pUpY;
	
	
	
	int width;
	int height;

	boolean isMovingRight;
	boolean isMovingLeft;
	boolean isJumping;
	boolean isFalling;
	boolean isCrouching;
	
	double moveSpeed;
	double maxSpeed;
	double jumpSpeed;
	double frictionSpeed;
	double maxFallingSpeed;
	double gravity;

	
	Map map;
	
	boolean topLeft;
	boolean topRight;
	boolean bottomLeft;
	boolean bottomRight;
	
	boolean collideLeft;
	boolean collideRight;
	boolean collideTop;
	boolean collideBottom;

	int health;
	int attackDamage;
	int pUpAttackDamage;
	double pUpSpeed;
	
	Rectangle powerUp;
	
	boolean canUsePowerUp = true;
	boolean canShoot = true;
	boolean isShooting = false;
	boolean powerUpExists = false;
	
	Enemy[] enemies = new Enemy[13];

	//Creates the number of enemy of objects that is specified
	public void setupEnemies() {
		for (int i = 0; i < enemies.length; i++) {
			
			enemies[i] = new Enemy(map);
		}
	}
	
	public Player(Map m, Enemy array[]) {

		map = m;
		enemies = array;
		width = 20;
		height = 20;
		health = 100;
		attackDamage = 40;
		pUpAttackDamage = 60;
		moveSpeed = 0.6;
		maxSpeed = 4.2;
		maxFallingSpeed = 12;
		jumpSpeed = -11.0;
		frictionSpeed = 0.30;
		gravity = 0.64;
		
		
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
	
	public void setJump(boolean b) {
		if(!isFalling) {
			
			isJumping = true;
			
		}
	}
	
	
	public void setShooting(boolean b) {
		isShooting = b;
	}
	
	public void setSpawning(boolean b) {
		powerUpExists = b;
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
	    
	    
	    /*
	     * Determines if two objects are colliding and how they are colliding
	     * @param x1, y1 The x and y coordinates of the first object
	     * @param x2, y2 The x and y coordinates of the second object
	     * @param w1, h1 The width and height of the first object
	     * @param w2, h2 The width and height of the second object
	     * 
	     */
	    public void collisionDetection(double x1,double y1,double x2,double y2, double w1, double h1, double w2, double h2) {
	    	
	    	double pLeftSide = x1 - width / 2;
	    	double pRightSide = x1 + width / 2;
	    	double pTopSide = y1 - height / 2;
	    	double pBottomSide = y1 + height / 2;
	    	
	    	double eLeftSide = x2 - width / 2;
	    	double eRightSide = x2 + width / 2;
	    	double eTopSide = y2 - height / 2;
	    	double eBottomSide = y2 + height / 2;
	    	
	    	collideLeft = pLeftSide <= eRightSide && pLeftSide >= eLeftSide && pBottomSide > eTopSide && pBottomSide < eBottomSide + 1;
	    	collideRight = pRightSide >= eLeftSide && pRightSide <= eRightSide && pBottomSide > eTopSide && pBottomSide < eBottomSide + 1;
	    	collideBottom = (pBottomSide < eTopSide + 5 && pBottomSide > eTopSide - 5) && ((pLeftSide <= eRightSide && pLeftSide >= eLeftSide) || ( pRightSide >= eLeftSide && pRightSide <= eRightSide));
	    	
	    }
	    
	    //Determines the next position of the player, checks collisions and updates position of powerup if it exists
		public void update() {
			
			//Determine next position
			
			if(isMovingLeft == true) {
				dx -= moveSpeed;
						
						if(dx < -maxSpeed) {
							dx = -maxSpeed;
						}	
			}
			else if(isMovingRight == true) {
				dx += moveSpeed;
						if (dx > maxSpeed) {
							dx = maxSpeed;
						}
			}
			else if(isMovingLeft == false || isMovingRight == false){
				if (dx > 0) {
					dx -= frictionSpeed;
					if (dx < 0) {
					dx = 0;
					}
				 	
				}
				
				else if (dx < 0) {
						dx += frictionSpeed;
						if(dx > 0) {
						dx = 0;
						}
					}
			}
			
			
			if(isJumping) {
				dy = jumpSpeed;
				isFalling = true;
				isJumping = false;
			}
			
			if(isFalling) {
				dy += gravity;
				if(dy > maxFallingSpeed) {
					dy = maxFallingSpeed;
				}
			}
			
			else {
				dy = 0;
			}
			
			//Check collisions with map
			
			int currTileCol = map.getColTile((int) x);
			int currTileRow = map.getRowTile((int) y);
			
			double nextX = x + dx;
			double nextY = y + dy;
			
			double tempX = x;
			double tempY = y;
			
	
			nearTiles(x, nextY);
			if(dy < 0) {
				if (topLeft || topRight) {
					dy = 0;
					tempY = currTileRow * map.getTileSize() + height / 2;
				}
				else {
					tempY += dy;
				}

			}
				
			if(dy > 0) {
				if (bottomLeft || bottomRight) {
					dy = 0;
					isFalling = false;
					tempY = (currTileRow + 1) * map.getTileSize() - height / 2;
					//System.out.println("colliding");
				}
					else {
						tempY += dy;
						//System.out.println("falling");
						
				}	
		}
			nearTiles(nextX, y); 
			if (dx < 0) {
				if (topLeft || bottomLeft) {
					dx = 0;
					tempX = currTileCol * map.getTileSize() + width / 2;
				}
			
			    else {
				tempX += dx;
			    }
			}
		if (dx > 0 ) {
			if (topRight || bottomRight) {
				dx = 0;
				tempX = (currTileCol + 1) * map.getTileSize() - width / 2;
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
		
		//Collision detection with enemies
		for (int i = 0; i < enemies.length; i++) {
		if (enemies[i].health > 0) {
		collisionDetection(x, nextY, enemies[i].x, enemies[i].y, width, height, width, height);
		
		if(collideBottom) {
			setJump(true);
			enemies[i].health -= attackDamage;
			dy = jumpSpeed;
		}
		
		else if(collideLeft) {
			tempX += 40;
			health -= enemies[i].attackDamage;
		}
		
		else if(collideRight) {
			tempX -= 40;
			health -= enemies[i].attackDamage;
		}
		
		
		}
		}
		
		if (canUsePowerUp && isShooting) {
			powerUpExists = true;
			
			
			pUpX = tempX;
			pUpY = tempY;
			
			if(dx >= 0) {
				pUpSpeed = 6.0;
			}
			
			if(dx < 0) {
				pUpSpeed = -6.0;
			}

			isShooting = false;
			}

			if (powerUpExists == true) {
				
				//Map collision for power up
				nearTiles(pUpX, pUpY);
				
				if (topRight || bottomRight) {
					powerUpExists = false;
				}
				
				for (int i = 0; i < enemies.length; i++) {
					
					//Enemy collision for power up
					if (enemies[i].health > 0) {
						collisionDetection(pUpX, pUpY, enemies[i].x, enemies[i].y, 5, 5, width, height);
						
						if(collideLeft || collideRight) {
							enemies[i].health -= pUpAttackDamage;
							powerUpExists = false;
						}
						
						
				
					}
					
				}
				//Changes power up position
				pUpX += pUpSpeed;
			}
				
				
		
	
		x = tempX;
		y = tempY;
			
		//move the map
		map.setX((int) (GamePanel.WIDTH / 2 - x));
		map.setY((int) (GamePanel.HEIGHT / 2 - y));
		

		
		
		
		
		}
		
		
		public void drawPlayer(Graphics g) {
			
			int camX = map.getX();
			int camY = map.getY();
			
			g.setColor(Color.green);
			//Draws the player
			g.fillRect((int) (camX + x - width/2),(int) (camY + y - height/2), width, height);
			//Draws the player health bar
			g.fillRect((int) (camX + x - width/2),(int) (camY + y - height/2 - 7), (int) ((health/100.0)*width), height / 6);
			if(powerUpExists) {
				//Draws the power up
				g.setColor(Color.orange);
				g.fillRect((int)(camX + pUpX), (int) (camY + pUpY), 5, 5);
				}
		}
		
		
	
	
	
	
	
}
