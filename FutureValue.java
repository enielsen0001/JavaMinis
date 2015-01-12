import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/*Program: FutureValue.java
 * Programmer: Erika Nielsen
 * Purpose: To calculate the future value of an investment at a given interest rate for a specified number of years.
 */
@SuppressWarnings("serial")
public class FutureValue extends JFrame implements ActionListener
{
	//declarations
	JPanel mainPanel, fieldsPanel;
	JButton cal;
	JLabel inv, year, anl, fut;
	JTextField invT, yearT, anlT, futT;
	double futureValue;
	JMenuItem calc, exit, help, about;
	
	public FutureValue()
	{
		try
		{
		//create panels
		mainPanel=new JPanel(new BorderLayout());
		fieldsPanel=new JPanel(new GridLayout(4,2));
			
		//create menu bar and add menu elements
		JMenuBar mb=new JMenuBar();		
		this.setJMenuBar(mb);
					
		//operation menu 		
		JMenu opp=new JMenu("Operation");
		mb.add(opp);
		
		JMenuItem calc=new JMenuItem("Calculate");
		//calc.addActionListener(new MyActionListener());
		calc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				futureValue=(Integer.parseInt(invT.getText().trim()))*Math.pow((1+(((Double.parseDouble(anlT.getText().trim()))/12)/100)),(((Integer.parseInt(yearT.getText().trim()))*12)));
				NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
				futT.setText((n.format(futureValue)));
			}
		});
		opp.add(calc);
		opp.add(new JSeparator());
		
		JMenuItem exit=new JMenuItem("Exit");
		//exit.addActionListener(new MyActionListener()); 
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		opp.add(exit);
		
		//help menu
		JMenuItem help=new JMenu("Help");
		mb.add(help);
		
		JMenuItem about=new JMenuItem("About");
		//about.addActionListener(new MyActionListener());
		about.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Compute Future Investment Value", "About this Program", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		help.add(about);
		
		//create JLabels
		inv=new JLabel("Investment Amount");
		year=new JLabel("Years");
		anl=new JLabel("Annual Interest Rate");
		fut=new JLabel("Future Value");
		
		//create JTextFields
		invT=new JTextField();
		yearT=new JTextField();
		anlT=new JTextField();
		futT=new JTextField();
		futT.setBackground(Color.LIGHT_GRAY);
		
		//create JButton
		cal=new JButton("Calculate");
		//cal.addActionListener(new MyActionListener());
		cal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				futureValue=(Integer.parseInt(invT.getText().trim()))*Math.pow((1+(((Double.parseDouble(anlT.getText().trim()))/12)/100)),(((Integer.parseInt(yearT.getText().trim()))*12)));
				NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
				futT.setText((n.format(futureValue)));
			}
		});
		
		//add components to panels and panels to frames
		fieldsPanel.add(inv);
		fieldsPanel.add(invT);
		fieldsPanel.add(year);
		fieldsPanel.add(yearT);
		fieldsPanel.add(anl);
		fieldsPanel.add(anlT);
		fieldsPanel.add(fut);
		fieldsPanel.add(futT);
		
		mainPanel.add(fieldsPanel,BorderLayout.CENTER);
		mainPanel.add(cal, BorderLayout.SOUTH);
		
		add(mainPanel);
	}
	catch (Exception e) 
	{
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        invT.setText(null);
        yearT.setText(null);
        anlT.setText(null);
        futT.setText(null);
    }
	}
	
	public static void main(String[]args)
	{
		FutureValue frame=new FutureValue();
		frame.setTitle("Calculate Future Value");
		frame.setSize(275,180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub to make the IDE happy
		
	}
}
	
	
	/*//listener class
	class MyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==cal||e.getSource()==calc)
			{
				futureValue=(Integer.parseInt(invT.getText().trim()))*Math.pow((1+(((Double.parseDouble(anlT.getText().trim()))/12)/100)),(((Integer.parseInt(yearT.getText().trim()))*12)));
				NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
				futT.setText((n.format(futureValue)));
			}
			else if(e.getSource()==exit)
			{
				System.exit(0);
			}
			else if(e.getSource()==about)
			{
				JOptionPane.showMessageDialog(null, "Compute Future Investment Value", "About this Program", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}*/
	
	

