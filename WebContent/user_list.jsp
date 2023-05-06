<%@ page language="java" pageEncoding="UTF-8"
	import="javabean.*,java.util.*"%>

<%
	User user = (User) session.getAttribute("current_user");

	String user_role;
	if (user.getRole().equals("0")) {
		user_role = "Admin";
	} else
		user_role = "User";

	String role = request.getParameter("role");
	/* 	ArrayList<Map> list = new User().queryByRole(role);
	 */
	int pageNo = 1;
	if (request.getParameter("pageNo") != null) {
		pageNo = Integer.parseInt(request.getParameter("pageNo"));
	}
	int pageSize = 6;
	int totalRow = new Storehouse().getTotalRow();
	int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;
	ArrayList<Map> list = new User().querybypage(role, pageNo, pageSize);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>仓库管理系统</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<style>
.rounded-input {
	border-radius: 1rem;
}

.navbar-brand {
	font-size: 1.5rem;
	font-family: Roboto;
}

.form-check-input[type="radio"]:checked {
	background-color: #343a40;
	border-color: #343a40;
}

.nav-masthead .nav-link {
	color: rgba(255, 255, 255, .5);
	border-bottom: .25rem solid transparent;
}

.nav-masthead .nav-link:hover, .nav-masthead .nav-link:focus {
	border-bottom-color: rgba(255, 255, 255, .25);
}

.nav-masthead .nav-link+.nav-link {
	margin-left: 1rem;
}

.nav-masthead .active {
	color: #fff;
	border-bottom-color: #fff;
}

.footer {
	position: fixed;
	bottom: 0;
	width: 100%;
}
</style>
</head>
<body class="d-flex flex-column min-vh-100">

	<header class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="sthouse_list.jsp">Storehouse</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 nav-masthead">
					<li class="nav-item"><a class="nav-link"
						href="sthouse_list.jsp">仓库管理</a></li>
					<li class="nav-item"><a class="nav-link"
						href="storehouse_list2.jsp">货物管理</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="user_list.jsp">用户管理</a></li>
				</ul>
				<div class="dropdown">
					<button class="btn btn-warning dropdown-toggle" type="button"
						id="dropdownMenuButton" data-bs-toggle="dropdown"
						aria-expanded="false">
						<%=user.getUsername()%>(<%=user_role%>)
					</button>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="dropdownMenuButton">
						<li><a class="dropdown-item" href="user_login.html">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<main class="flex-grow-1">
	<div class="container my-3">
		<div class="card border-0">
			<div class="card-body">
				<h1 class="mt-2 mb-2">用户管理</h1>

				<form class="row align-items-center" action="">
					<div class="col-auto d-flex align-items-center">
						<div class="me-2">权限：</div>
					</div>
					<div class="col-auto">
						<select name="role" class="form-select"
							aria-label="Default select example">
							<option <%=role == null ? "selected" : ""%> value="">所有用户</option>
							<option <%=role != null && role.equals("0") ? "selected" : ""%>
								value="0">管理员</option>
							<option <%=role != null && role.equals("1") ? "selected" : ""%>
								value="1">用户</option>
						</select>
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-dark"
							style='border-radius: 13px;'>查询</button>
					</div>
				</form>


				<table class="table table-hover shadow mt-3">
					<thead class="table-dark">
						<tr>
							<th>用户名</th>
							<th>密码</th>
							<th>权限</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<%
							for (Map map : list) {

								String userrole;
								if (map.get("role").equals("0")) {
									userrole = "管理员";
								} else
									userrole = "用户";
								out.print("<tr>");
								out.print("<td>" + map.get("username") + "</td>");
								out.print("<td>" + map.get("password") + "</td>");
								out.print("<td>" + userrole + "</td>");
								out.print(
										"<td>" + "<a class='btn btn-sm btn-outline-dark'  style='border-radius: 15px;' href=user_show.jsp?username="
												+ map.get("username") + ">编辑</a>&nbsp;&nbsp;"
												+ "<a class='btn btn-sm btn-outline-danger'style='border-radius: 15px;' href=userdel?username="
												+ map.get("username") + ">删除</a></td>");
								out.print("</tr>");
							}
						%>
					</tbody>
				</table>

			</div>
			<div class="card-footer border-0 bg-body">
				<a class='btn btn-sm btn-dark' href="user_add.html"
					style="border-radius: 15px;">添加</a>
			</div>
			<nav class="mt-2">
					<ul class="pagination justify-content-center mt-2">

						<%-- 首页链接 --%>
						<li class="page-item <%=pageNo == 1 ? "disabled" : ""%>"><a
							class="page-link text-dark" href="user_list.jsp?pageNo=1"
							aria-label="首页"> <span aria-hidden="true">&laquo;</span> <span
								class="sr-only">首页</span>
						</a></li>

						<%-- 上一页链接 --%>
						<li class="page-item <%=pageNo == 1 ? "disabled" : ""%>"><a
							class="page-link text-dark"
							href="user_list.jsp?pageNo=<%=pageNo - 1%>"
							aria-label="上一页"> <span aria-hidden="true">&lt;</span> <span
								class="sr-only">上一页</span>
						</a></li>

						<%-- 当前页码链接 --%>
						<li class="page-item active"><a
							class="page-link bg-dark text-white" href="#"> <%=pageNo%>
						</a></li>

						<%-- 下一页链接 --%>
						<li class="page-item <%=pageNo < totalPage ? "disabled" : ""%>">
							<a class="page-link text-dark"
							href="user_list.jsp?pageNo=<%=pageNo + 1%>"
							aria-label="下一页"> <span aria-hidden="true">&gt;</span> <span
								class="sr-only">下一页</span>
						</a>
						</li>

						<%-- 尾页链接 --%>
						<li class="page-item <%=pageNo < totalPage ? "disabled" : ""%>">
							<a class="page-link text-dark"
							href="user_list.jsp?pageNo=<%=totalPage%>" aria-label="尾页">
								<span aria-hidden="true">&raquo;</span> <span class="sr-only">尾页</span>
						</a>
						</li>

					</ul>
				</nav>
		</div>
	</div>

	</main>
	<div class="container py-0">
		<footer
			class="d-flex flex-wrap justify-content-between align-items-center py-0 my-4 border-top">
			<div class="col-md-4">
				<span class="mb-3 mb-md-0 text-muted"> &copy; 2023 王政豪</span>
			</div>
			<ul class="nav col-md-8 justify-content-end align-items-center">
				<li class="nav-item">
					<h5 class="mb-0">Contact me</h5>
				</li>
				<li class="nav-item"><a href="mailto:wzh791307963@163.com"
					class="nav-link px-2 text-muted">Email</a></li>
				<li class="nav-item"><a href="https://github.com/D1viner"
					class="nav-link px-2 text-muted">Github</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted"
					onclick="alert('微信号：wzh791307963')">WeChat</a></li>

				<li class="nav-item">
					<h5 class="mb-0 mx-3">|</h5>
				</li>
				<li class="nav-item">
					<h5 class="mb-0">Copy</h5>
				</li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">Privacy Policy</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">Terms of Service</a></li>
			</ul>
		</footer>
	</div>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>
