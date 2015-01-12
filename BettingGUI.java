import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*Program: BettingGUI.java
 * Programmer: Erika Nielsen
 * Purpose: to place bets on horses and to download the betting information to a file
 */
@SuppressWarnings("serial")
public class BettingGUI extends JFrame implements ActionListener 
{
	//declare components
	JLabel jlblWelcome=new JLabel("Welcome to Racing at Churchill Downs");
	JTextField jtfLower=new JTextField();
	JTextField jtfUpper=new JTextField();
	JButton jbtPlaceBet=new JButton("Place a Bet");
	JButton[] postArray=new JButton[4];
	JMenuBar menuBar=new JMenuBar();
	JPanel masterPanel=new JPanel();
	JPanel buttonPanel=new JPanel();
	ArrayList<Object> completedBets=new ArrayList<Object>();
	
	public BettingGUI()
	{
		//fill button array and add listeners while we were iterating
		for(int i=0;i<4;i++)
		{
			postArray[i]=new JButton("Post Position "+(i+1));
			postArray[i].setBackground(Color.RED);
			postArray[i].setFont(new Font("Cambria", Font.BOLD, 14));
			postArray[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					for(int i=0;i<4;i++)
					{
						if(e.getSource()==postArray[i])
						{
							jtfUpper.setText("Post Position "+(i+1));
						}
					}
				}
			});
		}
		
		
		//place buttons in panel and adjust look
		buttonPanel.setLayout(new GridLayout(1,4,5,5));
		for(int j=0;j<4;j++)
			buttonPanel.add(postArray[j]);
		
		//adjust look of welcome label
		jlblWelcome.setBackground(Color.GREEN);
		jlblWelcome.setOpaque(true);
		jlblWelcome.setFont(new Font("Cambria", Font.BOLD, 22));
		jlblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		
		//adjust look and feel of bet button and register listener
		jbtPlaceBet.setFont(new Font("Cambria", Font.BOLD, 16));
		jbtPlaceBet.addActionListener(this);
		
		
		//set constraint on text field and set look and feel
		jtfUpper.setEditable(false);
		jtfUpper.setFont(new Font("Cambria", Font.PLAIN, 14));
		jtfLower.setFont(new Font("Cambria", Font.PLAIN, 16));
		
		//add components to master panel
		masterPanel.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.fill=GridBagConstraints.BOTH;
		c.weightx=0.5;
		c.weighty=0.5;
		c.ipady=45;
		c.gridx=0;
		c.gridy=0;
		masterPanel.add(jlblWelcome, c);
		
		c.fill=GridBagConstraints.BOTH;
		c.ipady=0;
		c.gridx=0;
		c.gridy=3;
		masterPanel.add(jtfUpper, c);
		
		c.fill=GridBagConstraints.BOTH;
		c.ipady=25;
		c.gridx=0;
		c.gridy=4;
		masterPanel.add(buttonPanel, c);
		
		c.fill=GridBagConstraints.BOTH;
		c.ipady=0;
		c.gridx=0;
		c.gridy=6;
		masterPanel.add(jtfLower, c);
		
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=7;
		masterPanel.add(jbtPlaceBet, c);
		
		//create menu bar
		JMenu file=new JMenu("File");
		file.setMnemonic('F');
		JMenu edit=new JMenu("Edit");
		edit.setMnemonic('E');
		
		JMenuItem display=new JMenuItem("Display");
		display.setMnemonic('D');
		JMenuItem exit=new JMenuItem("Exit Program");
		exit.setMnemonic('P');
		JMenuItem clear=new JMenuItem("Clear");
		clear.setMnemonic('C');
		
		file.add(display);
		file.add(exit);
		
		edit.add(clear);
		menuBar.add(file);
		menuBar.add(edit);
		
		add(masterPanel, BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);
		
		//add action listeners to JMenu items
		display.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jtfUpper.setText("Choose the post position of the horse in Race 1.  Enter bet in lower field, then press Bet button.");
			}
		});
		
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jtfUpper.setText(null);
				jtfLower.setText(null);
			}
		});
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jbtPlaceBet&&jtfUpper.getText().equals("Post Position 1"))
			{
				try 
				{
					int betPlaced=Integer.parseInt(jtfLower.getText().trim());
					
					jtfUpper.setText("You have placed $"+betPlaced+" on the horse in position 1. Hope your horse wins");
					PlaceBet newBet=new PlaceBet(betPlaced, 1);
					
					completedBets.add(newBet);
					DataOutputStream out=openOutputStream("betsFile.dat");
					writeFile(newBet, out);
					closeFile(out);
					
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(null, "Bets must be placed in whole dollar ammounts", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		else if(e.getSource()==jbtPlaceBet&&jtfUpper.getText().equals("Post Position 2"))
		{
			try 
			{
				int betPlaced=Integer.parseInt(jtfLower.getText().trim());
				
				jtfUpper.setText("You have placed $"+betPlaced+" on the horse in position 2. Hope your horse wins");
				PlaceBet newBet=new PlaceBet(betPlaced, 2);
				
				completedBets.add(newBet);
				DataOutputStream out=openOutputStream("betsFile.dat");
				writeFile(newBet, out);
				closeFile(out);
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "Bets must be placed in whole dollar ammounts", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource()==jbtPlaceBet&&jtfUpper.getText().equals("Post Position 3"))
		{
			try 
			{
				int betPlaced=Integer.parseInt(jtfLower.getText().trim());
				
				jtfUpper.setText("You have placed $"+betPlaced+" on the horse in position 3. Hope your horse wins");
				PlaceBet newBet=new PlaceBet(betPlaced, 3);
				
				completedBets.add(newBet);
				DataOutputStream out=openOutputStream("betsFile.dat");
				writeFile(newBet, out);
				closeFile(out);
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "Bets must be placed in whole dollar ammounts", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource()==jbtPlaceBet&&jtfUpper.getText().equals("Post Position 4"))
		{
			try 
			{
				int betPlaced=Integer.parseInt(jtfLower.getText().trim());
				
				jtfUpper.setText("You have placed $"+betPlaced+" on the horse in position 4. Hope your horse wins");
				PlaceBet newBet=new PlaceBet(betPlaced, 4);
				
				completedBets.add(newBet);
				DataOutputStream out=openOutputStream("betsFile.dat");
				writeFile(newBet, out);
				closeFile(out);
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "Bets must be placed in whole dollar ammounts", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No horse is selcted");
		}
	}
	
	//create objects to store betting data for storage in ArrayList
	public class PlaceBet
	{
		public int bet=0, position=0;
		
		public PlaceBet(int bet, int position)
		{
			this.bet=bet;
			this.position=position;
		}
		
		public void setBet(int bet)
		{
			this.bet=bet;
		}
		
		public void setPosition(int position)
		{
			this.position=position;
		}
		
		public int getBet()
		{
			return bet;
		}
		
		public int getPosition()
		{
			return position;
		}
	}
		
	//create data output stream
	private static DataOutputStream
	openOutputStream(String name)
	{
		DataOutputStream out=null;
		try
		{
			File file=new File("betsFile.dat");
			out=new DataOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(file)));
			return out;
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: Bet not placed", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.print("I/O Exception opening file");
			System.exit(0);
		}
		return out;
	}
	
	private static void writeFile(PlaceBet b, DataOutputStream out)
	{
		try
		{
			out.writeInt(b.bet);
			out.writeInt(b.position);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: Bet not placed", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.print("I/O Exception writing to file");
			System.exit(0);
		}
	}
	
	private static void closeFile(DataOutputStream out)
	{
		try
		{
			out.close();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: Bet not placed", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.print("I/O Exception closing file");
			System.exit(0);
		}
	}
			
	public static void main(String[]args)
	{
		BettingGUI frame=new BettingGUI();
		frame.setTitle("Day at the Races");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,300);
		frame.setVisible(true);
	
	}	
}
		
		
		
		
		
	
	


