/*Program: ButtonsPanel.java
 * Programmer: Erika Nielsen
 * Purpose: To display two sets of three buttons in a frame using panels (GUI)
 */
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ButtonsPanel extends JFrame{
	ButtonsPanel()
	{
		//create panels and add buttons
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		panel1.add(new JButton("Button 1"));
		panel1.add(new JButton("Button 2"));
		panel1.add(new JButton("Button 3"));
		panel2.add(new JButton("Button 4"));
		panel2.add(new JButton("Button 5"));
		panel2.add(new JButton("Button 6"));
		//set layout
		setLayout(new FlowLayout());
		//add panels to frame-border layout is default
		add(panel1);
		add(panel2);
	}
	public static void main(String[]args)
	{
		//create frame 
		ButtonsPanel frame=new ButtonsPanel();
		frame.setTitle("Panel of Buttons");
		frame.pack();//fit to size of buttons
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
