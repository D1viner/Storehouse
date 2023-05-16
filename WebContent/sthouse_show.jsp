<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>修改仓库信息</title>
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
</style>
</head>
<body class="d-flex flex-column min-vh-100">
<header class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="sthouse_list.jsp">Storehouse</a>
			<div class="collapse navbar-collapse" id="navbarNav">
				<div class="ms-auto">
					<a class="btn btn-warning" href="sthouse_list.jsp">Back</a>
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
						<h1 class="card-title mb-4">仓库信息修改</h1>
						<form action="stupdate">
						
							<div class="form-floating mb-3 rounded-input">
								<input type="text" class="form-control rounded-input" id="floatingInput"
									name="id" placeholder="Id" readonly value="${st.id }"> <label
									for="floatingInput">Id</label>
							</div>
							
							<div class="form-floating mb-3 rounded-input">
								<input type="text" class="form-control rounded-input" id="floatingInput"
									name="name" placeholder="Name" value="${st.name }"> <label
									for="floatingInput">Name</label>
							</div>
							<div class="form-floating mb-3 rounded-input">
								<input type="text" class="form-control rounded-input" id="floatingInput"
									name="address" placeholder="Address" value="${st.address }"> <label
									for="floatingInput">Address</label>
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
					class="nav-link px-2 text-muted"  onclick="alert('微信号：wzh791307963')">WeChat</a></li>

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
</body>
</html>










