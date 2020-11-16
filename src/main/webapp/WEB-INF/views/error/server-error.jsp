<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>ERP 예제</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="<c:url value="/resources/css/erp.css"/>" /> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="body mt-5">
	<div class="container mt-5">
		<div class="row">
			<div class="col-12" style="height: 200px;"></div>
			<div class="col-12 mt-5">
				<div class="jumbotron">
					<h1>서버에러</h1>
					<p>요청처리 중 서버 에러가 발생하였습니다.</p>
					<p>에러 내용 : <strong class="text-danger">${pageContext.exception.message }</strong> </p>
					<p> <a class="btn btn-primary" type="button" href="main.do">돌아가기</a></p>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>