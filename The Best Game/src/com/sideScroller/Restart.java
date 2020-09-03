package com.sideScroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Restart {
	
	
	Rectangle restartButton = new Rectangle(GamePanel.WIDTH / 2 - 100, 250, 200, 50);
	
	public void drawScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font = new Font("arial", Font.PLAIN, 50);
		Font font2 = new Font("arial", Font.PLAIN, 30);
		
		g.setFont(font);
		
		int width = g.getFontMetrics(font).stringWidth("YOU DIED");
		g.setColor(Color.red);
		
		g.drawString("YOU DIED", GamePanel.WIDTH / 2 - width/2, 100);
		
		g.setFont(font2);
		g.setColor(Color.white);
		
		g.drawString("RESTART", GamePanel.WIDTH / 2 - g.getFontMetrics(font2).stringWidth("RESTART") / 2, restartButton.y + g.getFontMetrics(font2).getHeight());
		g2d.draw(restartButton);

	}
}
