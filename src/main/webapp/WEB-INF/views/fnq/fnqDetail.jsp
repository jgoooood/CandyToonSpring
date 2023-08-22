<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/customerCenter/fnqForm.css">
        <title>CANDYTOON</title>
    <body>
        <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
            <h1>자주 묻는 질문</h1>
            <div id="fnqForm">
                <table>
                    <tr>
                        <th id="fnqType">카테고리</th>
                        <td><input type="text" id="fnqCategory" name="fnqCategory" required value="${fnq.fnqCategory }" readonly></td>
                    </tr>
                    <tr id="fnqSubject">
                        <th>제목</th>
                        <td><input type="text" name="fnqSubject" required value="${fnq.fnqSubject }" readonly></td>
                    </tr>
                    <tr id="fnqContent">
                        <th>내용</th>
                        <td><textarea name="fnqContent" required readonly>${fnq.fnqContent }</textarea></td>
                    </tr>
                    <tr id="uploadFile">
                        <th>첨부파일</th>
                        <td>
                        	<c:if test="${fnq.fnqFileName ne null}">
		                    	<input type="text" name="uploadFile" value="${fnq.fnqFileName}" readonly>
		                    </c:if>
		                    <c:if test="${fnq.fnqFileName eq null}">
		                    	<input type="text" name="uploadFile" value="첨부파일없음" readonly>
		                    </c:if>
                        </td>
	                </tr>
                </table>
            </div>
            <div>
                <button id="fnqBtn"><a href="/fnq/modify.kr?fnqNo=${fnq.fnqNo}">수정하기</a></button>
                <button id="fnqBtn"><a href="/fnq/delete.kr?fnqNo=${fnq.fnqNo}">삭제하기</a></button>
                <button id="fnqBtn"><a href="/fnq/list.kr">목록이동</a></button>
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