<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Insert title here</title>
      
      <!-- Bootstrap 5를 위한 외부 라이브러리 설정 -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
      
      <!-- jquery 외부 라이브러리 사용 설정 -->
      <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
      
      <!-- 사용자 정의 자바스크립트 -->
      <script>
      
      </script>
   </head>
   <body>
      <div class="card">
          <div class="card-header">권한별 메뉴 보기</div>
                <div class="card-body">
                  	<ul>
                  		<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="${pageContext.request.contextPath}/ch17/admin/page">ADMIN페이지</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_MANAGER')">
							<li><a href="${pageContext.request.contextPath}/ch17/manager/page">MANAGER페이지</a></li>
							</sec:authorize>
						<sec:authorize access="hasRole('ROLE_USER')">	
							<li><a href="${pageContext.request.contextPath}/ch17/user/page">USER페이지(로그인 했을 경우)</a></li>
						</sec:authorize>
					</ul>
                </div> 
            </div>
         </div>
   </body>
</html>