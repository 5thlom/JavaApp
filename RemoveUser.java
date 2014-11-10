import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class RemoveUser extends JFrame{

	private JPanel contentPane;
	private FileMenuHandler fmh = new FileMenuHandler(this);
	public static boolean isOn = false;
	public static  JTextField textField = new JTextField(); 
	//remove user, this will remove a user form member table
	RemoveUser(){
			//setType(Type.UTILITY);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 374, 209);
			contentPane = new JPanel();
			setTitle("Remove Member");
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel lblMemberid = new JLabel("MemberID:");
			lblMemberid.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMemberid.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblMemberid.setBounds(10, 40, 109, 34);
			contentPane.add(lblMemberid);

			
			textField.setColumns(10);
			textField.setBounds(129, 40, 222, 34);
			contentPane.add(textField);

			JButton btnRemove = new JButton("Remove");
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnRemove.setBounds(66, 103, 109, 38);
			contentPane.add(btnRemove);

			// remove button listener under remove member
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (textField.getText().isEmpty()) {//if the textfield is empty, we stop it.
						JOptionPane.showMessageDialog(null,
								"Please enter a Member ID");
						return;
					}
					String un = textField.getText();
					if(FileMenuHandler.accountExist(0)==true){
						JOptionPane.showMessageDialog(null, "invaild username");
						return;
					}
					ConnectMYSQL dbconnect = new ConnectMYSQL();
					
					
					try {

						ConnectMYSQL.pst = dbconnect.con
								.prepareStatement("delete from gamereviews where username ="+un);//delete the table with forign key data;
					//	dbconnect.pst.setInt(1, memberid);
						
						
						ConnectMYSQL.pst.executeUpdate();
						ConnectMYSQL.pst = dbconnect.con
								.prepareStatement("delete from members where username ="+un);//delte the table data with the primary key data;
						ConnectMYSQL.pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Member removed");
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						dbconnect.close();
					}

				}
			});
			//button cancel
			JButton button_1 = new JButton("Cancel");
			button_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			button_1.setBounds(185, 103, 109, 38);
			contentPane.add(button_1);
			button_1.addActionListener(fmh);
		}
	}
	
