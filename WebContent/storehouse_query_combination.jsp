<%@ page language="java" pageEncoding="UTF-8"
	import="java.sql.*,javabean.*,java.util.*"%>

<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/storehouse", "root", "123456");
	
	String storehouseid = request.getParameter("storehouseid");
	String name = request.getParameter("name");
	if(name==null) name="";
	if(storehouseid==null) storehouseid="";
	Statement st=con.createStatement();
	String sql = "select * from storehouse where 1=1";
	if (name != null && name != "")
		sql += " and name like '%"+name+"%'";
	if (storehouseid != null && storehouseid != "")
		sql += " and storehouseid='"+storehouseid+"'";
	ResultSet rs = st.executeQuery(sql);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="">
		名称：<input type="text" name="name" value=<%=name %>> 
		仓库编号：<input type="text" name="storehouseid" value=<%=storehouseid %>> 
		<input type="submit" value="查询">
	</form>
	<table class="table table-hover shadow" border=1>
		<thead class="table-dark">
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>价格</th>
				<th>数量</th>
				<th>仓库编号</th>

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
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>