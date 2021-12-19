package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Node {

	// Every subclass supports a Color...
	// Access: protected (subclasses can access and modify directly) 
	protected Color c;
	
	// Every subclass of Node is forced to implement this method
	public abstract void draw(Graphics g);
	public abstract void setColor(Color c);
}
