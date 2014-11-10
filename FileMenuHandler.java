import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FileMenuHandler implements ActionListener {
	JFrame jframe;
	public static JFrame form;// defalut form
	public static int gid;// gid to keep information of game id

	// default constructer
	public FileMenuHandler(JFrame jf) {
		jframe = jf;
	}

	// string un to keep username
	public static String un;
	//actionperformed method
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		//cancel
		if(buttonName.equals("Cancel")){
			jframe.setVisible(false);
		}
		//CreatAccount;
		if (buttonName.equals("Creat Account")) {
			AddUser();
			}
		//exportmember
		if (buttonName.equals("ExportMember")) {
			exportMember();
		}
		//exportgames
		if (buttonName.equals("ExportGames")) {
			exportGames();
		}
		//exportgamereviews
		if (buttonName.equals("ExportGamesRiview")) {
			exportGamesReview();
		}
		//import games
		if (buttonName.equals("ImportGames")) {
			openFile(0);
			}
		//importgamesreviews
	
		//removemember
		if (buttonName.equals("RemoveMember")) {
			form = new RemoveUser();//create removUser gui by call cons
			form.setVisible(true);

		}
		//Login
		if (buttonName.equals("Login")) {
				LoginCheck();//check if account is valid

		}
		//add a new reviews;
		if (buttonName.equals("Add")) {
			AddReviews();
		}
		//reviews, check current items's reviews
		if (buttonName.equals("Reviews!")) {
			//get choice information
			String choice1 = mainGui.choice.getSelectedItem().toLowerCase();
			String choice = mainGui.choice.getSelectedItem().toLowerCase();
			//if choice is computer, we return; funtion is unavailable now
			if (choice.equals("computer") || choice1.equals("computer")) {
				JOptionPane
						.showMessageDialog(
								null,
								"Currently computer database we still working on it...\nGames database is available...");
				return;
			}
			//if gameGocheck is not false, we have the item in database
			if (gameGoCheck() == false) {
				System.out.println("here");
				JOptionPane.showMessageDialog(null, "Item found");
				//output line separator
				mainGui.textArea.setText(null);
				mainGui.textArea
						.append("\n---------------------------------------------------------"
								+ "---------------------------------------------------------------------\n\n");
				outputReviewsGames(1);//print reviews 
				//mainGui.textArea.setText("");

			} else {
				JOptionPane.showMessageDialog(null, "Item Not Found");
			}
		}
		
		//views, for admin options
		if (buttonName.equals("views!")) {
			String choice1 = mainGui.choicee1.getSelectedItem().toLowerCase();//get choices data
			//if choices item is computer
			if (choice1.equals("computer")) {
				JOptionPane.showMessageDialog(
								null,
								"Currently computer database we still working on it...\nGames database is available...");
				return;
			}
			//if the item is not found
			if (gameGoCheckA() == false) {
				mainGui.textAreae1.setText("");
				//System.out.println("heraaaaae"+ " "+ mainGui.textArea);
				
				JOptionPane.showMessageDialog(null, "Item found");
				mainGui.textAreae1
						.append("\n----------------------------------------------------"
								+ "----------------------------------------------------------\n\n");
				outputReviewsGames(0);//print reviews for admin option
				
			} else {
				JOptionPane.showMessageDialog(null, "Item Not Found");
			}
		}
	}//end of actionperformed method
	
	
	
	//import data, using File, int i indicates where its from for games or gamesreview;
	private void Import(File cf, int i) {

		String e = "";

		ConnectMYSQL dbconnect = new ConnectMYSQL();//connect mysql

		try {

			@SuppressWarnings("resource")
			Scanner cin = new Scanner(cf);

			while (cin.hasNext()) {

				String gamen, gcost, reviews, username;
				int gid, grid;
				// get each line in the file and split using delimiter ,
				String temp = cin.nextLine();
				String[] parts = temp.split(",,");

				// insert the values into database
				try {
					if (i == 0) {//if i==0,we are doing the games import
						gid = Integer.parseInt(parts[0]);
						gamen = parts[1];
						gcost = parts[2];
						dbconnect.pst = dbconnect.con
								.prepareStatement("insert into games(gid,gname,gcost) value("//insert current data to database
										+ "?,"
										+ "'"
										+ gamen
										+ "'"
										+ ","
										+ "'"
										+ gcost + "'" + ")");
						dbconnect.pst.setInt(1, gid);//updatae column one with gid data
					
					} else {//else we are doing gamereview import
						grid = Integer.parseInt(parts[0]);
						reviews = parts[1];
						gid = Integer.parseInt(parts[2]);
						username = parts[3];
						dbconnect.pst = dbconnect.con
								.prepareStatement("insert into gamereviews(grid,Review,gid,username) value("//input to database
										+ "?,"
										+ "'"
										+ reviews
										+ "'"
										+ ",?,"
										+ "'" + username + "'" + ")");
						dbconnect.pst.setInt(1, grid);//update column 1
						dbconnect.pst.setInt(3, gid);//updtate
					}
					dbconnect.pst.executeUpdate();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}

			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String chosenFileName = cf.getName();
		JOptionPane.showMessageDialog(null, chosenFileName
				+ " has been loaded " + "\n");

		// print out the member id that was not added
		if (!e.isEmpty()) {
			JOptionPane.showMessageDialog(null, "errors " + e);
		}
		dbconnect.close();
	}// readfile
//open files
	private void openFile(int i) {
		JFileChooser chooser;
		int status;
		chooser = new JFileChooser();
		status = chooser.showOpenDialog(null);
		//check
		if (status == JFileChooser.APPROVE_OPTION) {
			Import(chooser.getSelectedFile(), i);
		}

		else
			JOptionPane.showMessageDialog(null, "Open File dialog canceled");

	} // openFile

	//exportGamesReviews
	private void exportGamesReview() {
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			PrintWriter w = new PrintWriter("gamereviews.txt");//output text
			try {
				dbconnect.pst = dbconnect.con
						.prepareStatement("select * from gamereviews");//sql commant
				dbconnect.rs = dbconnect.pst.executeQuery();
				while (dbconnect.rs.next()) {
					w.println(dbconnect.rs.getInt(1) + ","
							+ dbconnect.rs.getString(2) + ","
							+ dbconnect.rs.getInt(3) + ","
							+ dbconnect.rs.getString(4) + "\n");//get datas;
				}
				w.flush();//flush
			} catch (SQLException e) {
			}
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
				"GamesReviewsFile export successfully!");

	}
	//export games tables
	private void exportGames() {
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			PrintWriter w = new PrintWriter("games.txt");
			try {
				dbconnect.pst = dbconnect.con
						.prepareStatement("select * from games");//sql comment
				dbconnect.rs = dbconnect.pst.executeQuery();
				while (dbconnect.rs.next()) {
					w.println(dbconnect.rs.getInt(1) + ","
							+ dbconnect.rs.getString(2) + ","
							+ dbconnect.rs.getString(3) + "\n");
				}
				w.flush();
			} catch (SQLException e) {
			}
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "GamesFile export successfully!");

	}//end of export games
