<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/customerCenter/ask.css">
        <title>CANDYTOON</title>
    <body>
         <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
        <!-- 메인 -->
        <main>
            <div id="center">
	                <div id = "centerMenu">
	                    <ul>
	                        <li><a href="/notice/list.kr">공지사항</a></li>
	                        <li><a href="/fnq/list.kr">FAQ</a></li>
	                        <li><a href="/ask/list.kr">1:1문의</a></li>
	                    </ul>
	                </div>
                <div id="centerSearch">
                    <input type="search" placeholder="궁금한 점을 검색해 보세요.">
                    <img src="../resources/images/icons/centerSearch.png" alt="검색">
                </div>
            </div>
            <div id = "ask">      
                <table>
                	<colgroup>
                		<col width="15%">
                		<col width="15%">
                		<col width="45%">
                		<col width="25%">
                	</colgroup>
                	<thead id=tableHead>
	                    <tr>
	                        <th>번호</th>
	                        <th>문의유형</th>
	                        <th>제목</th>
	                        <th>등록일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:forEach var="ask" items="${aList }">
		                    <tr>
		                        <td>${ask.askNo }</td>
		                        <td>${ask.askCategory }</td>
		                        <td><a href="/ask/detail.kr?askNo=${ask.askNo }">${ask.askSubject }</a></td>
		                        <td>${ask.askDate }</td>
		                    </tr>
	                	</c:forEach>
	                </tbody>
                </table>
            </div>
            <div>    
                <button id="askBtn"><a href="/ask/insert.kr">문의등록</a></button>
            </div>
            <div id="pages">
                <ul>
                    <li>
                    	<c:if test="${pInfo.startNavi != 1}">
                    		<c:url var="pageUrl" value="/ask/list.kr">
                    			<c:param name="page" value="${pInfo.startNavi -1}"></c:param>
                    		</c:url>
                    		<a href="${pageUrl}">이전</a>
                    	</c:if>
                    	<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
                    		<c:url var="pageUrl" value="/ask/list.kr">
                    			<c:param name="page" value="${p}"></c:param>
                    		</c:url>
                    		<a href="${pageUrl}">${p}</a>
                    	</c:forEach>
                    	<c:if test="${pInfo.endNavi != pInfo.naviTotalCount}">
                    		<c:url var="pageUrl" value="/ask/list.kr">
                    			<c:param name="page" value="${pInfo.endNavi +1}"></c:param>
                    		</c:url>
                    		<a href="${pageUrl}">다음</a>
                    	</c:if>
                   </li>
                </ul>
            </div>
        </main>
        
        <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    </body>
</html>