package AdvancedGraphics;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;

public class AppWindow {

	private JFrame frame;
	private NodePlot plot;

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Advanced Graphics");
		plot = new NodePlot();
		
		frame.setResizable(false);
		frame.setBounds(100, 100, 887, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(plot);
		scrollPane.setBounds(119, 8, 403, 403);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPaneList = new JScrollPane();
		scrollPaneList.setBounds(554, 8, 296, 403);
		scrollPaneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPaneList);
		JList list = new JList();
		list.setBackground(Color.LIGHT_GRAY);
		scrollPaneList.setViewportView(list);
		
		
		
		JButton btnGrid = new JButton("Add Grid");
		btnGrid.setBounds(12, 28, 97, 23);
		btnGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot.addItem(new NodeGrid());				
			}
		});
		frame.getContentPane().add(btnGrid);
		
		//Origin Point
		JButton btnOrigin = new JButton("Add Origin");
		btnOrigin.setBounds(12, 60, 97, 23);
		btnOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot.addItem(new NodeOrigin());
			}
		});
		frame.getContentPane().add(btnOrigin);
		
		//Clear Button
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(12, 361, 97, 23);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plot.clear();
				ArrayList n = getArr();
				list.setListData(n.toArray());
				
			}
		});
		frame.getContentPane().add(btnClear);
		
		//Add 10
		JButton btnAddRandom = new JButton("Add 10");
		btnAddRandom.setBounds(12, 94, 97, 23);
		btnAddRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(10);
				ArrayList n = getArr();
				list.setListData(n.toArray());;
				list.setBackground(Color.LIGHT_GRAY);
				list.setValueIsAdjusting(true);
				scrollPaneList.setViewportView(list);
			}
		});
		frame.getContentPane().add(btnAddRandom);
		
		//Add 1000
		JButton btnAdd = new JButton("Add 1000");
		btnAdd.setBounds(12, 128, 97, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(1000);
				ArrayList n = getArr();
				list.setListData(n.toArray());
				list.setBackground(Color.LIGHT_GRAY);
				list.setValueIsAdjusting(true);
				scrollPaneList.setViewportView(list);
			}
		});
		frame.getContentPane().add(btnAdd);
		
		JButton btnRecolor = new JButton("Recolor");
		btnRecolor.setBounds(12, 162, 97, 23);
		frame.getContentPane().add(btnRecolor);
		btnRecolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recolor();
				ArrayList n = getArr();
				list.setListData(n.toArray());
				list.setBackground(Color.LIGHT_GRAY);
				list.setValueIsAdjusting(true);
				scrollPaneList.setViewportView(list);
			}
		});
		
		JButton btnNewButton = new JButton("Remove Item");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex()>=0) {
					plot.removeItem(list.getSelectedIndex());
					ArrayList n = getArr();
					list.setListData(n.toArray());
					list.setBackground(Color.LIGHT_GRAY);
					list.setValueIsAdjusting(true);
					scrollPaneList.setViewportView(list);
				}
			}
		});
		btnNewButton.setBounds(12, 191, 97, 23);
		frame.getContentPane().add(btnNewButton);

		

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mntmNewMenuItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		// this is what happens when the open button is clicked
		// Set the relative file path
		// Replace with a JFileChooser
		JFileChooser jf = new JFileChooser();
		int result = jf.showOpenDialog(null);
		if(result != jf.APPROVE_OPTION) {
		System.err.println("File not selected");
		return;
		}
		String filename = jf.getSelectedFile().toString();
		// Open the file
		File file = new File(filename);

		// Create the Scanner object and scope to the entire main method
		Scanner in = null;

		try {
		// Try to open the file
		in = new Scanner(file);

		try {
		// Read in the data one line at a time
		while(in.hasNextLine()) {
		/***** In here, you need to
		* read in all the shapes one by one,
		* create a new shape and add it to the plot*/
		// Do something with this String
		String line = in.nextLine();
		String[] components = line.split(",");
		switch(components[0]) {
		case "Rectangle":
		int x = Integer.parseInt(components[1]);
		int y = Integer.parseInt(components[2]);

		// Set a random radius or height/width
		int w = Integer.parseInt(components[3]);
		int h = Integer.parseInt(components[4]);

		// Create random color components (0-255)
		int red = Integer.parseInt(components[5]);
		int green = Integer.parseInt(components[6]);
		int blue = Integer.parseInt(components[7]);

		Color c = new Color(red,green,blue);
		plot.addItem(new NodeRectangle(x,y,w,h,c));

		break;
		case"Circle":
		x = Integer.parseInt(components[1]);
		y = Integer.parseInt(components[2]);

		// Set a random radius or height/width
		int r = Integer.parseInt(components[3]);

		// Create random color components (0-255)
		red = Integer.parseInt(components[4]);
		green = Integer.parseInt(components[5]);
		blue = Integer.parseInt(components[6]);

		c = new Color(red,green,blue);
		plot.addItem(new NodeCircle(x,y,r,c));

		break;
		case"Rhombus":
			x = Integer.parseInt(components[1]);
			y = Integer.parseInt(components[2]);

			// Set a random radius or height/width
			int w1 = Integer.parseInt(components[3]);

			// Create random color components (0-255)
			red = Integer.parseInt(components[4]);
			green = Integer.parseInt(components[5]);
			blue = Integer.parseInt(components[6]);

			c = new Color(red,green,blue);
			plot.addItem(new NodeRhombus(x,y,w1,c));
		break;

		default:
		}
		}

		// Fix anything that broke and clean up
		} catch (NoSuchElementException e1) {
		System.err.println("Record Error: " + e1.getMessage());
		} catch (IndexOutOfBoundsException e1) {
		System.err.println("Parse Error: " + e1.getMessage());
		} catch (NumberFormatException e1) {
		System.err.println("Data Error: " + e1.getMessage());
		} finally {
			ArrayList n = getArr();
			list.setListData(n.toArray());
			list.setBackground(Color.LIGHT_GRAY);
			list.setValueIsAdjusting(true);
			scrollPaneList.setViewportView(list);
			in.close();
		}
		} catch (FileNotFoundException e1) {
		System.err.println("File Unavailable: " + e1.getMessage());
		}
		}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		mnNewMenu.add(mntmExit);
		}	
	// Use this method to help you create a shape loader	
	private void drawRandomShapes(int count) {
		Random generator = new Random();
		
		for (int i=0; i<count; i++) {
			
			// Create random X and Y locations
			int x = generator.nextInt(601)-200;
			int y = generator.nextInt(601)-200;

			// Set a random radius or height/width
			int r = generator.nextInt(201);
			int w = generator.nextInt(401);
			int h = generator.nextInt(401);

			// Create random color components (0-255)
			int red = generator.nextInt(128)+128;
			int green = generator.nextInt(128)+128;
			int blue = generator.nextInt(128)+128;
			
			Color c = new Color(red,green,blue);
			
			int ran = generator.nextInt(3); 
			if (ran == 0) { 
				plot.addItem(new NodeCircle(x,y,r,c));
			} else if(ran == 1) {
				plot.addItem(new NodeRectangle(x,y,w,h,c));
			} else if(ran == 2){
				plot.addItem(new NodeRhombus(x,y,w,c));
			}
		}

	}

	public void recolor () {
		for (Node node : plot.getList()) {
			if (node instanceof NodeGrid || node instanceof NodeOrigin) {}
			else {	
				Random generator = new Random();
				int red = generator.nextInt(128)+128;
				int green = generator.nextInt(128)+128;
				int blue = generator.nextInt(128)+128;
				Color c = new Color(red,green,blue);
				node.setColor(c);
				plot.reColor(node, c);
			}
		}
	}
	
	public ArrayList getArr() {
		ArrayList<String> n = new ArrayList<>();
		for (Node a : plot.getList()) {
			if (a instanceof NodeGrid || a instanceof NodeOrigin) {}else {
				n.add(a.toString()); 
			}
		}
		return n;
	}
	
	public void setVisible(boolean b) {
		if (b) {
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
	