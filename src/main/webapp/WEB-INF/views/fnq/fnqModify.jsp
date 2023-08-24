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
            <form action="/fnq/modify.kr" method="post" enctype="multipart/form-data">
	            <div id="fnqForm">
	            <input type="hidden" name="fnqNo" value="${fnq.fnqNo}">
	            <input type="hidden" name="fnqFileName"   value="${fnq.fnqFileName}">
	            <input type="hidden" name="fnqFileRename" value="${fnq.fnqFileRename}">
	            <input type="hidden" name="fnqFilePath"   value="${fnq.fnqFilePath}">
	            <input type="hidden" name="fnqFileLength" value="${fnq.fnqFileLength}">
	                <table>
	                    <tr>
	                        <th id="fnqType">카테고리</th>
	                        <td>
	                            <select id="selectType" name="fnqCategory" required>
	                                <option value="결제/환불">결제/환불</option>
	                                <option value="회원/계정">회원/계정</option>
	                                <option value="사이트이용">사이트이용</option>
	                                <option value="이벤트">이벤트</option>
	                                <option value="기타">기타</option>
	                            </select>
	                        </td>
	                    </tr>
	                    <tr id="fnqSubject">
	                        <th>제목</th>
	                        <td><input type="text" name="fnqSubject" required value="${fnq.fnqSubject }"></td>
	                    </tr>
	                    <tr id="fnqContent">
	                        <th>내용</th>
	                        <td><textarea name="fnqContent" required>${fnq.fnqContent }</textarea></td>
	                    </tr>
	                    <tr id="uploadFile">
	                        <th>첨부파일</th>
	                        <td>
	                        	<c:if test="${!empty fnq.fnqFileRename}">
	                        		<div style="float: left; padding: 10px;">
	                        			<img src="../resources/images/icons/file.png" id="file">
				                    	<a href="../resources/uploadFiles/${fnq.fnqFileRename }" download style="text-decoration: underline;">${fnq.fnqFileName }</a>
	                        		</div>
									<input type="file" name="uploadFile">
	                        	</c:if>
	                        	<c:if test="${empty fnq.fnqFileRename}">
	                        		<input type="text" value="첨부파일없음" readonly>
	                        		<input type="file" name="uploadFile">
	                        	</c:if>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	            <div>
	                <button id="fnqBtn" type="submit">수정완료</button>
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