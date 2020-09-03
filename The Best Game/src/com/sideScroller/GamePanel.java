package com.sideScroller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
	
/**
 * @author elliot
 *
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{

	/**
	 * 
	*/
	private static final long serialVersionUID = 1L;
		
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	Player p;
	Map LevelOne;
	//PowerUp pUp;
	Timer time;
	Enemy[] enemies;
	int powerUpsUsed = 0;
	Menu menu;
	Restart restart;
	EndGame endGame;
	
	boolean checkPoint1;
	boolean checkPoint2;
	private enum STATE{MENU, RESTART, GAME, ENDGAME};
	
	private STATE State = STATE.MENU;
	
	public GamePanel() {
		super();
		
		
		//Setting up the map
		
		LevelOne = new Map("./src/com/sideScroller/Level1Map.txt", 32);
		
		enemies = new Enemy[13];
		p = new Player(LevelOne, enemies);
		

		for (int i = 0; i < enemies.length; i++) {
				
				enemies[i] = new Enemy(LevelOne);
				enemies[i].setDirectionRight(true);

		}
		
		//Setting up starting position for player and enemies
		p.setX(105);
		p.setY(806 + 30*32);
		enemies[0].setX(176);
		enemies[0].setY(830 + 32*30);
		enemies[1].setX(32*32 + 20);
		enemies[1].setY(22*32 - 5 + 32*30);
		enemies[2].setX(200);
		enemies[2].setY(350 + 32*30);
		enemies[3].setX(27*32);
		enemies[3].setY(37*32 + 25);
		enemies[4].setX(29*32);
		enemies[4].setY(37*32 + 25);
		enemies[5].setX(7*32 + 10);
		enemies[5].setY(22*32 - 7);
		enemies[6].setX(48*22);
		enemies[6].setY(9*32 - 7);
		enemies[7].setX(49*32);
		enemies[7].setY(10*32 - 7);
		enemies[8].setX(49*32);
		enemies[8].setY(17*32 - 7);
		enemies[9].setX(62*32 + 10);
		enemies[9].setY(17*32 - 7);
		enemies[10].setX(76*32 + 10);
		enemies[10].setY(19*32 - 7);
		enemies[11].setX(77*32 + 10);
		enemies[11].setY(19*32 - 7);
		enemies[12].setX(78*32 + 10);
		enemies[12].setY(19*32 - 7);
		
		menu = new Menu();
		restart = new Restart();
		endGame = new EndGame();
		checkPoint1 = false;
		checkPoint2 = false;
	
		
		addKeyListener (this);
		addMouseListener (this);
		setPreferredSize ( new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		time = new Timer(20, this);
		time.restart();
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		if(State == STATE.GAME) {
		p.update();
		
		if(checkPoint1 == false) {
			if (p.y < 30*32) {
				p.health = 100;
				powerUpsUsed = 0;
				checkPoint1 = true;
				
			}
		}
		
		if(checkPoint2 == false) {
			if (p.x > 41*32) {
				p.health = 100;
				powerUpsUsed = 0;
				checkPoint2 = true;
			}
		}
		
		
		for (int i = 0; i < enemies.length; i++) {
			
		enemies[i].update();
		}
	
		if (p.health <= 0) {
			State = State.RESTART;
		}
		
		if (State == State.GAME) {
			if (p.y > 30*32 && p.x > 41*32) {
				State = State.ENDGAME;
				p.x = 105;
				p.y = 806 + 32*30;
			}
		}
		
	}
		
		repaint();
		
		}
		
		
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if(State == STATE.GAME) {
		LevelOne.drawMap(g);
		p.drawPlayer(g);
		
		for (int i = 0; i < enemies.length; i++) {
			
		enemies[i].drawEnemy(g);
			
		}
		
		}
		
		if (State == State.ENDGAME) {
			g.fillRect(0, 0, 2000, 2000);
			endGame.drawScreen(g);
		}
		
		if(State == STATE.MENU) {
			menu.drawMenu(g);
			
		}
		
		if(State == STATE.RESTART) {
			g.fillRect(0, 0, 2000, 2000);
			restart.drawScreen(g);
		}
		
		
			
		}
	
	
	public void keyTyped (KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(State == STATE.GAME) {
		
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			
			p.setDirectionRight(true);	
		}
		
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			
			p.setDirectionLeft(true);
		}
		
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { // and isGrounded == false
			p.setJump(true);
		}
		
		
		if (powerUpsUsed < 5 && p.powerUpExists == false) {
		if (key == KeyEvent.VK_SPACE) {
			p.setSpawning(true);
			p.setShooting(true);
			powerUpsUsed++;
		}
		
		}
		
		}
		//if (key == KeyEvent.VK_SPACE) {	//	pUp.update();
		//}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			
			p.setDirectionRight(false);
			//System.out.println("Right Released");
		}
		
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			
			p.setDirectionLeft(false);
			//System.out.println("Left Released");
		}
		
		
	}
	
		public void mousePressed(MouseEvent e) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			//Detects mouse events
			
			if(State == State.MENU) {
				
			if(menu.controls == false) {
			if(mouseX >= GamePanel.WIDTH / 2 - 100 && mouseX <= GamePanel.WIDTH / 2 + 100 && mouseY >= 150 && mouseY <= 200) {
					
				for(int i = 0; i < enemies.length; i++) {
					enemies[i].health = 100;
				}
				
				State = State.GAME;
				
				
			}
			
			if(mouseX >= GamePanel.WIDTH / 2 - 100 && mouseX <= GamePanel.WIDTH / 2 + 100 && mouseY >= 350 && mouseY <= 400) {
				System.exit(0);
				
			}
			}
				if(mouseX >= GamePanel.WIDTH / 2 - 100 && mouseX <= GamePanel.WIDTH / 2 + 100 && mouseY >= 250 && mouseY <= 300) {
					
					menu.controls = true;
					
				}
				
				if (menu.controls == true) {
					
					if(mouseX >= GamePanel.WIDTH / 2 - 175 && mouseX <= GamePanel.WIDTH / 2 + 175 && mouseY >= 350 && mouseY <= 400) {

						menu.controls = false;
					}
				}
					
					
				
			
			}
			
			
			
			if(State == State.RESTART) {
				if(mouseX >= GamePanel.WIDTH / 2 - 100 && mouseX <= GamePanel.WIDTH / 2 + 100 && mouseY >= 250 && mouseY <= 300) {
					p.health = 100;
					powerUpsUsed = 0;
					p.setX(105);
					p.setY(806 + 32*30);
					if(checkPoint1) {
						p.setY(806);
						for(int i = 5; i < enemies.length; i++) {
							enemies[i].health = 100;
						}
						
					}
					if(checkPoint2) {
						p.setX(105 + 45*30);
						for(int i = 7; i < enemies.length; i++) {
							enemies[i].health = 100;
						}
					}
					
					else  {
					for(int i = 0; i < enemies.length; i++) {
						enemies[i].health = 100;
					}
					}
					State = State.GAME;
					
				}
			}
			
			if(State == State.ENDGAME) {
				if(mouseX >= GamePanel.WIDTH / 2 - 175 && mouseX <= GamePanel.WIDTH / 2 + 175 && mouseY >= 250 && mouseY <= 300) {
					State = State.MENU;
					checkPoint1 = false;
					checkPoint2 = false;
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	

	
}


