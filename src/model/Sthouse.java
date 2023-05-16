package model;

import java.util.*;
import java.sql.*;

public class Sthouse {
	private String id;
	private String name;
	private String address;

	public Sthouse() {
	}

	public Sthouse(String id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Sthouse(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Sthouse> list() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select * from sthouse order by storehouseid";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);
		ArrayList<Sthouse> list = new ArrayList<Sthouse>();
		while (rs.next()) { // 将结果集中的每条记录转为对象
			Sthouse st = new Sthouse();
			st.setId(rs.getString("storehouseid"));
			st.setName(rs.getString("storehousename"));
			st.setAddress(rs.getString("storehouseaddress"));
			list.add(st); // 将对象添加到对象列表
		}
		DBHelp.close(con, ps, rs);
		return list;
	}

	public void add() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "insert into sthouse values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, name);
		ps.setString(3, address);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}

	public void del() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "delete from sthouse where storehouseid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}

	public Sthouse getOne(String id) throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select * from sthouse where storehouseid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		Sthouse st = new Sthouse();
		if (rs.next()) {
			st.setId(rs.getString("storehouseid"));
			st.setName(rs.getString("storehousename"));
			st.setAddress(rs.getString("storehouseaddress"));
		}
		DBHelp.close(con, ps, rs);
		return st;
	}

	public void update() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "update sthouse set storehousename=?,storehouseaddress=? where storehouseid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, address);
		ps.setString(3, id);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}

	public ArrayList<Map> queryByStatic(String id,String key) throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "SELECT sthouse.storehouseid, sthouse.storehousename, sthouse.storehouseaddress, COALESCE(SUM(storehouse.num), 0) AS total_amount FROM sthouse LEFT JOIN storehouse ON storehouse.storehouseid = sthouse.storehouseid";
		if(id!=null&&id!=""){
			sql+=" where sthouse.storehouseid='"+id+"'";
			if(key!=null&&key!=""){
				sql+=" and sthouse.storehouseaddress LIKE '%"+key+"%'";
			}
		}else{
			if(key!=null&&key!=""){
				sql+=" where sthouse.storehouseaddress LIKE '%"+key+"%'";
			}
		}
		
		sql+=" GROUP BY sthouse.storehouseid, sthouse.storehousename, sthouse.storehouseaddress"
				+" ORDER BY sthouse.storehouseid;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list = new ArrayList<Map>();
		while (rs.next()) { // 将结果集中的每条记录转为对象
			Map map = new HashMap();
			map.put("storehouseid", rs.getString(1));
			map.put("storehousename", rs.getString(2));
			map.put("storehouseaddress", rs.getString(3));
			map.put("total_amount", rs.getInt(4));
			list.add(map);
		}
		DBHelp.close(con, ps, rs);
		return list;
	}
	
	public int getTotalRow() throws ClassNotFoundException, SQLException{
		Connection con = DBHelp.GetConnection();
		Statement st = con.createStatement();
		String sql="select * from sthouse";
		ResultSet rs = st.executeQuery(sql);
		int rowcount =0;
		while(rs.next()){
			rowcount++;
		}
		DBHelp.close(con, null, rs);
		st.close();
		return rowcount;
	}
	
	public ArrayList<Map> querybypage(String id,String key, int pageNo, int pageSize) throws ClassNotFoundException, SQLException{
		Connection con = DBHelp.GetConnection();
		String sql = "SELECT sthouse.storehouseid, sthouse.storehousename, sthouse.storehouseaddress, COALESCE(SUM(storehouse.num), 0) AS total_amount FROM sthouse LEFT JOIN storehouse ON storehouse.storehouseid = sthouse.storehouseid";
		if(id!=null&&id!=""){
			sql+=" where sthouse.storehouseid='"+id+"'";
			if(key!=null&&key!=""){
				sql+=" and sthouse.storehouseaddress LIKE '%"+key+"%'";
			}
		}else{
			if(key!=null&&key!=""){
				sql+=" where sthouse.storehouseaddress LIKE '%"+key+"%'";
			}
		}
		sql+=" GROUP BY sthouse.storehouseid, sthouse.storehousename, sthouse.storehouseaddress"
				+" ORDER BY sthouse.storehouseid";
		int start = (pageNo - 1) * pageSize;
		sql += " limit " + start + "," + pageSize;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list = new ArrayList<Map>();
		while (rs.next()) { // 将结果集中的每条记录转为对象
			Map map = new HashMap();
			map.put("storehouseid", rs.getString(1));
			map.put("storehousename", rs.getString(2));
			map.put("storehouseaddress", rs.getString(3));
			map.put("total_amount", rs.getInt(4));
			list.add(map);
		}
		DBHelp.close(con, ps, rs);
		return list;
	}

}
