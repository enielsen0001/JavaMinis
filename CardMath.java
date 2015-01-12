import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/*Program: CardMath.java
 * Programmer:  Erika Nielsen
 * Purpose:  To both apply math to the numerical equivalences of four cards and to verify if user entered maths for a sum of 24 are correct.
 */

@SuppressWarnings("serial")
public class CardMath extends JApplet implements ActionListener{
	
	//create components
	
	//create 4 panels
	JPanel solutionPanel=new JPanel(new BorderLayout(5,10));
	JPanel cardsPanel=new JPanel(new GridLayout(1,4));
	JPanel expressionPanel=new JPanel(new BorderLayout(5,10));
	
	//create buttons
	JButton jbtSolution=new JButton("Find a Solution");
	JButton jbtRefresh=new JButton("Refresh");
	JButton jbtVerify=new JButton("Verify");
	
	//create text fields
	JTextField jtfSolution=new JTextField();
	JTextField jtfExpression=new JTextField();
	
	//create labels
	JLabel jlblExpression=new JLabel("Enter an expression:");
	private JLabel jlblCard1=new JLabel();
	private JLabel jlblCard2=new JLabel();
	private JLabel jlblCard3=new JLabel();
	private JLabel jlblCard4=new JLabel();
	
	//create array for cards and card values
	private ImageIcon[] cardIcons=new ImageIcon[52];
	private ArrayList<Integer> list=new ArrayList<Integer>();
	private ArrayList<Integer> currentCardValues=new ArrayList<Integer>();
	
