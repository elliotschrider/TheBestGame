package com.sideScroller;

import java.io.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.*;

public class Map {

	int x;
	int y;
	
	int mapHeight = 60;
	int mapWidth = 80;
	
	int minX;
	int minY;
	int maxX = 0;
	int maxY = 0;
	
	String fileName;
	
	
	int tileSize;
	private int[][] map;
	
	public Map(String fileName, int tileSize) {

		
		this.tileSize = tileSize;
		
		try {
			map = new int [mapHeight][mapWidth];
			
			minX = GamePanel.WIDTH - mapWidth * tileSize;
			minY = GamePanel.HEIGHT - mapHeight * tileSize;
			
			
			Scanner sc = new Scanner(new File(fileName));
		//	while (sc.hasNextInt()) {
			
			
			for(int row = 0; row < mapHeight; row++) {
				for(int col = 0; col < mapWidth; col++) {
					
						map[row][col] = sc.nextInt();
						
				
				}
				
				
				
				}
			
			
		//	}
	
		
		} 
		
		
		
		catch(Exception e) {}
		
	}
	
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		public void setX(int set) {
			x = set;
			if(x < minX) {
				x = minX;
			}
			if(x > maxX) {
				x = maxX;
			}
		}
		
		public void setY(int set) {
			y = set;
			if(y < minY) {
				y = minY;
			}
			if(y > maxY) {
				y = maxY;
			}
			
		}
		
		public int getColTile(int x) {
			return x / tileSize;
		}
		
		public int getRowTile(int y) {
			return y / tileSize;
		}
		
		public int getTile(int row, int col) {
			return map[row][col];
		}
		
		public int getTileSize() {
			return tileSize;
		}
		
		
		
		public void drawMap(Graphics g) {
			
			
		for(int row = 0; row < mapHeight; row++) {
			for(int col = 0; col < mapWidth; col++) {
				
				   int tile = map[row][col];
				   
				
				if (tile == 0) {
					
					g.setColor(Color.BLUE);
				}
				
				else if (tile == 1) {
					
					g.setColor(Color.black);
				}
				
				
				g.fillRect(x + col * tileSize, y + row * tileSize, tileSize, tileSize);
				//g.drawString(test, 100 + 10*col, 100 + 10*row);
			
				
				
			}
		}
		
		
	}
	
	
}
