import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/*Program: UserPanel.java
 * Programmer: Erika Nielsen
 * Purpose: to set properties of a flowlayout panel using user input
 */
@SuppressWarnings("serial")
public class UserPanel extends JApplet
{
	//declare components
	private JPanel buttonPanel, inputPanel;
	@SuppressWarnings("rawtypes")
	public JComboBox alignment;
	private String[] comboArray={"LEFT", "CENTER", "RIGHT"};
	public JTextField hField, vField;
	private JLabel hFieldLabel, vFieldLabel, alignmentLabel;
	int i, hGap, vGap;
	JButton[] jbts = new JButton[15];
	FlowLayout flow = new FlowLayout();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//constructor
	public UserPanel()
	{
		//set up button panel
		//set border and layout
		buttonPanel=new JPanel(new FlowLayout());
		buttonPanel.setBorder(new TitledBorder("A Container of FlowLayout"));
		buttonPanel.setPreferredSize(null);
		//create buttons
		for (i = 0; i < jbts.length; i++)
			jbts[i] = new JButton("Component "+i);
		
		//add buttons to panel
		for(i=0; i<jbts.length;i++)
			buttonPanel.add(jbts[i]);
		
		//set up input panel
		//set border and layout
		inputPanel=new JPanel(new GridBagLayout());
		GridBagConstraints gbcHlabel = new GridBagConstraints();
		GridBagConstraints gbcVlabel=new GridBagConstraints();
		GridBagConstraints gbcComboLabel=new GridBagConstraints();
		GridBagConstraints gbcComboField=new GridBagConstraints();
		GridBagConstraints gbcHfield=new GridBagConstraints();
		GridBagConstraints gbcVfield=new GridBagConstraints();
		inputPanel.setBorder(new TitledBorder("FlowLayout Properties"));
		inputPanel.setPreferredSize(null);
		
		//create components
		hField=new JTextField();
		vField=new JTextField();
		alignment=new JComboBox(comboArray);
		hFieldLabel=new JLabel("HGap");
		vFieldLabel=new JLabel("VGap");
		alignmentLabel=new JLabel("Alignment");
		
		//set GridBagLayout constraints
		gbcHlabel.gridx=0;
		gbcHlabel.gridy=1;
		gbcHlabel.anchor=GridBagConstraints.WEST;
		
		gbcVlabel.gridx=0;
		gbcVlabel.gridy=2;
		gbcVlabel.anchor=GridBagConstraints.WEST;
		
		gbcComboLabel.gridx=0;
		gbcComboLabel.gridy=0;
		gbcComboLabel.anchor=GridBagConstraints.WEST;
		
		gbcHfield.gridx=1;
		gbcHfield.weightx=1;
		gbcHfield.gridy=1;
		gbcHfield.gridwidth=2;
		gbcHfield.fill=GridBagConstraints.HORIZONTAL;
		
		gbcVfield.gridx=1;
		gbcVfield.gridy=2;
		gbcVfield.gridwidth=2;
		gbcVfield.fill=GridBagConstraints.HORIZONTAL;
		
		gbcComboField.gridx=1;
		gbcComboField.gridy=0;
		gbcComboField.gridwidth=2;
		gbcComboField.fill=GridBagConstraints.HORIZONTAL;
		
		//add components
		inputPanel.add(alignmentLabel, gbcComboLabel);
		inputPanel.add(alignment, gbcComboField);
		inputPanel.add(hFieldLabel, gbcHlabel);
		inputPanel.add(hField, gbcHfield);
		inputPanel.add(vFieldLabel, gbcVlabel);
		inputPanel.add(vField, gbcVfield);
		
		//add panels to frame
		add(buttonPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
		
		//add listeners to components
		hField.addActionListener(new HgapListener());
		vField.addActionListener(new VgapListener());
		alignment.addActionListener(new ComboListener());
				
	}
	
	public static void main(String[]args)
	{
		UserPanel applet = new UserPanel();
		JFrame frame=new JFrame();
		frame.setTitle("You Do It");
		frame.add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}
	
	//create listener classes 
		class ComboListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if((String)alignment.getSelectedItem()==comboArray[0])
					{
					flow.setAlignment(FlowLayout.LEFT);
					buttonPanel.setLayout(flow);
					}
				else if((String)alignment.getSelectedItem()==comboArray[1])
					{
					flow.setAlignment(FlowLayout.CENTER);
					buttonPanel.setLayout(flow);
					}
				else if((String)alignment.getSelectedItem()==comboArray[2])
					{
					flow.setAlignment(FlowLayout.LEFT);
					buttonPanel.setLayout(flow);
					}
			}
		}
			
		class HgapListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				flow.setHgap((Integer.parseInt(hField.getText().trim())));
				buttonPanel.setLayout(flow);
			}			
		}
			
		class VgapListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				flow.setVgap((Integer.parseInt(vField.getText().trim())));
				buttonPanel.setLayout(flow);
			}
		}
}