//exportmembers tabbles
	private void exportMember() {
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			PrintWriter w = new PrintWriter("members.txt");
			try {
				dbconnect.pst = dbconnect.con
						.prepareStatement("select * from members");//sql
				dbconnect.rs = dbconnect.pst.executeQuery();
				while (dbconnect.rs.next()) {
					w.println(dbconnect.rs.getString(1) + ","
							+ dbconnect.rs.getString(2) + ","
							+ dbconnect.rs.getString(3) + ","
							+ dbconnect.rs.getString(4) + ","
							+ dbconnect.rs.getString(5) + ","
							+ dbconnect.rs.getString(6) + ","
							+ dbconnect.rs.getString(7) + ","
							+ dbconnect.rs.getString(8) + ","
							+ dbconnect.rs.getString(9) + ","
							+ dbconnect.rs.getString(10) + ","
							+ dbconnect.rs.getInt(11) + ","
							+ dbconnect.rs.getInt(12) + "\n");
				}
				w.flush();
			} catch (SQLException e) {
			}
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "MemberFile export successfully!");
	}

	//game go check will check if the game is alreay in the database, if yes, we print the name, id and price.
	private boolean gameGoCheckA() {
		String choice = mainGui.choicee1.getSelectedItem().toLowerCase();

		String item = mainGui.textFielde1.getText();
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			dbconnect.pst = dbconnect.con.prepareStatement("select * from "
					+ choice + " where gname=?");
			dbconnect.pst.setString(1, item);
			dbconnect.rs = dbconnect.pst.executeQuery();

			while (dbconnect.rs.next()) {
				if (dbconnect.rs.getString(2).equals(item))//check if column 2 each item, item is the textfield data

					mainGui.textAreae1.append("Game Department- " + "\n"
							+ "ProductName: " + item + "\n" + "GameID: "
							+ dbconnect.rs.getInt(1) + "\n" + "GamePrice: "
							+ dbconnect.rs.getString(3) + "\n");
				gid = dbconnect.rs.getInt(1);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}
//add reviews, this will add a creat a new addReview gui.
	private void AddReviews() {
		// formIsOn();
		// form = new AddUser();
		// AddUser.isOn = true;
		// form.setLocation(null);
		// form.setVisible(true);
		form = new AddReview();
		AddUser.isOn = true;
		// form.setLocation(null);
		form.setVisible(true);
		// JOptionPane.showMessageDialog(null, "sdfa");

	}
//output reviewsgames, it will print all reviews if the gid is the same
	private void outputReviewsGames(int i) {
		
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		String choice = mainGui.choice.getSelectedItem().toLowerCase();
		String item = mainGui.textFielde2.getText();

		try {
			dbconnect.pst = dbconnect.con
					.prepareStatement("select * from gamereviews where gid="
							+ Integer.toString(gid));

			dbconnect.rs = dbconnect.pst.executeQuery();

			// mainGui.textArea.append("Game Reviews- "
			// +dbconnect.rs.getInt(1)+"\n"
			// );
			// System.out.println(dbconnect.rs.getInt(1));
			String usern;
			while (dbconnect.rs.next()) {
				if (i == 1) {
					mainGui.textArea.append(dbconnect.rs.getString(2) + "\n");
				}
				if (i == 0) {
					mainGui.textAreae1.append(dbconnect.rs.getString(2) + "\n");
				}
				printUserInfo(dbconnect.rs.getString(4), i);

				usern = dbconnect.rs.getString(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//print user info, this will print the userinfo 
	private void printUserInfo(String gid2, int i) {
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			dbconnect.pst = dbconnect.con
					.prepareStatement("select * from members where username="
							+ gid2);
			if (i == 1) {//i==1 for normal user, else for adm user.
				mainGui.textArea.append("Writer: " + dbconnect.rs.getString(4)
						+ "\n");
				mainGui.textArea
						.append("\n-----------------------------------------------=---------------------------"
								+ "-----------------------------------------------------\n");
			} else {
				mainGui.textAreae1.append("Writer: "
						+ dbconnect.rs.getString(4) + "\n");
				mainGui.textAreae1
						.append("\n---------------------------------------------------=----------------------------"
								+ "-----------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//game go check, this is for normal user to check games by the select choice and textfield data.
	private boolean gameGoCheck() {
		String choice = mainGui.choice.getSelectedItem().toLowerCase();//get choice data;

		String item = mainGui.textFielde2.getText();//get textfielde2 data;
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			dbconnect.pst = dbconnect.con.prepareStatement("select * from "
					+ choice + " where gname=?");
			dbconnect.pst.setString(1, item);
			dbconnect.rs = dbconnect.pst.executeQuery();
			//while rs.next is true, we print the data;
			while (dbconnect.rs.next()) {
				if (dbconnect.rs.getString(2).equals(item))

					mainGui.textArea.append("Game Department- " + "\n"
							+ "ProductName: " + item + "\n" + "GameID: "
							+ dbconnect.rs.getInt(1) + "\n" + "GamePrice: "
							+ dbconnect.rs.getString(3) + "\n");
				gid = dbconnect.rs.getInt(1);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}
	//account Exits is the method that check if the account is already in the database; when new user want to create a new account
	public static boolean accountExist(int i) {
		String username;
		if (i == 1) {
			username = AddUser.textField_2.getText();//1 for normal user
		} else {
			username = RemoveUser.textField.getText();//0 for adm user
		}
		if (username.equals(""))
			return false;
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			dbconnect.pst = dbconnect.con
					.prepareStatement("select * from members where username=?");
			dbconnect.pst.setString(1, username);
			dbconnect.rs = dbconnect.pst.executeQuery();
			while (dbconnect.rs.next()) {
				if (dbconnect.rs.getString(4).equals(username))
					return false;//false if the name is in database
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	//login check, this will check if the password and username is matched or not
	public void LoginCheck() {
		String username = mainGui.textField.getText();
		String password = mainGui.textField_1.getText();
		boolean f = false;
		boolean f1 = false;
		ConnectMYSQL dbconnect = new ConnectMYSQL();
		try {
			dbconnect.pst = dbconnect.con
					.prepareStatement("select * from members where username=?");//sql
			dbconnect.pst.setString(1, username);
			dbconnect.rs = dbconnect.pst.executeQuery();

			while (dbconnect.rs.next()) {

				if (dbconnect.rs.getString(4).equals(username)
						&& dbconnect.rs.getString(5).equals(password)) {//if the password and username is matched in database 
					f = true;
					un = dbconnect.rs.getString(4);
					if (dbconnect.rs.getString(1).equals("NORMAL")) {
						System.out.println("you are normal client");
						mainGui.cards.previous(mainGui.displayPanel);//check if normal user, if yes, we want him/her use normal interface.

					} else {
						mainGui.cards.next(mainGui.displayPanel);//if user is ok and check if they are normal or not, if not, we want him to use adm interface
						System.out.println("you are admin client");
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (f == false) {
			JOptionPane.showMessageDialog(null, "invalid Username or Password");
		}
		if (f == true)
			JOptionPane.showMessageDialog(null, "Welcome!!");

	}

	private void AddUser() {
		// TODO Auto-generated method stub
		// formIsOn();
		form = new AddUser();
		AddUser.isOn = true;
		// form.setLocation(null);
		form.setVisible(true);
	}
}
