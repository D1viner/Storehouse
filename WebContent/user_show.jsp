<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>修改用户信息</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<style>
.navbar-brand {
	font-size: 1.5rem;
	font-family: Roboto;
}

.card {
	margin-top: 2rem;
}

.form-check-input[type="radio"]:checked {
	background-color: #343a40;
	border-color: #343a40;
}

.rounded-input {
	border-radius: 1rem;
}
</style>
</head>
<body class="d-flex flex-column min-vh-100">
	<header class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="sthouse_list.jsp">Storehouse</a>
			<div class="collapse navbar-collapse" id="navbarNav">
				<div class="ms-auto">
					<a class="btn btn-warning" href="user_list">Back</a>
				</div>
			</div>
		</div>
	</header>
	<main class="flex-grow-1">

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-8 col-lg-4 ">
				<div class="card shadow-lg">
					<div class="card-body">
						<h1 class="card-title mb-4">用户信息修改</h1>
						<form action="userupdate">
							<div class="form-floating mb-3 rounded-input">
								<input type="text" class="form-control rounded-input"
									id="floatingInput" name="username" placeholder="username"
									value="${username}" readonly> <label
									for="floatingInput">Username</label>
							</div>
							<div class="form-floating mb-3 rounded-input">
								<input type="text" class="form-control rounded-input "
									id="floatingPassword" placeholder="Password" name="password"
									value="${ur.password }"> <label for="floatingPassword">Password</label>
							</div>
							<p id="passwordError" class="text-danger" style="display: none;">密码长度不能少于6位</p>

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
								<button type="submit" class="btn btn-dark">修改</button>
							</div>
						</form>
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

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var passwordInput = document.getElementById("floatingPassword");
			var passwordError = document.getElementById("passwordError");
			var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$/;

			passwordInput.addEventListener("input", function() {
				var password = passwordInput.value;

				if (password.length < 6) {
					passwordError.textContent = "密码长度不能少于6位";
					passwordError.style.display = "block";
				} else if (!/[a-z]/.test(password)) {
					passwordError.textContent = "密码必须包含至少一个小写字母";
					passwordError.style.display = "block";
				} else if (!/[A-Z]/.test(password)) {
					passwordError.textContent = "密码必须包含至少一个大写字母";
					passwordError.style.display = "block";
				} else if (!/\d/.test(password)) {
					passwordError.textContent = "密码必须包含至少一个数字";
					passwordError.style.display = "block";
				} else {
					passwordError.style.display = "none";
				}
			});
		});
	</script>
</body>
</html>
