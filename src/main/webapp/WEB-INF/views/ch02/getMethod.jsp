<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
			function handleBtn1() {
				console.log("handleBtn1() 실행");
				location.href = "getMethod" 
				// 왜 location.href = ""여도 자기 주소로 가고 location.href = "getMethod" 로 해도 자기 주소로 가는 지 모르겠음?
			}
			
			function handleBtn2() {
				console.log("handleBtn2() 실행");
				$.ajax({
					url : "getMethodAjax",
					type : "get",
					data : "bkind=notice&bno=300",
					/* data : {bkind: "notice", bno: 300}, */
				
						success: function(data){
						console.log(data);
						$("#ajaxResponseInclude").html(data);
					} 
				});
				
				/*
				$.get("getMethodAjax", {bkind:"notice", bno:300}, function(data){
					$("#ajaxResponseInclude").html(data);
				});
				*/
			}
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
							<div class="card-header">GET 방식</div>
							<div class="card-body">
								<form class="m-2" method="get" action="getMethod"> <!-- get 방식으로 하면 input값이 넘어옴 -->
									<input type="hidden" name="chNum" value="ch02"> <!-- name 이 매개변수 이름으로  value가 값으로 넘어감 -->
								   <div class="form-group mb-2">
								       <label for="bkind">bkind</label>
								       <input type="text" class="form-control" id="bkind" name="bkind" value="free">
								       <small id="bkindHelp" class="form-text text-muted">input board kind</small>
								   </div>
								   
								   <div class="form-group mb-2">
								       <label for="bno">bno</label>
								       <input type="text" class="form-control" id="bno" name="bno" value="1">
								       <small id="bnoHelp" class="form-text text-muted">input board no</small>
								   </div>
								   <!-- 제출 버튼: 형식의 데이터를 서버로 보내겠다. -->
								   <!-- <input type="submit" value="GET방식" class="btn btn-info btn-sm"/>
								   <button type="submit" class="btn btn-info btn-sm mb-2">GET 방식</button> -->
								   <input type="image" 
								   src="${pageContext.request.contextPath}/resources/image/photos/button.png"
								   width="100"> 
								</form>
								
								<hr/>
								
								<button onclick="handleBtn1()" type="button" class="btn btn-success btn=sm">javaScript로 호출</button>
								<button onclick="handleBtn2()" type="button" class="btn btn-success btn=sm">AJAX로 호출</button>
								<div id="ajaxResponseInclude" class="mt-2">
								 
								</div>
							
							</div>
						</div>
						<!-- ###################################### -->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>