<%@ page language="java" pageEncoding="UTF-8"
	import="java.sql.*,javabean.*,java.util.*"%>

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
				<th>货物数量</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<Map> list = new Storehouse().queryByStatic();
				for(Map map:list) {
					out.print("<tr>");
					out.print("<td>" + map.get("storehouseid") + "</td>");
					out.print("<td>" + map.get("storehousename") + "</td>");
					out.print("<td>" + map.get("num")+ "</td>");
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>