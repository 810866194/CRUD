<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h3>添加用户</h3>
	<form action="User_save" method="post">
		姓名:<input type="text" name="uname" /> <br />
		密码:<input type="text" name="upwd" /> <br />
		日期:<input type="text" name="udate" /> <br />
		<button type="submit">添加</button>
	</form>
</body>
</html>