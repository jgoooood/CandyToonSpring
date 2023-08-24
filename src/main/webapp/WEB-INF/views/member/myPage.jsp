<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
    <title>마이페이지</title>
    <link rel="stylesheet" href="../resources/CSS/member/myPage.css">
    
    <body>
        <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
        	<!-- 마이페이지 메뉴 -->
			<jsp:include page="/WEB-INF/views/include/myPageMenu.jsp"></jsp:include>
            <section id="rightSection">
                <div id="topArea">
                    <div id="userAccount">
                        <div><img src="../resources/images/icons/account.png" alt="계정"></div>
                        <div>
                       		<p name="memberId">${member.memberId }<br>
                            ${member.memberEmail }</p>
                        </div>
                    </div>
                    <div id="userCandy">
                        <div><img src="../resources/images/icons/candy.png" alt="캔디"></div>
                        <div id="candy">
                            <span>보유캔디</span>
                            <span id="have">50개</span>
                        </div>
                    </div>
                </div>
                <div id="bottomArea">
                        <ul>
                            <li><p>소장 작품<br><span class="count">35</span></p></li>
                            <li><p>최근 조회한 작품<br><span class="count">7</span></p></li>
                            <li><p>관심작품<br><span class="count">5</span></p></li>
                        </ul>
                </div>
            </section>
        </main>
        <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    </body>
</html>