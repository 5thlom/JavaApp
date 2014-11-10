import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JTextArea;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

//add reviews for selected items
public class AddReview extends JFrame {

	/**
	 * private datas;
	 */
	private static final long serialVersionUID = 1L;//
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public static int  gid;
	private FileMenuHandler fmh = new FileMenuHandler(this);

	

	/**
	 * Create the frame.
	 */
	public AddReview() {
		//setType(Type.UTILITY);
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 591);
		setLocationRelativeTo(null);
		setTitle("Creat new Review");//settitle
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("ItemName");//lblusername
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));//set font
		lblUsername.setBounds(10, 29, 73, 24);//setbounds
		contentPane.add(lblUsername);//add to pane
		
		textField = new JTextField();//jtextfield
		textField.setBounds(92, 32, 133, 22);//setbounds
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel note = new JLabel("Click checkI to check if the items is available!.");//jlabel
		note.setBounds(92,59,314,14);
		contentPane.add(note);
		
		JButton btnChecki = new JButton("CheckI");
		btnChecki.setBounds(235, 32, 89, 23);
		contentPane.add(btnChecki);
		
		final Choice choice = new Choice();
		choice.setBounds(330, 33, 94, 20);
		contentPane.add(choice);
		choice.add("Games");
		choice.add("Computer");
		
		JLabel label = new JLabel("Title");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 80, 73, 24);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(92, 84, 302, 22);
		contentPane.add(textField_1);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 131, 414, 352);
		textArea.setBackground(Color.LIGHT_GRAY);
		contentPane.add(textArea);
		
		//addactionlistenner for botton btncheck
		btnChecki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get the current selected choices and compare to string computer
				if(choice.getSelectedItem().toString().toLowerCase().equals("computer")){JOptionPane.showMessageDialog(null, "Currently computer database we still working on it...\nGames database is available...");
				return;};
				//call check() see if the selected item is in the database
				if(check()==false){
				JOptionPane.showMessageDialog(null, "Items you selected is not in database yet!");
				}else{
					JOptionPane.showMessageDialog(null, "Found!");
				}
			}
			//a check method to check items if in the database
			private boolean check() {
				ConnectMYSQL dbconnect = new ConnectMYSQL();//connectsql
				String item = textField.getText();//get textfield data
			
				try{
					dbconnect.pst = dbconnect.con.prepareStatement("select * from games where gname=?" );//select all from games
					dbconnect.pst.setString(1,item);
					dbconnect.rs = dbconnect.pst.executeQuery();
					//if there is more rows
					while(dbconnect.rs.next()){
					if(dbconnect.rs.getString(2).equals(item)){//if the column two name is the textfiled data, return true;
						gid = dbconnect.rs.getInt(1);
					return true;
						
						
					}
					}
					
				}catch(SQLException e){
						e.printStackTrace();
					}
				return false;//data not found,
				
			}
			
		});
		
		Button button = new Button("Submit");
		button.setBounds(209, 505, 70, 22);
		contentPane.add(button);
		//buuton for sumbmit, addactionlistenner
		button.addActionListener(new ActionListener(){
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				String s = "\t\t";//s for tad
				String title =//title for title textfiled
						textField_1.getText();
				String end = "\n";//new line
				String body = textArea.getText();//get body text
				ConnectMYSQL dbconnect = new ConnectMYSQL();//connect mysql
				try {
					//insert data into gamerviews table, fllow by each column
					dbconnect.pst = dbconnect.con.prepareStatement("insert into gamereviews(Review,gid,username) values("+"'"
				+s+title+end+body+"',"+"'"+Integer.toString(gid)+"',"+"'"+FileMenuHandler.un+"')");
					dbconnect.pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Database updated");
					mainGui.textArea.setText(null);//set textarea empty after insert a new review
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		Button button_1 = new Button("Cancel");
		button_1.setBounds(302, 505, 70, 22);
		contentPane.add(button_1);
		button_1.addActionListener(fmh);
		
	}
}
