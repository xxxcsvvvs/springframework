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
						<div class="card-header">로그인</div>
						<div class="card-body">

							<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
								<div class="alert alert-danger mb-2" role="alert">
									<c:if
										test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">
							               	아이디 또는 비밀번호가 틀립니다.
							            </c:if>
								</div>
							</c:if>

							<form method="post"
								action="${pageContext.request.contextPath}/login">
								<!-- 절대경로인 이유는 -->
								<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%> <!-- 활성화되어있다면 스프링 시큐리티가 자동으로 값을 넣어줌 -->
								<div class="form-group mb-2">
									<label for="mid">Member ID</label> <input type="text"
										class="form-control" id="mid" name="mid">
								</div>
								<div class="form-group mb-2">
									<label for="mpassword">Member Password</label> <input
										type="password" class="form-control" id="mpassword"
										name="mpassword">
								</div>
								<button type="submit" class="btn btn-info btn-sm mt-2">로그인</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>