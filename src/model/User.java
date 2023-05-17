package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class User {
	private String username;
	private String password;
	private String role;

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(){
	}
	
	public User(String username){
		this.username=username;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void register() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "insert into user values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, role);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}

	public boolean login() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select * from user where username=? and password=? and role=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, role);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		} else
			return false;
	}
	public ArrayList<User> list() throws ClassNotFoundException, SQLException{
		Connection con=DBHelp.GetConnection();
		String sql="select * from user order by username";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery(sql);
		ArrayList<User> list=new ArrayList<User>();
		while(rs.next()){ //将结果集中的每条记录转为对象
			User ur=new User();
			ur.setUsername(rs.getString("username"));
			ur.setPassword(rs.getString("password"));
			ur.setRole(rs.getString("role"));
			list.add(ur); //将对象添加到对象列表
		}
		DBHelp.close(con, ps, rs);
		return list;
	}
	public static void del(String username) throws ClassNotFoundException, SQLException{
		Connection con=DBHelp.GetConnection();
		String sql="delete from user where username=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, username);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}
	
	public User getOne(String username) throws ClassNotFoundException, SQLException{
		Connection con=DBHelp.GetConnection();
		String sql="select * from user where username=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs=ps.executeQuery();
		User ur=new User();
		if(rs.next()){ 
			ur.setUsername(rs.getString("username"));
			ur.setPassword(rs.getString("password"));
			ur.setRole(rs.getString("role"));
		}
		DBHelp.close(con, ps, rs);
		return ur;
	}
	
	public void update() throws ClassNotFoundException, SQLException{
		Connection con=DBHelp.GetConnection();
		String sql="update user set password=?,role=? where username=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, role);
		ps.setString(3, username);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}
	
	public ArrayList<Map> queryByRole(String role) throws ClassNotFoundException, SQLException{
		Connection con=DBHelp.GetConnection();
		String sql="select * from user";
		if (role != null && role != "")
			sql += " where role=?";
		PreparedStatement ps = con.prepareStatement(sql);
		if (role != null && role != "")
			ps.setString(1, role);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list=new ArrayList<Map>();
		while(rs.next()){
			Map map = new HashMap();
			map.put("username", rs.getString(1));
			map.put("password", rs.getString(2));
			map.put("role", rs.getString(3));
			list.add(map);
		}
		DBHelp.close(con, ps, rs);
		return list;
	}
	public int getTotalRow() throws ClassNotFoundException, SQLException{
		Connection con = DBHelp.GetConnection();
		Statement st = con.createStatement();
		String sql="select * from user";
		ResultSet rs = st.executeQuery(sql);
		int rowcount =0;
		while(rs.next()){
			rowcount++;
		}
		DBHelp.close(con, null, rs);
		st.close();
		return rowcount;
	}
	public ArrayList<Map> querybypage(String role, int pageNo, int pageSize) throws ClassNotFoundException, SQLException{
		Connection con=DBHelp.GetConnection();
		String sql="select * from user";
		if (role != null && role != "")
			sql += " where role=?";
		PreparedStatement ps = con.prepareStatement(sql);
		if (role != null && role != "")
			ps.setString(1, role);
		
		sql += " order by username";
		int start = (pageNo - 1) * pageSize;
		sql += " limit " + start + "," + pageSize;
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list=new ArrayList<Map>();
		while(rs.next()){
			Map map = new HashMap();
			map.put("username", rs.getString(1));
			map.put("password", rs.getString(2));
			map.put("role", rs.getString(3));
			list.add(map);
		}
		DBHelp.close(con, ps, rs);
		return list;
	}

}
