import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;

import java.awt.CardLayout;
import javax.swing.JButton;
//import java.awt.Panel;
//import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;



	


class mainGui extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnLogin = new JButton("Login");
	private JButton btnLogin1 = new JButton("!Login");
	private JButton btnCreat = new JButton("Create an Account");
	public  static JPanel displayPanel = new JPanel();
	public  static CardLayout cards = new CardLayout();
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textFielde2;
	public String choicePane2 ;
	public static JTextArea textArea=new JTextArea();
	public static Choice choice = new Choice();
	public static Choice choicee1 = new Choice();
	public static JScrollPane jp;
	public static JScrollPane jp2;
	public static JTextField textFielde1 = new JTextField();
	public static JTextArea textAreae1 = new JTextArea();
	
	public mainGui(String title){
		super(title);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FileMenuHandler fmh = new FileMenuHandler(this);
		btnLogin.addActionListener(this);
		btnLogin1.addActionListener(this);
		
	//	colorList.addListSelectionListener(this);
		displayPanel.setLayout(cards);
		
		
		
			JPanel e = new JPanel();
			
			e.setLayout(null);
			
			
			btnLogin.setBounds(157, 148, 89, 23);
			e.add(btnLogin);
			
			btnLogin1.setBounds(170,160,100,45);
			e.add(btnLogin1);
			
			btnCreat.setBounds(200,200,200,150);
			e.add(btnCreat);
			btnCreat.addActionListener(fmh);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 750, 750);
			setLocationRelativeTo(null);
			e = new JPanel();
			e.setBorder(new EmptyBorder(5, 5, 5, 5));
			
			e.setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(299, 134, 226, 31);
			e.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(299, 204, 226, 31);
			e.add(textField_1);
			
			JButton btnNewButton = new JButton("Login");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton.setBounds(299, 257, 226, 45);
			e.add(btnNewButton);
			btnNewButton.addActionListener(fmh);
			
			JButton btnCreatAccount = new JButton("Creat Account");
			btnCreatAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnCreatAccount.setBounds(299, 396, 226, 45);
			e.add(btnCreatAccount);
			btnCreatAccount.addActionListener(fmh);
			
			JLabel lblNewLabel = new JLabel("User Name");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(188, 132, 101, 31);
			e.add(lblNewLabel);
			
			
			
			JLabel lblPassword = new JLabel("PassWord");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPassword.setBounds(188, 202, 101, 31);
			e.add(lblPassword);
			
			JLabel lblLogin = new JLabel("Login");
			lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblLogin.setBounds(188, 264, 101, 31);
			e.add(lblLogin);
			
			
			JLabel lblFirstTimeCreat = new JLabel("First Time? Creat an Account now!! FREE");
			lblFirstTimeCreat.setBounds(295, 354, 299, 31);
			e.add(lblFirstTimeCreat);
			
			
			JLabel lblReviewDatabase = new JLabel("Customer Reviews DataBase");
			lblReviewDatabase.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblReviewDatabase.setBounds(170, 49, 485, 49);
			e.add(lblReviewDatabase);
			
			//e1
			JPanel e1 = new JPanel();
			e1.setLayout(null);
			
			textFielde1.setBounds(141, 10, 450, 23);
			e1.add(textFielde1);
			textFielde1.setColumns(10);
			
			
			choicee1.setBounds(22, 11, 107, 25);
			//(22, 11, 107, 25);
			e1.add(choicee1);
			choicee1.add("Games");
			choicee1.add("Computer");
			
			JButton buttone1 = new JButton("views!");
			buttone1.setBounds(590, 9, 140, 23);
			e1.add(buttone1);
			buttone1.addActionListener(fmh);
			
			JButton button_1e1 = new JButton("Add");
			button_1e1.setBounds(590, 54, 140, 23);
			e1.add(button_1e1);
			button_1e1.addActionListener(fmh);
			
			JButton button_2e1 = new JButton("RemoveUser");
			button_2e1.setBounds(590, 101, 140, 23);
			e1.add(button_2e1);
			button_2e1.addActionListener(fmh);
			
			JButton button_3e1 = new JButton("ExportMember");
			button_3e1.setBounds(590, 147, 140, 23);
			//button_3e1.setHorizontalAlignment(SwingConstants.LEFT);
			e1.add(button_3e1);
			button_3e1.addActionListener(fmh);
			
			JButton button_4e1 = new JButton("ExportGames");
			button_4e1.setBounds(590, 198, 140, 23);
			e1.add(button_4e1);
			button_4e1.addActionListener(fmh);
			
			JButton button_5e1 = new JButton("ExportGameReview");
			button_5e1.setBounds(590, 248, 140, 23);
			button_5e1.setHorizontalAlignment(SwingConstants.LEFT);
			e1.add(button_5e1);
			button_5e1.addActionListener(fmh);
			JButton button_6e1 = new JButton("ImportGames");
			
			button_6e1.setBounds(590, 299, 140, 23);
			e1.add(button_6e1);
			button_6e1.addActionListener(fmh);
			JButton button_7e1 = new JButton("ImportGameReviews");
			button_7e1.setHorizontalAlignment(SwingConstants.LEFT);
			button_7e1.setBounds(590, 351, 140, 23);
			
			
			
			

			textAreae1.setLineWrap(true);
			textAreae1.setBackground(Color.LIGHT_GRAY);
			 jp2 = new JScrollPane(textAreae1);
			 jp2.setBounds(22, 51, 560, 623);
			 
			jp2.getVerticalScrollBar().setUnitIncrement(50);
			jp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			textArea.setEditable(false);
			
			
			
			e1.add(jp2);
			
			//e2
			
			JPanel e2 = new JPanel();
			e2.setLayout(null);
			JButton btnGo = new JButton("Reviews!");
			btnGo.setBounds(556, 11, 85, 23);
			e2.add(btnGo);
			btnGo.addActionListener(fmh);
	
			
			textFielde2 = new JTextField();
			textFielde2.setBounds(135, 12, 417, 25);
			e2.add(textFielde2);
			textFielde2.setColumns(10);
			
			
			choice.setBounds(22, 11, 107, 25);
			e2.add(choice);
			
			choice.add("Games");
			choice.add("Computer");
			
			
			
			
			
			
			textArea.setLineWrap(true);
			textArea.setBackground(Color.LIGHT_GRAY);
			 jp = new JScrollPane(textArea);
			 jp.setBounds(22, 51, 696, 623);
			 
			jp.getVerticalScrollBar().setUnitIncrement(50);
			jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			textArea.setEditable(false);
			
			
			
			e2.add(jp);
			
			
			
			JButton btnNewButtone2 = new JButton("Add");
			btnNewButtone2.setBounds(660, 11, 65, 23);
			e2.add(btnNewButtone2);
			btnNewButtone2.addActionListener(fmh);
			
	//end of e2
			displayPanel.add(e,"e");
			displayPanel.add(e1,"1");
			displayPanel.add(e2,"2");
		
	
		Container pane = getContentPane();
		pane.add(displayPanel,BorderLayout.CENTER);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	if(arg0.getSource()==btnLogin){
		cards.next(displayPanel);

	
	}else if(arg0.getSource()==btnLogin1){
		cards.previous(displayPanel);
		
	}
}
}

