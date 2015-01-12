/*Program: CircleMove.java
 * Programmer:Erika Nielsen
 * Purpose: GUI to paint a circle on the screen and add buttons to shift circle.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircleMove extends JFrame 
{
		//create components and set variable
		private static int DIAMETER=10, MAX_X=300, MAX_Y=200;
		private JButton jbtLeft=new JButton("Left");
		private JButton jbtRight=new JButton("Right");
		private JButton jbtUp=new JButton("Up");
		private JButton jbtDown=new JButton("Down");
		private CirclePanel canvas=new CirclePanel();
		
	CircleMove()
		{
			//create a panel and add buttons
			JPanel panel=new JPanel();
			panel.add(jbtLeft);
			panel.add(jbtRight);
			panel.add(jbtUp);
			panel.add(jbtDown);
		
			//add canvas panel and button panels to frame
			add(canvas, BorderLayout.CENTER);
			add(panel, BorderLayout.SOUTH);
			
			//add listener and events to buttons
			jbtLeft.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					canvas.moveLeft();
					if(canvas.getX()<=0)
					{
						JOptionPane.showMessageDialog(null,"You have reached the edge of the screen");
						canvas.setX(0);
					}
					canvas.requestFocusInWindow();
				}
			});
			jbtRight.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					canvas.moveRight();
					if(canvas.getX()>=MAX_X-DIAMETER)
					{
						JOptionPane.showMessageDialog(null,"You have reached the edge of the screen");
						canvas.setX(MAX_X-DIAMETER);
					}
					canvas.requestFocusInWindow();
				}
			});
			jbtUp.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					canvas.moveUp();
					if(canvas.getY()<=0)
					{
						JOptionPane.showMessageDialog(null,"You have reached the edge of the screen");
						canvas.setY(0);
					}
					canvas.requestFocusInWindow();
				}
			});
			jbtDown.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					canvas.moveDown();
					if(canvas.getY()>=MAX_Y-DIAMETER)
					{
						JOptionPane.showMessageDialog(null,"You have reached the edge of the screen");
						canvas.setY(MAX_Y-DIAMETER);
					}
					canvas.requestFocusInWindow();
				}
			});
		}		
	public static void main(String[]args)
	{
		JFrame frame=new CircleMove();
		frame.setTitle("Move a Circle");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAX_X, MAX_Y);
		frame.setVisible(true);		
	}
}

//class for creating circle and drawing it to screen
@SuppressWarnings("serial")
class CirclePanel extends JPanel
{
	private int DIAMETER=10, MAX_X=300, MAX_Y=200, SHIFT=5, x, y;
	
	public CirclePanel()
	{
	}
	public CirclePanel(int xValue, int yValue)
	{
		x=xValue;
		y=yValue;
	}	
	public void setX(int xValue)
	{
		x=xValue;
	}
	public int getX()
	{
		return x;
	}
	public void setY(int yValue)
	{
		y=yValue;
	}
	public int getY()
	{
		return y;
	}
	public void moveLeft()
	{
		x-=SHIFT;
		repaint();
	}
	public void moveRight()
	{
		x+=SHIFT;
		repaint();
	}
	public void moveUp()
	{
		y-=SHIFT;
		repaint();
	}
	public void moveDown()
	{
		y+=SHIFT;
		repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawOval(x, y, DIAMETER, DIAMETER);
	}
	//is this needed?
	public Dimension getPerferredSize()
	{
		return new Dimension(MAX_X, MAX_Y);
	}
}

		