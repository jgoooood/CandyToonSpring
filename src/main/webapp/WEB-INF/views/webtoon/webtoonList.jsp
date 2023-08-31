<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/webtoon/list.css">
        <title>CANDYTOON</title>
        <style>
        	#centerMenu li:first-child {
        		background-color: rgb(252, 117, 7);
    		}

    		#centerMenu li:first-child a {
        		color: white;
    		}
        </style>
    <body>
       <!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
        
        <!-- 메인 -->
        <main>
        	<div id ="center">
				<div id = "centerMenu">
                    <ul>
                        <li><a href="/webtoon/list.kr">웹툰 리스트</a></li>
                        <li><a href="/webtoon/insert.kr">웹툰 정보등록</a></li>
                    </ul>
	            </div>
	            <div id="centerSearch">
	            	<form action="/notice/search.kr" method="get">
	        			<div id= "searchKeyword">
	                		<input type="search" name="searchKeyword" placeholder="궁금한 점을 검색해 보세요.">
	          				<img src="../resources/images/icons/centerSearch.png" alt="검색">
	        			</div>
	        			<div id = "searchCondition">
		             		<select name="searchCondition" style="height:30px">
		             			<option value="all">전체</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
		             		</select>
	        			</div>
	                </form>
	            </div>
        	</div>
        	
            <div id = "board">
                <table>
                	<colgroup>
                		<col width="10%">
                		<col width="40%">
                		<col width="20%">
                		<col width="20%">
                		<col width="10%">
                	</colgroup>
                	<thead id=tableHead>
	                    <tr>
	                        <th>웹툰번호</th>
	                        <th>웹툰제목</th>
	                        <th>글작가</th>
	                        <th>그림작가</th>
	                        <th>오픈일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${allList}" var="webtoon">
		                    <tr>
		                        <td>${webtoon.webtoonNo }</td>
		                        <td><a href="/webtoon/detail.kr?webtoonNo=${webtoon.webtoonNo }">${webtoon.webtoonTitle }</a></td>
		                        <td>${webtoon.webtoonWriter}</td>
		                        <td>${webtoon.webtoonWriter}</td>
		                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${webtoon.wOpenDate }"/></td>
		                    </tr>	                	
	                	</c:forEach>
	                </tbody>
                </table>
            </div>
            
            <div id="pages">
                <ul>
                	<li>
                		<c:if test="${pInfo.startNavi != 1 }">
                			<c:url var="pageUrl" value="/webtoon/list.kr" >
                				<c:param name="page" value="${pInfo.startNavi -1 }"></c:param>
                			</c:url>
                			<a href="${pageUrl }">이전</a>
                    	</c:if>
                    	<c:forEach begin="${pInfo.startNavi}" end="${pInfo.endNavi }" var="p">
                    		<c:url var="pageUrl" value="/webtoon/list.kr">
                    			<c:param name="page" value="${p }"></c:param>
                    		</c:url>
                    		<a href="${pageUrl}">${p }</a>
                    	</c:forEach>
                    	<c:if test="${pInfo.endNavi != pInfo.naviTotalCount }">
                			<c:url var="pageUrl" value="/webtoon/list.kr" >
                				<c:param name="page" value="${pInfo.endNavi +1 }"></c:param>
                			</c:url>
                			<a href="${pageUrl }">다음</a>
                    	</c:if>
                    </li>
                </ul>
            </div>
            
        </main>
        <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    </body>
</html>