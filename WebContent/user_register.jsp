<%@ page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
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
</style>
</head>
<body class="d-flex flex-column min-vh-100">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand " href="user_login.jsp"
				style="margin-left: 30px;">Storehouse</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav"
				style="margin-right: 30px;">
				<ul class="navbar-nav ms-auto nav-masthead">
					<li class="nav-item"><a class="nav-link " aria-current="page"
						href="user_login.jsp">Sign in</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="user_register.jsp">Sign up</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="flex-grow-1">
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6 col-lg-4">
				<div class="card shadow-lg">
					<div class="card-body">
						<h1 class="card-title mb-4 text-center">注册</h1>
						<form action="UserRegister">
							<div class="form-floating mb-3 rounded-input">
								<input type="text" class="form-control rounded-input"
									id="floatingInput" name="username" placeholder="username">
								<label for="floatingInput">Username</label>
							</div>
							<div class="form-floating mb-3 rounded-input">
								<input type="password" class="form-control rounded-input"
									id="floatingPassword" placeholder="Password" name="password">
								<label for="floatingPassword">Password</label>
							</div>
							<p class="text-danger">${error }</p>
							<div class="list-group mb-2">
								<label class="list-group-item d-flex gap-4"> <input
									class="form-check-input flex-shrink-0" type="radio" name="role"
									id="listGroupRadios1" value="0" checked> <span><font
										style="vertical-align: inherit;"> <font
											style="vertical-align: inherit;"> 管理员 </font>
									</font> <small class="d-block text-body-secondary"> <font
											style="vertical-align: inherit;"> <font
												style="vertical-align: inherit;">仓库、商品、用户管理</font></font></small> </span>
								</label> <label class="list-group-item d-flex gap-4"> <input
									class="form-check-input flex-shrink-0" type="radio" name="role"
									id="listGroupRadios2" value="1"> <span><font
										style="vertical-align: inherit;"><font
											style="vertical-align: inherit;"> 用户 </font></font><small
										class="d-block text-body-secondary"><font
											style="vertical-align: inherit;"><font
												style="vertical-align: inherit;">仓库、商品管理</font></font></small> </span>
								</label>
							</div>
							<br>
							<div class="d-grid gap-2 mb-4">
								<button type="submit" class="btn btn-dark">注册</button>
							</div>
						</form>
						<hr>
						<p class="text-center mb-3">已有账号？</p>
						<div class="d-grid gap-2">
							<a href="user_login.html" class="btn btn-outline-secondary">登录</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
	<div class="container py-0">
		<footer
			class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>