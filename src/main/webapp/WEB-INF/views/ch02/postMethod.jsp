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
			function handleBtn1(){
				var mid = $("#mid").val();
				var mpassword = $("#mpassword").val();
				
				console.log(mid);
				console.log(mpassword);
				
				$.ajax({
					url : "postMethodAjax",
					type : "post",
					/* data : "mid" + mid + "&mpassword=" + mpassword, */
					<!-- 위에는 요청시 사용-->
					//data: {mid:mid, mpassword:mpassword}, 
					<!-- 속성 이름이랑 변수 이름이 같을 경우-->
					data: {mid, mpassword},
					
					<!-- 왜 여기서 data 값이 들어오는 지 모르겠음 -->
					
					<!-- 아래는 응답시 사용-->
					success: function(data){
						// data: {"result" : "siccess"}
						// data: {"result" : "fail", "reason" : "wrongMid"}
						// data: {"result" : "fail", "reason" : "wrongMpassword"}
						// 자동으로 JSON => Javascript Object 변환
						console.log(data);
						
						if(data.result === "success"){
							$("#ajaxResponseInclude").html("로그인 성공");
						} else {
							if(data.reason === "wrongMid"){
								$("#ajaxResponseInclude").html("아이디가 존재하지 않습니다.");
							} else {
								$("#ajaxResponseInclude").html("비밀번호가 틀립니다.");
							}
						}
						
					}
				});
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
							<div class="card-header">POST 방식</div>
							<div class="card-body">
								<form class="m-2" method="post" action="postMethod"> <!-- get 방식으로 하면 input값이 넘어옴 -->
									<input type="hidden" name="chNum" value="ch02"> <!-- name 이 매개변수 이름으로  value가 값으로 넘어감 -->
								 
								   <div class="form-group mb-2">
								       <label for="mid">아이디</label>
								       <input type="text" class="form-control" id="mid" name="mid" value="spring">
								      
								   </div>
								   
								   <div class="form-group mb-2">
								       <label for="mpassword">패스워드</label>
								       <input type="password" class="form-control" id="mpassword" name="mpassword" value="12345">
								   </div>
								   <!-- 제출 버튼: 형식의 데이터를 서버로 보내겠다. -->
								   <!-- <input type="submit" value="GET방식" class="btn btn-info btn-sm"/>
								   
								   <button type="submit" class="btn btn-info btn-sm mb-2">GET 방식</button> -->
								    <button type="submit" class="btn btn-info btn-sm mb-2">POST 방식</button>
								   <%-- <input type="image"
								   src="${pageContext.request.contextPath}/resources/image/photos/button.png"
								   width="100">  --%>
								</form>
								
								<hr/>
								
								<button onclick="handleBtn1()" type="button" class="btn btn-success btn=sm">javaScript로 호출</button>
								
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