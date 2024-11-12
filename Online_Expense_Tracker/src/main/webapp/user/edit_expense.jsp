<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ page import="com.dao.*"%>
<%@ page import="com.entity.*"%>
<%@ page import="com.db.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../component/all_css.jsp"%>
<style type="text/css">
.card-sh {
	box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.8);
}
</style>
</head>
<body class="bd-light">
	<%@ include file="../component/navbar.jsp"%>


	<c:if test="${empty loginUser }">
		<c:redirect url="../Login.jsp"></c:redirect>
	</c:if>


	<%
	int id=Integer.parseInt(request.getParameter("id"));
	
	ExpenseDao dao =new ExpenseDao(HibernateUtil.getSessionFactory()); 
	Expense ex=dao.getExpensebyId(id);
	
	
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card card-sh">
					<div class="card-header text=center">
						<p class="fs-3">Edit Expense</p>

					</div>
					<div class="card-body">
						<form action="../updateExpense" method="post">
							<div class="mb-3">
								<label>Title</label> <input type="text" name="title"
									class="form-control" value="<%= ex.getTitle() %>">
							</div>
							<div class="mb-3">
								<label>Date </label> <input type="date" name="date"
									class="form-control" value="<%=ex.getDate()%>">
							</div>
							<div class="mb-3">
								<label>Time </label> <input type="time" name="time"
									class="form-control" value="<%=ex.getTime()%>" >
							</div>
							<div class="mb-3">
								<label>Description </label> <input type="text"
									name="description" class="form-control" value="<%=ex.getDescription()%>">
							</div>
							<div class="mb-3">
								<label>Price </label> <input type="text" name="price"
									class="form-control" value="<%=ex.getPrice()%>">
							</div>
							<input type="hidden" name="id" value="<%= ex.getId() %>" >
							<button class="btn btn-success col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
    // Automatically remove the flash message after 3 seconds
    setTimeout(() => {
        const flashMsg = document.getElementById("flash-msg");
        if (flashMsg) flashMsg.style.display = 'none';
    }, 3000);
</script>
</body>
</html>