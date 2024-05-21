<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
	<!-- BS5 -->
	<!-- Latest compiled and minified CSS -->
	<link href="${pageContext.request.contextPath}/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css"rel="stylesheet">
	<!-- Latest compiled JavaScript -->
	<script src="${pageContext.request.contextPath}/resources/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/jquery/jquery-3.7.1.min.js"></script>
	<!-- 사용자 정의 JavaScript -->
	<script type="text/javascript">
</script>
</head>

<body>
	<div class="d-flex flex-column vh-100">
		<%-- include 지시자는 소스 복사 개념 --%>
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<div class="flex-grow-1 m-2">
			<div class="d-flex row">
				<div class="col-md-4">
					<%@ include file="/WEB-INF/views/common/menu.jsp"%>
				</div>
				<div class="col-md-8">
					<div class="card">
						<div class="card-header">권한 불충분</div>
						<div class="card-body">
					
							현재 로그인한 사용자는 권한이 없어 페이지를 볼 수 없습니다.
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>