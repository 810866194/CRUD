<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h3>修改用户</h3>
	<form action="User_edit" method="post">
		<input type="hidden" name="uid" value="${user.uid }" />
		姓名:<input type="text" name="uname" value="${user.uname }" /> <br />
		密码:<input type="text" name="upwd" value="${user.upwd }" /> <br />
		日期:<input type="text" name="udate" value="${user.udate }" /> <br />
		<button type="submit">修改</button>
	</form>
</body>
</html>