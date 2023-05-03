<%@ page language="java" pageEncoding="UTF-8"
	import="java.sql.*,javabean.*,java.util.*"%>

<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/storehouse", "root", "123456");
	String storehouseid = request.getParameter("storehouseid");
	String sql = "select storehouse.storehouseid,storehousename,storehouseaddress,sum(num) num"
			+ " from sthouse,storehouse where storehouse.storehouseid=sthouse.storehouseid"
			+ " group by storehouse.storehouseid,storehousename";

	PreparedStatement ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action=""></form>
	<table class="table table-hover shadow" border=1>
		<thead class="table-dark">
			<tr>
				<th>仓库编号</th>
				<th>仓库名称</th>
				<th>仓库地址</th>
				<th>货物数量</th>
			</tr>
		</thead>
		<tbody>
			<%
				while (rs.next()) {
					out.print("<tr>");
					out.print("<td>" + rs.getString(1) + "</td>");
					out.print("<td>" + rs.getString(2) + "</td>");
					out.print("<td>" + rs.getString(3) + "</td>");
					out.print("<td>" + rs.getInt(4) + "</td>");
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>