<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>서비스 결과 실패</title>
	</head>
	<body>
		<h1>${msg}</h1>
		<script>
			const alertMsg = '${alertMsg}';
			const url = '${url}';
			alert(alertMsg);
			location.href = url;
		</script>
	</body>
</html>