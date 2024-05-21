<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 라이브러리 사용을 위해서 추가 form: 식으로 사용가능 -->


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Insert title here</title>
		
		<!-- Bootstrap 5 외부 라이브러리 설정 -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>	
		
		<!-- jQuery 외부 라이브러리 설정 -->
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
		
		<!-- 사용자 정의 자바스크립트 -->
		<script>
		</script>
	</head>

	<body>
		<div class="d-flex flex-column vh-100">
			<%@ include file="/WEB-INF/views/common/header.jsp"%>
			<div class="flex-grow-1 m-2">
				<div class="d-flex row">
					<div class="col-md-4">
						<%@ include file="/WEB-INF/views/common/menu.jsp"%>
					</div>
					
					<div class="col-md-8">
						<!-- ###################################### -->
						<div class="card">
							<div class="card-header">sessionData</div>
							<div class="card-body">
								
								<c:if test="${login=='success'}">
									<a href="sessionLogout" class="btn btn-danger btn-sm">로그아웃</a>
								
								</c:if>
								<c:if test="${login!='success'}">
									<form class="m-2" method="post" action="sessionLogin"> <!-- get 방식으로 하면 input값이 넘어옴 -->
			                           <div class="form-group mb-2">
			                               <label for="mid">아이디</label>
			                               <input type="text" class="form-control" id="mid" name="mid" value="${ch04LoginForm.mid}">
			                  				<div><form:errors path="ch04LoginForm.mid"/></div>
			                               
			                           </div>
			                           
			                           <div class="form-group mb-2">
			                               <label for="mpassword">패스워드</label>
			                               <input type="password" class="form-control" id="mpassword" name="mpassword" value="${ch04LoginForm.mpassword}" >
			                           </div>
				                          <!-- 제출 버튼: 형식의 데이터를 서버로 보내겠다. -->
			                           <button type="submit" class="btn btn-info btn-sm mb-2">로그인</button>
	                        		</form>
			                    </c:if>
		                           
		                           
		                           
                        		
							</div>
						</div>
						<!-- ###################################### -->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>