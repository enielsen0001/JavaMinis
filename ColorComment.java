import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*Program: ColorComment.java
 * Programmer: Erika Nielsen
 * Purpose: Display text using menu and buttons
 */
@SuppressWarnings("serial")
public class ColorComment extends JFrame implements ActionListener 
{
	//declare components
	JButton blue, black, darkGray, gray, red, magenta;
	JPanel mainPanel, buttonPanel;
	final JTextField textBox;
	JFrame frame;
	
	public ColorComment()
	{
		//create panels
		JPanel mainPanel=new JPanel(new BorderLayout());
		JPanel buttonPanel=new JPanel(new GridLayout(3,2));
	
		//create menu bar and add menu elements
		JMenuBar mb=new JMenuBar();		
		this.setJMenuBar(mb);
			
		//file menu 		
		JMenu file=new JMenu("File");
		file.setMnemonic('f');
		mb.add(file);
			
		JMenuItem disp=new JMenuItem("Display");
		disp.setMnemonic('d');
		file.add(disp);
			
		JMenuItem exit=new JMenuItem("Exit");
		exit.setMnemonic('x');
		exit.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	System.exit(0);
		    }
		 });
		file.add(exit);
			
		//edit menu
		JMenu edit=new JMenu("Edit");
		edit.setMnemonic('e');
		mb.add(edit);
			
		JMenuItem clear=new JMenuItem("Clear");
		clear.setMnemonic('c');
		clear.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	textBox.setText(null);
		    	textBox.setBackground(Color.WHITE);
		    }
		 });
		edit.add(clear);
			
		//about menu
		JMenu about=new JMenu("About");
		about.setMnemonic('a');
		mb.add(about);
			
		JMenuItem aboutItem=new JMenuItem("About Color Buttons");
		aboutItem.setMnemonic('b');
		aboutItem.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	JOptionPane.showMessageDialog(null, "These are your favorite colors!");
		    }
		 });
		about.add(aboutItem);
			
		//create buttons
		JButton blue=new JButton("blue");
		blue.setBackground(Color.BLUE);
		blue.setForeground(Color.WHITE);
		blue.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	textBox.setText("blue is such a lovely color");
				textBox.setBackground(Color.BLUE);
				textBox.setForeground(Color.WHITE);
		    }
		});
	
		JButton black=new JButton("black");
		black.setBackground(Color.BLACK);
		black.setForeground(Color.WHITE);
		black.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	textBox.setText("black is such a mysterious color");
				textBox.setBackground(Color.BLACK);
				textBox.setForeground(Color.WHITE);
		    }
		});
	
		JButton darkGray=new JButton("darkGray");
		darkGray.setBackground(Color.DARK_GRAY);
		darkGray.setForeground(Color.WHITE);
		darkGray.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	textBox.setText("dark gray is such a brooding color");
				textBox.setBackground(Color.DARK_GRAY);
				textBox.setForeground(Color.WHITE);
		    }
		});
	
		JButton gray=new JButton("gray");
		gray.setBackground(Color.GRAY);
		gray.setForeground(Color.WHITE);
		gray.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	textBox.setText("gray is such a soft color");
				textBox.setBackground(Color.GRAY);
				textBox.setForeground(Color.WHITE);
		    }
		});
	
		JButton red=new JButton("red");
		red.setBackground(Color.RED);
		red.setForeground(Color.WHITE);
		red.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	textBox.setText("red is such a vibrant color");
				textBox.setBackground(Color.RED);
				textBox.setForeground(Color.WHITE);
		    }
		});
	
		JButton magenta=new JButton("magenta");
		magenta.setBackground(Color.MAGENTA);
		magenta.setForeground(Color.WHITE);
		magenta.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	textBox.setText("magenta is such a festive color");
				textBox.setBackground(Color.MAGENTA);
				textBox.setForeground(Color.WHITE);
		    }
		});
	
		//create textbox and label
		textBox=new JTextField(null);
		
		JLabel welcome=new JLabel("Welcome to Your Favorite Colors!");
		welcome.setBackground(Color.GRAY);
		welcome.setForeground(Color.BLACK);
		
		//add buttons to buttonPanel
		buttonPanel.add(blue);
		buttonPanel.add(black);
		buttonPanel.add(darkGray);
		buttonPanel.add(gray);
		buttonPanel.add(red);
		buttonPanel.add(magenta);
		
	
		//add components to panel
		mainPanel.add(textBox, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		mainPanel.add(welcome, BorderLayout.SOUTH);
	
		//add mainPanel 
		add(mainPanel);
	}
	
	
	public static void main(String[]args)
	{
		ColorComment frame=new ColorComment();
		frame.setTitle("Colors Speak");
		frame.setSize(400,320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}


