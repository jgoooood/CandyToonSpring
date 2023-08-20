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
            <h1>공지사항</h1>
            <div id="noticeForm">
                <table>
                    <tr id="noticeSubject">
                        <th>제목</th>
                        <td><input type="text" name="noticeSubject" required value="${notice.noticeSubject }" readonly></td>
                    </tr>
                    <tr id="noticeContent">
                        <th>내용</th>
                        <td><textarea name="noticeContent" required readonly>${notice.noticeContent }</textarea></td>
                    </tr>
                </table>
            </div>
            <div>
                <button id="noticeBtn"><a href="/notice/modify.kr?noticeNo=${notice.noticeNo}">수정하기</a></button>
                <button id="noticeBtn"><a href="/notice/delete.kr?noticeNo=${notice.noticeNo}">삭제하기</a></button>
                <button id="noticeBtn"><a href="/notice/list.kr">목록이동</a></button>
            </div>

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