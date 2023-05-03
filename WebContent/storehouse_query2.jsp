<%@ page language="java" pageEncoding="UTF-8"
	import="java.sql.*,javabean.*,java.util.*"%>

<%
	String storehouseid = request.getParameter("storehouseid");
	ArrayList<Map> list = new Storehouse().queryByStorehouseid(storehouseid);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="">
		仓库编号<select name="storehouseid">
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
				for (Map map : list) {
					out.print("<tr>");
					out.print("<td>" + map.get("no") + "</td>");
					out.print("<td>" + map.get("name") + "</td>");
					out.print("<td>" + map.get("price") + "</td>");
					out.print("<td>" + map.get("num") + "</td>");
					out.print("<td>" + map.get("storehouseid") + "</td>");
					out.print("<td>" + map.get("storehousename") + "</td>");
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>