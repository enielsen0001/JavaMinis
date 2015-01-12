/*Program: ShowCards.java
 * Programmer: Erika Nielsen
 * Purpose: display entire deck of playing cards in random order
 */
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShowCards extends JFrame
{
	ShowCards()
	{
		setLayout(new GridLayout(6,9));
		//create an array for 54 numbers
		int list[]=new int[54];
		//use a for loop to set the value of each element to equal 1 higher than its index and shuffle
		for(int i=0;i<list.length;i++)
			list[i]=i+1;
		shuffle(list);
		//use a for loop to add a new label with an ImageIcon of each card
				for(int i=0;i<list.length;i++)
				{
					JLabel card=new JLabel(new ImageIcon("image/card/"+list[i]+".png"));
					add(card);
				}
	}
		//create shuffle method to shuffle cards
		public static void shuffle(int[] tempList)
		{
			for(int i=0;i<tempList.length;i++)
			{
				int index=(int)(Math.random()*tempList.length);//create a random index number
				int temp=tempList[i];//temp int is set to current list[i]
				tempList[i]=tempList[index];//current list element is set to random number
				tempList[index]=temp;//swap old element value with temp
			}
		}
	public static void main(String[]args)
	{
		ShowCards frame=new ShowCards();
		frame.setTitle("Showing You Some Cards");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
