<%@ page language="java" pageEncoding="UTF-8"
	import="java.sql.*,javabean.*,java.util.*"%>

<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/storehouse", "root", "123456");
	String storehouseid = request.getParameter("storehouseid");
	String sql = "select no,name,price,num,sthouse.storehouseid,storehousename"
			+ " from sthouse,storehouse where sthouse.storehouseid=storehouse.storehouseid";
	if (storehouseid != null && storehouseid != "")
		sql += " and storehouse.storehouseid=?";
	PreparedStatement ps = con.prepareStatement(sql);
	if (storehouseid != null && storehouseid != "")
		ps.setString(1, storehouseid);
	ResultSet rs = ps.executeQuery();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="">
		仓库编号<select name="storehouseid" class="form-select">
			<option value="">所有仓库
			<option value="1">1号仓库
			<option value="2">2号仓库
		</select> <input type="submit" value="查询">
	</form>
	<table class="table table-hover shadow">
		<thead class="table-dark">
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>价格</th>
				<th>数量</th>
				<th>仓库编号</th>
				<th>仓库名称</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<Storehouse> list = new Storehouse().list();
				while (rs.next()) {
					out.print("<tr>");
					out.print("<td>" + rs.getString(1) + "</td>");
					out.print("<td>" + rs.getString(2) + "</td>");
					out.print("<td>" + rs.getInt(3) + "</td>");
					out.print("<td>" + rs.getString(4) + "</td>");
					out.print("<td>" + rs.getString(5) + "</td>");
					out.print("<td>" + rs.getString(6) + "</td>");
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>