package javabean;

import java.util.*;
import java.sql.*;

public class Storehouse {
	private String no;
	private String name;
	private Integer price;
	private String num;
	private String storehouseid;
	private String inventorydate;

	public Storehouse() {
	}

	public Storehouse(String no, String name, Integer price, String num, String storehouseid, String inventorydate) {
		this.no = no;
		this.name = name;
		this.price = price;
		this.num = num;
		this.storehouseid = storehouseid;
		this.inventorydate = inventorydate;
	}

	public String getInventorydate() {
		return inventorydate;
	}

	public void setInventorydate(String inventorydate) {
		this.inventorydate = inventorydate;
	}

	public String getStorehouseid() {
		return storehouseid;
	}

	public void setStorehouseid(String storehouseid) {
		this.storehouseid = storehouseid;
	}

	public Storehouse(String no) {
		this.no = no;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public ArrayList<Storehouse> list() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select * from storehouse order by no";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);
		ArrayList<Storehouse> list = new ArrayList<Storehouse>();
		while (rs.next()) { // 将结果集中的每条记录转为对象
			Storehouse sh = new Storehouse();
			sh.setNo(rs.getString("no"));
			sh.setName(rs.getString("name"));
			sh.setPrice(rs.getInt("price"));
			sh.setNum(rs.getString("num"));
			sh.setName(rs.getString("storehouseid"));
			sh.setInventorydate(rs.getString("inventorydate"));
			list.add(sh); // 将对象添加到对象列表
		}
		DBHelp.close(con, ps, rs);
		return list;
	}

	public void add() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "insert into storehouse values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, no);
		ps.setString(2, name);
		ps.setInt(3, price);
		ps.setString(4, num);
		ps.setString(5, storehouseid);
		ps.setString(6, inventorydate);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}

	public void del() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "delete from storehouse where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, no);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}

	public Storehouse getOne(String no) throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select * from storehouse where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, no);
		ResultSet rs = ps.executeQuery();
		Storehouse sh = new Storehouse();
		if (rs.next()) {
			sh.setNo(rs.getString("no"));
			sh.setName(rs.getString("name"));
			sh.setPrice(rs.getInt("price"));
			sh.setNum(rs.getString("num"));
			sh.setStorehouseid(rs.getString("storehouseid"));
			sh.setInventorydate(rs.getString("inventorydate"));
		}
		DBHelp.close(con, ps, rs);
		return sh;
	}

	public void update() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "update storehouse set name=?,price=?,num=?,storehouseid=?,inventorydate=? where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, price);
		ps.setString(3, num);
		ps.setString(4, storehouseid);
		ps.setString(5, inventorydate);
		ps.setString(6, no);
		ps.executeUpdate();
		DBHelp.close(con, ps, null);
	}

	public ArrayList<Map> queryByStorehouseid(String storehouseid) throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select no,name,price,num,sthouse.storehouseid,storehousename"
				+ " from sthouse,storehouse where sthouse.storehouseid=storehouse.storehouseid";
		if (storehouseid != null && storehouseid != "")
			sql += " and storehouse.storehouseid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		if (storehouseid != null && storehouseid != "")
			ps.setString(1, storehouseid);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list = new ArrayList<Map>();
		while (rs.next()) { // 将结果集中的每条记录转为对象
			Map map = new HashMap();
			map.put("no", rs.getString(1));
			map.put("name", rs.getString(2));
			map.put("price", rs.getInt(3));
			map.put("num", rs.getString(4));
			map.put("storehouseid", rs.getString(5));
			map.put("storehousename", rs.getString(6));
			list.add(map);
		}
		DBHelp.close(con, ps, rs);
		return list;
	}

	public ArrayList<Map> queryByStatic() throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select storehouse.storehouseid,storehousename,sum(num) num"
				+ " from sthouse,storehouse where storehouse.storehouseid=sthouse.storehouseid"
				+ " group by storehouse.storehouseid,storehousename";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list = new ArrayList<Map>();
		while (rs.next()) { // 将结果集中的每条记录转为对象
			Map map = new HashMap();
			map.put("storehouseid", rs.getString(1));
			map.put("storehousename", rs.getString(2));
			map.put("num", rs.getString(3));
			list.add(map);
		}
		DBHelp.close(con, ps, rs);
		return list;
	}

	public ArrayList<Map> queryCom(String no, String key, String inventorydatefrom, String inventorydateto)
			throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select no,name,price,num,storehousename,inventorydate"
				+ " from sthouse,storehouse where storehouse.storehouseid=sthouse.storehouseid";
		if (no != null && no != "") {
			sql += " and storehouse.no='" + no + "'";
		}
		if (key != null && key != "") {
			sql += " and (name like '%" + key + "%' or sthouse.storehousename like '%" + key + "%')";
		}
		if (inventorydatefrom != null && inventorydatefrom != "") {
			sql += " and inventorydate>='" + inventorydatefrom + "'";
			if (inventorydateto != null && inventorydateto != "") {
				sql += " and inventorydate<='" + inventorydateto + "'";
			}
		} else {
			if (inventorydateto != null && inventorydateto != "") {
				sql += " and inventorydate<='" + inventorydateto + "'";
			}
		}
		sql += " order by no";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list = new ArrayList<Map>();
		while (rs.next()) {
			Map map = new HashMap();
			map.put("no", rs.getString(1));
			map.put("name", rs.getString(2));
			map.put("price", rs.getInt(3));
			map.put("num", rs.getString(4));
			map.put("storehousename", rs.getString(5));
			map.put("inventorydate", rs.getString(6));
			list.add(map);
		}

		DBHelp.close(con, ps, rs);
		return list;
	}
	
	
	public int getTotalRow() throws ClassNotFoundException, SQLException{
		Connection con = DBHelp.GetConnection();
		Statement st = con.createStatement();
		String sql="select * from storehouse";
		ResultSet rs = st.executeQuery(sql);
		int rowcount =0;
		while(rs.next()){
			rowcount++;
		}
		DBHelp.close(con, null, rs);
		st.close();
		return rowcount;
	}
	
	public ArrayList<Map> querybypage(String no, String key, String inventorydatefrom, String inventorydateto, int pageNo, int pageSize)
			throws ClassNotFoundException, SQLException {
		Connection con = DBHelp.GetConnection();
		String sql = "select no,name,price,num,storehousename,inventorydate"
				+ " from sthouse,storehouse where storehouse.storehouseid=sthouse.storehouseid";
		if (no != null && no != "") {
			sql += " and storehouse.no='" + no + "'";
		}
		if (key != null && key != "") {
			sql += " and (name like '%" + key + "%' or sthouse.storehousename like '%" + key + "%')";
		}
		if (inventorydatefrom != null && inventorydatefrom != "") {
			sql += " and inventorydate>='" + inventorydatefrom + "'";
			if (inventorydateto != null && inventorydateto != "") {
				sql += " and inventorydate<='" + inventorydateto + "'";
			}
		} else {
			if (inventorydateto != null && inventorydateto != "") {
				sql += " and inventorydate<='" + inventorydateto + "'";
			}
		}
		sql += " order by no";
		int start = (pageNo - 1) * pageSize;
		sql += " limit " + start + "," + pageSize;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map> list = new ArrayList<Map>();
		while (rs.next()) {
			Map map = new HashMap();
			map.put("no", rs.getString(1));
			map.put("name", rs.getString(2));
			map.put("price", rs.getInt(3));
			map.put("num", rs.getString(4));
			map.put("storehousename", rs.getString(5));
			map.put("inventorydate", rs.getString(6));
			list.add(map);
		}

		DBHelp.close(con, ps, rs);
		return list;
	}

}
