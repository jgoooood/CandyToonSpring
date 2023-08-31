<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/webtoon/info.css">
        <title>CANDYTOON</title>
    <body>
        <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
            <h1>웹툰 정보</h1>
            <div id="form">
            	<div id="formLeft">
            	<input type="hidden" value="${webtoon.webtoonNo }">
	                <ul>
	                    <li>
	                        <label>웹툰 제목</label>
	                        <span>${webtoon.webtoonTitle }</span>
	                    </li><br>
	                    <li>
	                        <label>글작가</label>
	                        <span>${webtoon.webtoonWriter }</span>
	                    </li><br>
	                    <li>
	                        <label>그림작가</label>
	                        <span>${webtoon.webtoonTitle }</span>
	                    </li><br>
	                    <li>
	                        <label>웹툰장르</label>
	                        <span>${webtoon.webtoonGenre }</span>
	                    </li><br>
	                    <li>
	                        <label>대여가격</label>
	                        <span>${webtoon.wLentPrice }</span>
	                    </li><br>
	                    <li>
	                        <label>소장가격</label>
	                        <span>${webtoon.wBuyPrice }</span>
	                    </li><br>
	                    <li>
	                        <label>오픈날짜</label>
	                        <span>${webtoon.wOpenDate }</span>
	                    </li><br>
	                    <li>
	                        <label>최근 시리즈 업데이트</label>
	                        <span>${webtoon.wUpdateDate }</span>
	                    </li><br>
	                    <li>
	                        <label>완결여부</label>
	                        <span>${webtoon.wEndYn}</span>
	                    </li><br>
	                    <li>
	                        <label>조회수</label>
	                        <span>${webtoon.wViewCount }</span>
	                    </li><br>
	                </ul>
            	</div>
            	<div id="formRight">
					<p style="font-size: 20px"><b>대표표지</b></p><br>
					<img src="../resources/uploadFiles/${webtoon.wCoverRename }" alt="${webtoon.webtoonTitle }">
            	</div>
            </div>
            <div>
                <button id="formBtn"><a href="/webtoon/modify.kr?webtoonNo=${webtoon.webtoonNo }">수정하기</a></button>
                <button id="formBtn"><a href="/webtoon/list.kr">목록이동</a></button>
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