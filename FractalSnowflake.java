/* Program: FractalSnowflake.java
 * Student: Erika Nielsen
 * Purpose: To create a Koch snowflake fractal within a GUI application 
*/

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class FractalSnowflake extends JApplet implements ActionListener
{
	//create JTextField and panel
	private JLabel jlbl = new JLabel("Enter an Order: ");
	private JTextField orderField = new JTextField();
	private FractalSnowflakePanel snowflakePanel = new FractalSnowflakePanel(); 
	
	public FractalSnowflake()
	{
		// Panel to hold label & text field
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(jlbl);
		panel.add(orderField);
		
		// Add a Koch snowflake panel to the applet
		add(snowflakePanel);
		add(panel, BorderLayout.SOUTH);
		
		// Register a listener
		orderField.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{ 
		try{
		int newOrder=Integer.parseInt(orderField.getText().trim());
		snowflakePanel.setOrder(newOrder);
		}
		catch (NumberFormatException e1) 
		{
	        JOptionPane.showMessageDialog(null, "You must enter a whole number", "Error", JOptionPane.ERROR_MESSAGE);
	        orderField.setText(null);
		}
	}
	
	static class FractalSnowflakePanel extends JPanel
	{
		private int order = 0;
		static Point np3, np4, np5;

		/** Return order */
		public int getOrder()
		{
			return order;
		}
		
		/** Set a new order */
		public void setOrder(int order)
		{
			this.order = order;
			repaint();//to avoid ghost lines
		}

		//does the painting
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			// Select three points in proportion to the panel size
			Point p1 = new Point(getWidth() / 2, 10);
			Point p2 = new Point(10, getHeight()-10);
			Point p3 = new Point(getWidth()-10, getHeight()-10);
			
			// Draw a triangle to connect three points
			displayTriangles(g,order,p1,p2);
	        displayTriangles(g,order,p2,p3);
	        displayTriangles(g,order,p3,p1);
		}
		
		private static void displayTriangles(Graphics g, int order, Point p1, Point p2)
		{
			if (order == 0)
			{
				
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
			}
			else if(order>0)
			{
				splitSides(p1, p2);
				
                displayTriangles(g,order-1, p1, np3);
                displayTriangles(g,order-1, np3, np5);
                displayTriangles(g,order-1, np5, np4);
                displayTriangles(g,order-1, np4, p2);
			}
		}

		private static void splitSides(Point p1,Point p2)
		{
			int changeX, changeY;
			
			
			//find distance between coordinates
			changeX=p2.x-p1.x;
			changeY=p2.y-p1.y;

			//points in line with parent triangle
			np3.x=p1.x+(changeX/3);
			np3.y=p1.y+(changeY/3);
			
			np4.x=p1.x+2*(changeX/3);
			np4.y=p1.y+2*(changeY/3);

			//bumped out point (ripped the equation off line)
            np5.x=(int)(.5*(p1.x+p2.x)+Math.sqrt(3)*(p1.y-p2.y)/6);
			np5.y=(int)(.5*(p1.y+p2.y)+Math.sqrt(3)*(p1.x-p2.x)/6);
		}
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Koch Snowflake");
		FractalSnowflake applet = new FractalSnowflake();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); 
	}
}

