<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:if test="${ empty curUser }">
		<form action="/login" method="post">
			<input type="text" name="username" /><br>
			<input type="password" name="password"/><br>
			<input type="submit" value="提交"/>
		</form>
	</c:if>
	<c:if test="${! empty curUser }">
		欢迎：${curUser}
	</c:if>
</body>
</html>