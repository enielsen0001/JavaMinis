/*Program: TextBox.java
 * Programmer: Erika Nielsen
 * Purpose: Use radio buttons to change text box background color and buttons to move text in text box.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


@SuppressWarnings("serial")
public class TextBox extends JFrame
{
	//declare radio buttons
	private JRadioButton jrbRed, jrbYellow, jrbWhite, jrbGray, jrbGreen;
	
	//create a radio button group
	private ButtonGroup btg=new ButtonGroup();
	
	//create a message display panel
	private MessagePanel message=new MessagePanel("Welcome to Java");
	
	/**main method*/
	public static void main(String[]args)
	{
		TextBox frame=new TextBox();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	/**default constructor*/
	public TextBox()
	{
		setTitle("Alter the Textbox");
		//add button group to panel1 
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(jrbRed=new JRadioButton("Red"));
		p1.add(jrbYellow=new JRadioButton("Yellow"));
		p1.add(jrbWhite=new JRadioButton("White"));
		p1.add(jrbGray=new JRadioButton("Gray"));
		p1.add(jrbGreen=new JRadioButton("Green"));
		
		//set keyboard mnemonics
		jrbRed.setMnemonic('R');
		jrbYellow.setMnemonic('Y');
		jrbWhite.setMnemonic('T');
		jrbGray.setMnemonic('A');
		jrbGreen.setMnemonic('G');
		
		//group radio buttons
		btg.add(jrbRed);
		btg.add(jrbYellow);
		btg.add(jrbWhite);
		btg.add(jrbGray);
		btg.add(jrbGreen);
		
		//put put message panel in panel p2
		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(message);
		
		//place panel p1 and p2 into frame
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		
		//register listeners for check boxes
		jrbRed.addItemListener(new MyItemEventListener());
		jrbYellow.addItemListener(new MyItemEventListener());
		jrbWhite.addItemListener(new MyItemEventListener());
		jrbGray.addItemListener(new MyItemEventListener());
		jrbGreen.addItemListener(new MyItemEventListener());
		
		//set initial background to white
		jrbWhite.setSelected(true);
		message.backgroundWhite();
	}
	
	class MyItemEventListener implements ItemListener
	{
		/**handle checkbox events*/
		public void itemStateChanged(ItemEvent e)
		{
			if(jrbRed.isSelected())
			{
				message.backgroundRed();//set red background
			}
			if(jrbYellow.isSelected())
			{
				message.backgroundYellow();//set yellow background
			}
			if(jrbWhite.isSelected())
			{
				message.backgroundWhite();//set white background
			}
			if(jrbGray.isSelected())
			{
				message.backgroundGray();//set grey background
			}
			if(jrbGreen.isSelected())
			{
				message.backgroundGreen();//set green background
			}
		}
	}

//Create message panel class
class MessagePanel extends JPanel
{
	
	//create sub-panels to add to messagePanel 
	JPanel textPanel=new JPanel();
	JPanel buttonPanel=new JPanel();
	JLabel text= new JLabel();
	//set shift 
	private int SHIFT=10;
	
	//default constructor
	public MessagePanel()
	{
	}
	
	public MessagePanel(String message)
	{
		text.setText(message);
		setLayout(new BorderLayout());
	
		//add text and buttons to MessagePanel
		add(textPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		//set size and add JLabel to panel
		textPanel.setPreferredSize(new Dimension(400, 30));
		textPanel.add(text);
		
		//create buttons and add to button panel
		JButton left=new JButton("<=");
		JButton right=new JButton("=>");
		JPanel leftRight=new JPanel();
		leftRight.add(left);
		leftRight.add(right);
		buttonPanel.add(leftRight);

		//register and create listeners for buttons
		left.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//code borrowed from slider example in chapter-doesn't work
				//code using setHorizontalAlignment() didn't work either
				
				//int newX=TextBox.this.message.getWidth()-SHIFT;
				//TextBox.this.message.setXCoordinate(newX);
			///
			/*if()
				{
					JOptionPane.showMessageDialog(null,"You have reached the edge of the text area");
					text.setHorizontalAlignment(0);
				}
			*/}
		});
		
		right.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				moveRight();
				/*if()
				{
					JOptionPane.showMessageDialog(null,"You have reached the edge of the text area");
					text.setHorizontalAlignment(0);
				}
				*/}
		});
	}
	/*	
	//create methods to handle  radio button events
	/**set red background*/
	public void backgroundRed()
	{
		textPanel.setBackground(Color.RED);
		repaint();
	}
	/**set yellow background*/
	public void backgroundYellow()
	{
		textPanel.setBackground(Color.YELLOW);
		repaint();
	}
	/**set white background*/
	public void backgroundWhite()
	{
		textPanel.setBackground(Color.WHITE);
		repaint();
	}
	/**set gray background*/
	public void backgroundGray()
	{
		textPanel.setBackground(Color.GRAY);
		repaint();
	}
	/**set green background*/
	public void backgroundGreen()
	{
		textPanel.setBackground(Color.GREEN);
		repaint();
	}
	
	//create methods to move text
	public void moveLeft()
	{
	}
	
	public void moveRight()
	{
	}
}
}



