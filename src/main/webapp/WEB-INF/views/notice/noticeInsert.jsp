<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/customerCenter/form.css">
        <title>CANDYTOON</title>
    <body>
        <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
            <form action="/notice/insert.kr" method="post" enctype="multipart/form-data">
	            <h1>공지사항 등록</h1>
	            <div id="form">
	                <table>
	                    <tr id="title">
	                        <th>제목</th>
	                        <td><input type="text" name="noticeSubject" required placeholder="공지 제목을 입력해주세요."></td>
	                    </tr>
	                    <tr id="content">
	                        <th>내용</th>
	                        <td><textarea name="noticeContent" placeholder="공지 내용을 입력해주세요." required></textarea></td>
	                    </tr>
	                    <tr id="uploadFile">
	                        <th>첨부파일</th>
	                        <td><input type="file" name="uploadFile"></td>
	                    </tr>
	                </table>
	            </div>
	            <div>
	                <button id="formBtn" type="submit">등록하기</button>
	                <button id="formBtn"><a href="/notice/list.kr">목록이동</a></button>
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