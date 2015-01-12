/*Program: JoinFiles.java
 * Programmer: Erika Nielsen
 * Purpose: to join multiple files into one target file
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JoinFiles extends JFrame
{
	//create components
	private JTextField jtfInputFile=new JTextField(20);
	private JTextField jtfNumberOfFiles=new JTextField(2);
	private JButton jbtStart=new JButton("Start");
	
	public JoinFiles()
	{
		//create panel1 and add components
		JPanel panel1=new JPanel(new BorderLayout());
		panel1.add(new JLabel("Enter a base file: "),BorderLayout.WEST);
		panel1.add(jtfInputFile,BorderLayout.CENTER);
		//create panel2 and add components
		JPanel panel2=new JPanel(new BorderLayout());
		panel2.add(new JLabel("Specify the numebr of smaller files: "),BorderLayout.WEST);
		panel2.add(jtfNumberOfFiles,BorderLayout.CENTER);
		//create third panel, text area and set text wrapping
		JPanel panel=new JPanel(new GridLayout(4,1));
		JTextArea jta=new JTextArea("If the base file is named temp.txt with three pieces, the three smaller files temp.txt.1, temp.txt.2, and temp.txt.3 are combined into temp.txt");
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		//add text area, panels and jbtStart button to last panel
		panel.add(jta);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(jbtStart);
		//add panel to frame
		add(panel);
		//add listener to the start button and invoke the appropriate methods for the action
		jbtStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//create and fill target file
				joinFile(jtfInputFile.getText(),Integer.parseInt(jtfNumberOfFiles.getText()));
			}
		});
		
	}
	public void joinFile(String filename, int numberOfPieces)
	{
		try
		{
			try
			{
				//construct new BufferedOutputStream used to create target file 
				BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(new File(filename), true));
				for(int i=1;i<=numberOfPieces;i++)
				{
					//construct a BufferedInputStream to check for the files to be joined
					BufferedInputStream input=new BufferedInputStream(new FileInputStream(new File(filename+"."+i)));
					//create value to determine EOF
					int value;
					while((value=input.read())!=-1)
					{
						//write bytes to output buffer
						output.write(value);
					}
					input.close();
				}
				output.close();
			}
			finally
			{
				//Pointless filler
				System.out.println(" ");
			}
		}
		catch(IOException ex)
		{
			System.out.println("You have an ERROR");
			ex.printStackTrace();
		}
	}

	public static void main(String[]args)
	{
		JoinFiles frame=new JoinFiles();
		frame.setSize(500, 200);
		frame.setTitle("Merge Files ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//center the frame
		frame.setVisible(true);
	}
}

	