package model;

import java.sql.*;

public class DBHelp {

	public DBHelp() {

	}

	public static Connection GetConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/storehouse", "root", "123456");
		return con;
	}

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
		if (con != null) {
			con.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
}
