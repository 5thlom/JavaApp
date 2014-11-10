import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CreateDatabase {

	public static Connection con = null;
	public static PreparedStatement pst = null;
	public static ResultSet rs = null;
	public static String dbName = "370pj2013a";

	// create database for first time use.
	
	public CreateDatabase() {

		String url = "jdbc:mysql://localhost:3306/";//url
		String user = "root";//user
		String password = "admin";//passwork
		try {
			con = DriverManager.getConnection(url, user, password);//getconnection with given url, user and passwork

			// set database name as 370project
			pst = con.prepareStatement("create database " + dbName);
			pst.executeUpdate();
			System.out.println("Database Created");
 
			url = "jdbc:mysql://localhost:3306/" + dbName;
			con = DriverManager.getConnection(url, user, password);

			//create member tables
			pst = con.prepareStatement("CREATE  TABLE members "
					+ "(type VARCHAR(45) NOT NULL, "
					+ "firstname VARCHAR(45) NOT NULL, "
					+ "lastname VARCHAR(45) NOT NULL, "
					+ "username VARCHAR(45) NOT NULL, "
					+ "password VARCHAR(45) NOT NULL , "
					+ "region VARCHAR(45) NOT NULL , "
					+ "email VARCHAR (45) NOT NULL , "
					+ "address VARCHAR(45) NOT NULL, "
					+ "city VARCHAR(45) NOT NULL, "
					+ "state VARCHAR(45) NOT NULL, " 
					+ "zip INT NOT NULL, "
					+ "dob DATE NOT NULL, "
					+ " PRIMARY KEY (`username`) )");
			pst.executeUpdate();//update
			System.out.println("Member Table Created");
			//create default admin info
			pst = con.prepareStatement("insert into members(type,firstname,lastname,username,password,region,email,address,city,state, zip,dob) values('ADMIN','ROOT', 'ROOT', 'root', 'root', 'ROOT', 'ROOT', 'ROOT', 'ROOT', 'ROOT',0000,0000)");
			pst.executeUpdate();
			
			
			// create table games 
			pst = con.prepareStatement("CREATE  TABLE games "
							+ " (gid INT NOT NULL AUTO_INCREMENT, "
							+ "gname VARCHAR(45) NOT NULL,"
							+ "gcost VARCHAR(45) NOT NULL,"
							+ "PRIMARY KEY (`gid`))");
							
			pst.executeUpdate();//update
			System.out.println("Games Table Created");
			//defalut data in games table
			pst = con.prepareStatement("insert into games(gname,gcost) values('2k13', 100)");
			pst.executeUpdate();
			pst = con.prepareStatement("insert into games(gname,gcost) values('root2', 1010)");
			pst.executeUpdate();//update;
			
			//create gamereviews table
			pst = con.prepareStatement("CREATE  TABLE gamereviews "
									+ "(grid INT NOT NULL AUTO_INCREMENT, "
									+ "Review VARCHAR(1500) NOT NULL,"
									+ "gid INT NOT NULL,"
									+ "username VARCHAR(45) NOT NULL,"
									+ "FOREIGN KEY(gid) REFERENCES  games(gid),"
									+ "FOREIGN KEY(username) REFERENCES  members(username),"
									+ "PRIMARY KEY (`grid`))");
										
			pst.executeUpdate();//updata database
			System.out.println("GameReviews Table Created");
			//creat deafult data
			pst = con.prepareStatement("insert into gamereviews(Review,gid,username) values('\nthis is a tester',1,'root')");

			pst.executeUpdate();
			pst = con.prepareStatement("insert into gamereviews(Review,gid,username) values('\nThis game is at least as good as NBA 2K12, with a few notable changes. Defense is much more difficult to play, which makes the game much more fun both offline and online. The other side of this is that the player gets to see a lot more explosive offensive plays, as defensive errors are common..',1,'root')");
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			
			
		}

	}
}
