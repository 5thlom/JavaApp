import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public static JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	private FileMenuHandler fmh = new FileMenuHandler(this);
	public static boolean isOn = false;

	// table gui for find member options
	public AddUser() {
		//setType(Type.UTILITY);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 594);
		setLocationRelativeTo(null);
		setTitle("Create Account");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setBounds(54, 89, 98, 14);
		contentPane.add(lblLastname);

		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setBounds(54, 55, 98, 14);
		contentPane.add(lblFirstname);

		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(54, 125, 98, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setBounds(54, 165, 98, 14);
		contentPane.add(lblPassword);

		JLabel lblComfirm = new JLabel("Region");
		lblComfirm.setHorizontalAlignment(SwingConstants.LEFT);
		lblComfirm.setBounds(54, 205, 121, 14);
		contentPane.add(lblComfirm);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(54, 243, 98, 14);
		contentPane.add(lblEmail);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(54, 285, 98, 14);
		contentPane.add(lblAddress);

		JLabel lblState = new JLabel("City");
		lblState.setBounds(54, 328, 98, 14);
		contentPane.add(lblState);

		JLabel lblState_1 = new JLabel("State");
		lblState_1.setBounds(54, 370, 98, 14);
		contentPane.add(lblState_1);

		JLabel lblZipcode = new JLabel("ZipCode");
		lblZipcode.setBounds(54, 416, 98, 14);
		contentPane.add(lblZipcode);

		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setBounds(54, 454, 98, 14);
		contentPane.add(lblDateOfBirth);

		textField = new JTextField();
		textField.setBounds(174, 52, 224, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(173, 86, 225, 20);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(174, 122, 120, 20);
		contentPane.add(textField_2);

		JButton check = new JButton("Check");
		check.setBounds(300, 122, 100, 20);
		contentPane.add(check);

		setResizable(false);
		// }

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(173, 162, 225, 20);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(174, 202, 224, 20);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(173, 240, 225, 20);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(173, 282, 225, 20);
		contentPane.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(174, 325, 224, 20);
		contentPane.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(173, 367, 225, 20);
		contentPane.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(174, 413, 224, 20);
		contentPane.add(textField_9);

		// choice design for date of birth
		// month choices
		final Choice choice = new Choice();
		choice.setBounds(173, 451, 55, 20);
		contentPane.add(choice);
		for (int i = 1; i <= 9; i++) {
			choice.add("0" + i);
		}
		choice.add("10");
		choice.add("11");
		choice.add("12");

		// day choices
		final Choice choice_1 = new Choice();
		choice_1.setBounds(244, 451, 55, 20);
		contentPane.add(choice_1);
		for (int i = 1; i <= 9; i++) {
			choice_1.add("0" + i);
		}

		for (int i = 10; i < 32; i++) {
			choice_1.add(i + "");
		}

		// year choices
		final Choice choice_2 = new Choice();
		choice_2.setBounds(315, 451, 75, 20);
		contentPane.add(choice_2);
		for (int i = 2013; i >= 1925; i--) {
			choice_2.add(i + "");
		}

		JLabel lblDatabaseAccount = new JLabel("DataBase Account ");
		lblDatabaseAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatabaseAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseAccount.setBounds(120, 11, 174, 14);
		contentPane.add(lblDatabaseAccount);

		JLabel lblNoteAllData = new JLabel(
				"Note: All Data needed to creat account.");
		lblNoteAllData.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoteAllData.setBounds(198, 531, 236, 14);
		contentPane.add(lblNoteAllData);
		// button create
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(242, 497, 73, 23);
		contentPane.add(btnCreate);
		// button cancel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(325, 497, 73, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(fmh);
		
		// check actionlistener
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {
				// check if account is already in use
				if (FileMenuHandler.accountExist(1) == false) {
					JOptionPane
							.showMessageDialog(null, "Username Unavailable!");
				} else {
					JOptionPane.showMessageDialog(null, "Username available!");
				}
			}
		});

		// button create actionlistener
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// connect mysql
				ConnectMYSQL dbconnect = new ConnectMYSQL();
				try {

					// make sure all the informations are filled
					if (textField.getText().isEmpty()
							|| textField_1.getText().isEmpty()
							|| textField_2.getText().isEmpty()
							|| textField_3.getText().isEmpty()
							|| textField_4.getText().isEmpty()
							|| textField_5.getText().isEmpty()
							|| textField_6.getText().isEmpty()
							|| textField_8.getText().isEmpty()
							|| textField_9.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Please fill up all information");
						return;
					}
					// get the text from the member form and add into
					// database
					System.out.println("here");
					dbconnect.pst = dbconnect.con
							.prepareStatement("insert into  members(type, firstname, lastname, username,password,region,email,address, city, state, zip, dob) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					System.out.print("te;");
					// make sure the number id is an integer
					// update database after all field filled.
					dbconnect.pst.setString(1, "NORMAL");
					dbconnect.pst.setString(2, textField.getText());// firstname
					dbconnect.pst.setString(3, textField_1.getText());// lastname
					dbconnect.pst.setString(4, textField_2.getText());// username
					dbconnect.pst.setString(5, textField_3.getText());// password

					dbconnect.pst.setString(6, textField_4.getText());// region
					dbconnect.pst.setString(7, textField_5.getText());// email
					dbconnect.pst.setString(8, textField_6.getText());// address
					dbconnect.pst.setString(9, textField_7.getText());// city
					dbconnect.pst.setString(10, textField_8.getText());// state
					// dbconnect.pst.setString(11,textField_9.getText().toUpperCase());
					try {
						dbconnect.pst.setInt(11,
								Integer.parseInt(textField_9.getText()));// check
																			// if
																			// zipcode
																			// is
																			// not
																			// integers
					} catch (Exception et) {
						JOptionPane.showMessageDialog(null,
								"zipcode must be numbers!");
					}
					// get selected choice and conver them to a string
					String temp = choice.getSelectedItem() + "/"
							+ choice_1.getSelectedItem() + "/"
							+ choice_2.getSelectedItem();

					// format the input date into sql date format
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					try {
						// check if date is vaild.
						java.sql.Date date = new java.sql.Date(sdf.parse(temp)
								.getTime());
						dbconnect.pst.setDate(12, date);
					} catch (ParseException e1) {
						JOptionPane
								.showMessageDialog(null,
										"Birthday invalid, Please enter in MM/DD/YYYY!");
					}

					// dbconnect.pst.setInt(9, Integer.parseInt(textField_8
					// .getText().toLowerCase()));
					dbconnect.pst.executeUpdate();
					System.out.println("asa");
					JOptionPane.showMessageDialog(null, "Member added");
					setVisible(false);

				} catch (SQLException e1) {

					textField.requestFocus();
					textField.selectAll();
					return;
				} finally {
					dbconnect.close();
				}

			}
		});
	}
}
