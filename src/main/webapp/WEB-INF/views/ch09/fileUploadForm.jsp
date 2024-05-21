<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                     <div class="card-header">파일 업로드 폼</div>
                     <div class="card-body">
                        <!--  -->
                        <form method="post" enctype="multipart/form-data" action="fileUpload">
                        <!-- enctype="multipart/form-data" 이게 없으면 쿼리스트링으로 넘어감 -->
                        
                     <div class="mt-2 form-group">
                        <label for="title">File Title</label> 
                        <input type="text" class="form-control" id="title" name="title" placeholder="제목">
                     </div>
                     
                     <div class="mt-2 form-group mb-2">
                        <label for="desc">File Description</label> 
                        <input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
                     </div>
                     
                     <div class="form-group">
                         <label for="attach">file Attach</label>
                         <input type="file" class="form-control-file" id="attach" name="attach">
                         <!-- 파일 업로드를 위한 태그 -->
                       </div>
                       
                       <!-- 방법1 -->
                       <button class="mt-2 btn btn-info btn-sm">Form 파일 업로드</button>
                  </form>
                     </div>
                  </div>
                  <!-- ###################################### -->
               </div>
            </div>
         </div>
      </div>
   </body>
</html>