	public CardMath()
	{
		//load values into array
		for(int i=0;i<52;i++)
			list.add(i);
		
		//load card images into array
		for(int i=0;i<52;i++)
			cardIcons[i]=new ImageIcon("image/card/"+(i+1)+".png");
		
		//add components to panels
		solutionPanel.add(jbtSolution, BorderLayout.WEST);
		solutionPanel.add(jtfSolution, BorderLayout.CENTER);
		solutionPanel.add(jbtRefresh, BorderLayout.EAST);
		solutionPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		
		// Sets interface variables for solution
		jtfSolution.setEditable(false);
		jtfSolution.setHorizontalAlignment(SwingConstants.LEFT);
		jtfSolution.setColumns(9);
		jtfSolution.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		
		cardsPanel.add(jlblCard1);
		cardsPanel.add(jlblCard2);
		cardsPanel.add(jlblCard3);
		cardsPanel.add(jlblCard4);
		
		expressionPanel.add(jlblExpression, BorderLayout.WEST);
		expressionPanel.add(jtfExpression, BorderLayout.CENTER);
		expressionPanel.add(jbtVerify, BorderLayout.EAST);
		expressionPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		
		//add panels to frame
		add(cardsPanel, BorderLayout.CENTER);
		add(expressionPanel, BorderLayout.SOUTH);
		add(solutionPanel, BorderLayout.NORTH);
		refresh();
		
		//register listeners
		jbtSolution.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jtfSolution.setText(findSolution());
			}
		});
		jbtRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				refresh();
			}
		});
				
		jbtVerify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//check all numbers in the expression are currently selected
				if(!correctNumbers())
				{
					JOptionPane.showMessageDialog(null, "The numbers in the expression don't \nmatch the numbers in the set");
				}
				else
				{
					//check that the expression evaluates to 24
					if(evaluate())
					{
						JOptionPane.showMessageDialog(null, "Correct");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Expression","Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private boolean correctNumbers()
	{
		//get card values from expression
		ArrayList<Integer> valueList=new ArrayList<Integer>();
				
		String expression=jtfExpression.getText();
		String[]numbers=expression.split("[()+-/*]");
				
		for(String s: numbers)
		{
			if(s.length()>0)
			{
				valueList.add(new Integer(s));
			}
		}
				
		Collections.sort(valueList);
		Collections.sort(currentCardValues);
				
		if(valueList.equals(currentCardValues))
			return true;
		else 
			return false;
		}
			
		private boolean evaluate()
		{
			return EvaluateExpression.evaluateExpression(jtfExpression.getText().trim())==24;
		}
			
		private void refresh()
		{
			Collections.shuffle(list);
				
			int index1=list.get(0);
			int index2=list.get(1);
			int index3=list.get(2);
			int index4=list.get(3);
				
			currentCardValues.clear();
			currentCardValues.add(index1%13+1);
			currentCardValues.add(index2%13+1);
			currentCardValues.add(index3%13+1);
			currentCardValues.add(index4%13+1);
				
			jlblCard1.setIcon(cardIcons[index1]);
			jlblCard2.setIcon(cardIcons[index2]);
			jlblCard3.setIcon(cardIcons[index3]);
			jlblCard4.setIcon(cardIcons[index4]);
		}
				
		public static void main(String[]args)
		{
			CardMath applet=new CardMath();
			JFrame frame=new JFrame();
			frame.setDefaultCloseOperation(3);
			frame.setTitle("24-Point Card Game");
			frame.add(applet, BorderLayout.CENTER);
			applet.init();
			applet.start();
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
			
		//inner class
		public static class EvaluateExpression
		{
			/**evaluate an expression*/
			public static int evaluateExpression(String expression)
			{
				//create stack to store operands
				GenrericStack<Integer> operandStack=new GenrericStack<Integer>();
					
				//create stack to store operators
				GenrericStack<Character> operatorStack=new GenrericStack<Character>();
					
				//extract operands and operators
				StringTokenizer tokens=new StringTokenizer(expression,"()+-/*", true);
					
				//phase 1: scan tokens
				while(tokens.hasMoreTokens())
				{
					String token=tokens.nextToken().trim();//extract a token
					if(token.length()==0)//blank space
						continue;//back to while loop to extract next token
					else if(token.charAt(0)=='+'||token.charAt(0)=='-')
					{
						//process all +,-,/,* in the top of the operator stack
						while(!operatorStack.isEmpty()&&(operatorStack.peek()=='+'||operatorStack.peek()=='-'||operatorStack.peek()=='*'||operatorStack.peek()=='/'))
						{
							processAnOperator(operandStack, operatorStack);
						}
							
						//push the + or - to the operator stack 
						operatorStack.push(token.charAt(0));
					}
					else if(token.charAt(0)=='*'||token.charAt(0)=='/')
					{
						while(!operatorStack.isEmpty()&&(operatorStack.peek()=='*'||operatorStack.peek()=='/'))
						{
							processAnOperator(operandStack, operatorStack);
						}
							
						operatorStack.push(token.charAt(0));
					}
					else if(token.trim().charAt(0)=='(')
					{
						operatorStack.push('(');
					}
					else if(token.trim().charAt(0)==')')
					{
						//process all operators in stack until seeing'('
						while(operatorStack.peek()!='(')
						{
							processAnOperator(operandStack, operatorStack);
						}
						operatorStack.pop();//pop the '(' symbol from stack
					}
					else
					{
						//an operand scanned
						//push an operand to stack
						operandStack.push(new Integer(token));
					}
				}
					
				//phase 2: process all the remaining operators in stack
				while(!operatorStack.isEmpty())
				{
					processAnOperator(operandStack, operatorStack);
				}
					
				return operandStack.pop();
			}
			
			/**
			 * process one operator.  take an operator from operatorStack and apply it on the operands in the operand stack					
			 */
			public static void processAnOperator(GenrericStack<Integer> operandStack, GenrericStack<Character> operatorStack)
			{
				char op=operatorStack.pop();
				int op1=operandStack.pop();
				int op2=operandStack.pop();
				if(op=='+')
					operandStack.push(op2+op1);
				else if(op=='-')
				operandStack.push(op2-op1);
				else if(op=='*')
					operandStack.push(op2*op1);
				else if(op=='/')
					operandStack.push(op2/op1);
			}
		}
		
		public String findSolution()
		{
			//from the 4 cards
			int a = currentCardValues.get(0);
			int b = currentCardValues.get(1);
			int c = currentCardValues.get(2);
			int d = currentCardValues.get(3);

			String noSolution = "No solution";
			String solution;
			String[] operators = {"+", "-", "*", "/"};

			//multi-dimensional array that has every possible combination
			int[][] allCombinations = { { a, b, c, d }, { d, a, b, c }, { c, d, a, b }, { b, c, d, a }, { a, b, d, c }, { c, a, b, d }, { d, c, a, b }, { b, d, c, a }, { a, d, c, b }, { b, a, d, c }, { c, b, a, d }, { d, c, b, a }, { a, c, b, d }, { d, a, c, b }, { b, d, a, c }, { c, b, d, a }, { b, a, c, d }, { d, b, a, c }, { c, d, b, a}, { a, c, d, b }, { a, d, b, c }, { c, a, d, b }, { b, c, a, d }, { d, b, c,a } };

				for (String firstOp : operators)
					for (String secondOp : operators)
						for (String thirdOp :operators)
							for (int[] cardNums : allCombinations)
								for (int i = 0; i < 3; i++)
									for (int j = 0; j < 5; j++)
									{
										if (i == 0)
										{
											if (j == 0)
											{
												solution = cardNums[0] + firstOp + cardNums[1] + secondOp + cardNums[2] + thirdOp + cardNums[3]; 
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
											else if (j == 1)
											{
												solution = "(" + cardNums[0] + firstOp + cardNums[1] + ")" + secondOp + cardNums[2] + thirdOp + cardNums[3];
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
											else if (j == 2)
											{
												solution = cardNums[0] + firstOp + "(" + cardNums[1] + secondOp + cardNums[2] + ")" + thirdOp + cardNums[3];
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
											else if (j == 3)
											{
												solution = cardNums[0] + firstOp + cardNums[1] + secondOp + "(" + cardNums[2] + thirdOp + cardNums[3] + ")";
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
											else if (j == 4)
											{
												solution = "(" + cardNums[0] + firstOp + cardNums[1] + ")" + secondOp + "(" + cardNums[2] + thirdOp + cardNums[3] + ")";
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
										}
										else if (i == 1)
										{
											if (j == 0)
											{
												solution = "(" + cardNums[0] + firstOp + cardNums[1] + secondOp + cardNums[2] + ")" + thirdOp + cardNums[3];
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
											else if (j == 1)
											{
												solution = "((" + cardNums[0] + firstOp + cardNums[1] + ")" + secondOp + cardNums[2] + ")" + thirdOp + cardNums[3];
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
											else if (j == 2)
											{
												solution = "(" + cardNums[0] + firstOp + "(" + cardNums[1] + secondOp + cardNums[2] + "))" + thirdOp + cardNums[3];
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
										}
										else if (i == 2)
										{
											if (j == 0) 
											{
												solution = cardNums[0] + firstOp + "("+ cardNums[1] + secondOp + cardNums[2] + thirdOp + cardNums[3] + ")";
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
												}
											else if (j == 1)
											{
												solution = cardNums[0] + firstOp + "(("+cardNums[1] + secondOp+ cardNums[2] + ")" + thirdOp+ cardNums[3] + ")";
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
											}
											else if (j == 2)
											{
												solution = cardNums[0] + firstOp + "("+ cardNums[1] + secondOp + "("+ cardNums[2] + thirdOp+ cardNums[3] + "))";
												if(EvaluateExpression.evaluateExpression(solution) == 24)
													return solution;
												}
											}
										}
				return noSolution;
			}

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			// TODO Auto-generated method stub to make eclipse satisfied
		}
}

		
		
		
		
		
	
	


