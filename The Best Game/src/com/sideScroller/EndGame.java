package com.sideScroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EndGame {
	
	
	Rectangle backToMainButton = new Rectangle(GamePanel.WIDTH / 2 - 175, 250, 350, 50);
	
	/*
	 * Draws the screen
	 * @param g The graphics object
	 */
	public void drawScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font = new Font("arial", Font.PLAIN, 50);
		Font font2 = new Font("arial", Font.PLAIN, 30);
		
		g.setFont(font);
		
		int width = g.getFontMetrics(font).stringWidth("YOU BEAT THE GAME");
		g.setColor(Color.green);
		
		g.drawString("YOU BEAT THE GAME", GamePanel.WIDTH / 2 - width/2, 100);
		
		g.setFont(font2);
		g.setColor(Color.white);
		
		g.drawString("BACK TO MAIN MENU", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("BACK TO MAIN MENU") / 2, backToMainButton.y + g.getFontMetrics(font2).getHeight());
		g2d.draw(backToMainButton);

	}
}
