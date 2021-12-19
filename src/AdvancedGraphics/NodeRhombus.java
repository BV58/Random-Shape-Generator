package AdvancedGraphics;

import java.awt.Color;
import java.awt.Graphics;

public class NodeRhombus extends Node{

	private int x = 0;
	private int y = 0;
	private int shapeWidth = 0;
	//private int plotWidth = 0;
	//private int plotHeight = 0;
	
	public NodeRhombus(int x, int y, int shapeWidth, Color c) {
		this.x = x;
		this.y = y;
		this.shapeWidth = shapeWidth;
		this.c = c;
	}
	@Override
	public void draw(Graphics g) {
		int[] dX = new int[4];
		int[] dY = new int[4];
		dX[0] = x; dX[1] = x+shapeWidth; dX[2] = x+(shapeWidth/2); dX[3] = x-(shapeWidth/2);
		dY[0] = y; dY[1] = y; dY[2] = y-shapeWidth; dY[3] = y-shapeWidth;

		g.setColor(c);
		g.drawPolygon(dX, dY, 4);
	}
	@Override
	public void setColor(Color c) {
		this.c=c;
	}
	public String toString() {
		return "Rhombus X" +x+" Y"+y+" W"+shapeWidth+ " R"+ c.getRed()+" G"+c.getGreen()+ " B"+ c.getBlue();
	}
}
