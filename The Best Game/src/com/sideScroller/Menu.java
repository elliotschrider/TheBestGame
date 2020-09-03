package com.sideScroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	Rectangle startButton = new Rectangle(GamePanel.WIDTH / 2 - 100, 150, 200, 50);
	Rectangle controlsButton = new Rectangle(GamePanel.WIDTH / 2 - 100, 250, 200, 50);
	Rectangle quitButton = new Rectangle(GamePanel.WIDTH / 2 - 100, 350, 200, 50);
	Rectangle backToMainButton = new Rectangle(GamePanel.WIDTH / 2 - 175, 350, 350, 50);
	boolean controls = false;
	
	public void drawMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font = new Font("arial", Font.PLAIN, 50);
		Font font2 = new Font("arial", Font.PLAIN, 30);
		
		g.setFont(font);
		
		int width = g.getFontMetrics(font).stringWidth("THE BEST GAME");
		
		g.drawString("THE BEST GAME", GamePanel.WIDTH / 2 - width/2, 100);
		
		g.setFont(font2);
		g.drawString("START", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("START") / 2, startButton.y + g.getFontMetrics(font2).getHeight());
		g.drawString("CONTROLS", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("CONTROLS") / 2, controlsButton.y + g.getFontMetrics(font2).getHeight());
		g.drawString("QUIT", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("QUIT") / 2, quitButton.y + g.getFontMetrics(font2).getHeight());
		g2d.draw(startButton);
		g2d.draw(controlsButton);
		g2d.draw(quitButton);
		
		if(controls == true) {
			g.fillRect(0, 0, 2000, 2000);
			g.setColor(Color.white);
			g.setFont(font);
			g.drawString("CONTROLS", GamePanel.WIDTH / 2 - g.getFontMetrics(font).stringWidth("CONTROLS")/2, 100);
			g.setFont(font2);
			g.drawString("JUMP - UP ARROW or W", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("JUMP - UP ARROW or W")/2, 150);
			g.drawString("LEFT - LEFT ARROW or A" , GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("LEFT - LEFT ARROW or A")/2, 200);
			g.drawString("RIGHT - RIGHT ARROW or D", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("RIGHT - RIGHT ARROW or D")/2, 250);
			g.drawString("POWER UP - SPACE BAR", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("POWER UP - SPACE BAR")/2, 300);
			g.drawString("BACK TO MAIN MENU", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("BACK TO MAIN MENU") / 2, backToMainButton.y + g.getFontMetrics(font2).getHeight());
			g2d.draw(backToMainButton);
		}
		
	}
}
