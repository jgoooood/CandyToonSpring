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
            <form action="/notice/insert.kr" method="post" enctype="multipart/form-data">
	            <h1>웹툰 정보 등록</h1>
	            <div id="form">
	                <ul>
	                    <li>
	                        <label>웹툰 제목</label>
	                        <input type="text" name="webtoonTitle" required placeholder="웹툰 제목을 입력해주세요.">
	                    </li><br>
	                    <li>
	                        <label>글작가</label>
	                        <input type="text" name="webtoonWriter" required placeholder="글작가 이름을 입력해주세요.">
	                    </li><br>
	                    <li>
	                        <label>그림작가</label>
	                        <input type="text" name="webtoonPainter" required placeholder="그림작가 이름을 입력해주세요.">
	                    </li><br>
	                    <li>
	                        <label>대표표지</label>
	                        <input type="file" id="uploadFile" name="uploadFile" required><br>
	                    </li><br>
	                    <li>
	                        <label>웹툰장르</label>
	                         <select name="webtoonGenre" required>
                                <option value="판타지">판타지</option>
                                <option value="로맨스">로맨스</option>
                                <option value="액션">액션</option>
                                <option value="개그">개그</option>
                                <option value="기타">기타</option>
                            </select>
	                    </li><br>
	                    <li>
	                        <label>대여가격</label>
	                        <input type="text" name="wLentPrice" required placeholder="숫자만 입력 ex)300">
	                    </li><br>
	                    <li>
	                        <label>소장가격</label>
	                        <input type="text" name="wLentPrice" required placeholder="숫자만 입력 ex)500">
	                    </li><br>
	                    <li>
	                        <label>오픈날짜</label>
	                        <input type="date" name="wLentPrice" required>
	                    </li><br>
	                </ul>
	            </div>
	            <div>
	                <button id="formBtn" type="submit">등록하기</button>
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