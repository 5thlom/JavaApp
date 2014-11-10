

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConnectMYSQL {

	public Connection con = null;
	public static PreparedStatement pst = null;
	public static ResultSet rs = null;

	public ConnectMYSQL() {

		String url = "jdbc:mysql://localhost:3306/"+CreateDatabase.dbName;//url
		String user = "root";//username
		String password = "admin";

		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Database not connect!");
			System.exit(1);
		}

	}

	public void close() {

	try {
			if (rs != null) {
				rs.close();
			}

			if (pst != null) {
				pst.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {

		}
	}

}
