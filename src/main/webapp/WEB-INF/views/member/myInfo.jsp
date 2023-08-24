<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
    <title>마이페이지</title>
    <link rel="stylesheet" href="../resources/CSS/member/myInfo.css">
    
    <body>
        <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
        	<!-- 마이페이지 메뉴 -->
            <jsp:include page="/WEB-INF/views/include/myPageMenu.jsp"></jsp:include>
            <section id="rightSection">
                <div id="topArea">
                	<div>
                    	<h1>나의 정보</h1><br>
                	</div>
                	<div id="info">
	                    <label for="memberId">아이디</label>
	                    <p id="memberId" name="memberId">${member.memberId }</p>
	                    <label for="email">이메일주소</label>
	                    <p id="email" name="memberEmail">${member.memberEmail }</p>
	                    <label for="memberName">이름</label>
	                    <p id="memberName" name="memberName">${member.memberName }</p>
                	</div>
                </div>
               	<div id="bottomArea">
                	<button type="button">비밀번호 변경하기</button>
                    <button>이메일주소 변경하기</button>
                    <button>회원탈퇴</button>
               	</div>
            </section>
        </main>
        <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    </body>
</html>