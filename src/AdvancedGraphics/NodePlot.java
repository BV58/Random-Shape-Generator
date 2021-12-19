package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class NodePlot extends JComponent implements Iterable<Node>{
	
	private ArrayList<Node> listNode = new ArrayList<>();
	
	public NodePlot() {
		
	}
	
	public void paintComponent(Graphics g) {
		for (Node item : listNode) {
			item.draw(g);
		}
	}
	
	public void addItem(Node node) {
		listNode.add(node);
		this.repaint();
	}
	
	public void removeItem(int index) {
		listNode.remove(index);
		this.repaint();
	}
	
	public void clear() {
		listNode.clear();
		this.repaint();
	}
	
	public void reColor(Node node, Color c) {
		node.setColor(c);
		this.repaint();
	}
	
	public ArrayList<Node> getList() {
		return listNode;
	}

	@Override
	public Iterator<Node> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
