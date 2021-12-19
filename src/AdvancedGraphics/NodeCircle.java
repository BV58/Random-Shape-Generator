package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;

public class NodeCircle extends Node {

	// Every subclass of Node must provide implementation 
	// for the draw() method (see below) as well as inheriting the 
	// protected instance variable c for the shape color
	
	private int x = 0;
	private int y = 0;
	private int radius = 0;
	private int plotWidth = 0;
	private int plotHeight = 0;
		
	public NodeCircle(int x, int y, int radius, Color c) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.c = c;
	}
	
	public void draw(Graphics g) {
		
		plotWidth = (int) g.getClipBounds().getWidth();
		plotHeight = (int) g.getClipBounds().getHeight();
		
		int dX = x - radius + (plotWidth / 2);
		int dY = y - radius + (plotHeight / 2);
		
		g.setColor(c);
		g.drawOval(dX, dY, radius*2, radius*2);
				
	}
	public void setColor(Color c) {
		this.c=c;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	public String toString() {
		return "Circle X" +x+" Y"+y+" Rad"+radius+" R"+ c.getRed()+" G"+c.getGreen()+ " B"+ c.getBlue();
	}
}
