<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/webtoon/form.css">
        <title>CANDYTOON</title>
    <body>
        <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
			<form action="/webtoon/modify.kr" method="post" enctype="multipart/form-data">
	            <h1>웹툰 정보 수정</h1>
	            <div id="form">
                    <input type="hidden" name="webtoonNo" required value="${webtoon.webtoonNo}">
                    <input type="hidden" name="wCoverName" value="${webtoon.wCoverName}">
                	<input type="hidden" name="wCoverRename" value="${webtoon.wCoverRename}">
                	<input type="hidden" name="wCoverPath" value="${webtoon.wCoverPath}">
	                <ul>
	                    <li>
	                        <label>웹툰 제목</label>
	                        <input type="text" name="webtoonTitle" required value="${webtoon.webtoonTitle}">
	                    </li><br>
	                    <li>
	                        <label>글작가</label>
	                        <input type="text" name="webtoonWriter" required value="${webtoon.webtoonWriter}">
	                    </li><br>
	                    <li>
	                        <label>그림작가</label>
	                        <input type="text" name="webtoonPainter" required value="${webtoon.webtoonPainter}">
	                    </li><br>
	                    <li>
	                        <label>현재표지</label>
	                        <a href="../resources/uploadFiles/${webtoon.wCoverRename }" download style="text-decoration: underline;">${webtoon.wCoverName }</a>
	                    </li><br>
	                    <li>
	                        <label>표지변경</label>
	                        <input type="file" id="uploadFile" name="uploadFile"><br>
	                    </li><br>
	                    <li>
	                        <label>웹툰장르</label>
	                         <select name="webtoonGenre" required>
                                <option value="판타지">판타지</option>
                                <option value="로맨스">로맨스</option>
                                <option value="액션">액션</option>
                                <option value="무협">무협</option>
                                <option value="개그">개그</option>
                                <option value="기타">기타</option>
                            </select>
	                    </li><br>
	                    <li>
	                        <label>대여가격</label>
	                        <input type="text" name="wLentPrice" required value="${webtoon.wLentPrice}">
	                    </li><br>
	                    <li>
	                        <label>소장가격</label>
	                        <input type="text" name="wBuyPrice" required value="${webtoon.wBuyPrice}">
	                    </li><br>
	                    <li>
	                        <label>오픈날짜</label>
	                        <input type="date" name="wOpenDate" required value="${webtoon.wOpenDate}">
	                    </li><br>
	                    <li>
		                    <label>완결여부</label>
		                    <input type="radio" name="wEndYn" value="N" class="radio" <c:if test="${webtoon.wEndYn eq 'N'}">checked</c:if>> 연재중 
		                    <input type="radio" name="wEndYn" value="Y" class="radio" <c:if test="${webtoon.wEndYn eq 'Y'}">checked</c:if>> 완결
		                    <br>
	                    </li>
	                </ul>
                </div>
	            <div>
	                <button id="formBtn" type="submit">수정하기</button>
	                <button id="formBtn"><a href="/webtoon/list.kr">목록이동</a></button>
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