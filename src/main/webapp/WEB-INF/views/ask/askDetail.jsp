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
	        <c:if test="${sessionScope.memberId ne null }">
	            <h1>${memberId }님의 문의내역</h1>
	            <div id="form">
	            	<input type="hidden" name="askWriter" value="${sessionScope.memberId}">
	                <table>
	                    <tr>
	                        <th id="category">문의유형</th>
	                        <td><input type="text" id="selectCategory" name="askCategory" required value="${ask.askCategory }" readonly></td>
	                    </tr>
	                    <tr id="title">
	                        <th>제목</th>
	                        <td><input type="text" name="askSubject" required value="${ask.askSubject }" readonly></td>
	                    </tr>
	                    <tr id="content">
	                        <th>내용</th>
	                        <td><textarea name="askContent" required readonly>${ask.askContent }</textarea></td>
	                    </tr>
	                    <tr id="uploadFile">
	                        <th>첨부파일</th>
	                        <td>
	                        	<c:if test="${ask.askFileRename ne null}">
		                        	<div style="float: left; padding-left: 10px;">
	                        			<img src="../resources/images/icons/file.png" id="file">
			                    		<a href="../resources/uploadFiles/${ask.askFileRename }" download style="text-decoration: underline;">${ask.askFileName }</a>
			                    	</div>
			                    </c:if>
			                    <c:if test="${ask.askFileRename eq null}">
			                    	<input type="text" name="uploadFile" value="첨부파일없음" readonly>
			                    </c:if>
	                        </td>
		                </tr>
	                </table>
	            </div>
	            <div>
	                <button id="formBtn"><a href="/ask/modify.kr?askNo=${ask.askNo}">수정하기</a></button>
	                <button id="formBtn"><a href="/ask/delete.kr?askNo=${ask.askNo}">삭제하기</a></button>
	                <button id="formBtn"><a href="/ask/list.kr">목록이동</a></button>
	            </div>
	        </c:if>
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