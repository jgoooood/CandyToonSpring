<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/customerCenter/noticeForm.css">
        <title>CANDYTOON</title>
    <body>
        <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
        	<form action="/notice/modify.kr" method="post" enctype="multipart/form-data">
	            <h1>공지사항</h1>
	            <div id="noticeForm">
                	<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
	                <table>
	                    <tr id="noticeSubject">
	                        <th>제목</th>
	                        <td><input type="text" name="noticeSubject" required value="${notice.noticeSubject }"></td>
	                    </tr>
	                    <tr id="noticeContent">
	                        <th>내용</th>
	                        <td><textarea name="noticeContent" required>${notice.noticeContent }</textarea></td>
	                    </tr>
	                    <tr id="uploadFile">
	                        <th>첨부파일</th>
	                        <td>
	                        	<c:if test="${empty notice.noticeFileName}">
	                        		<input type="file" name="uploadFile">
	                        	</c:if>
	                        	<c:if test="${!empty notice.noticeFileName}">
	                        		<input type="text" value="등록파일 : ${notice.noticeFileName}">
	                        		<input type="file" name="uploadFile">
	                        	</c:if>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	            <div>
	                <button id="noticeBtn" type="submit">수정완료</button>
	            </div>
			</form>
        </main>
        <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
        
		<!--  
        <script>
            document.querySelector("#askBtn").addEventListener("click", function(){
                alert("이용문의 등록이 완료되었습니다.");
                location.replace("./ask.jsp");
            })
        </script>-->
    </body>
</html>