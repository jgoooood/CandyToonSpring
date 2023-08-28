<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
       		<div id ="center">
				<div id = "centerMenu">
			       	<c:if test="${sessionScope.memberId ne null }">
	                    <ul>
	                        <li><a href="/notice/list.kr">공지사항</a></li>
	                        <li><a href="/fnq/list.kr">FAQ</a></li>
	                        <li><a href="/ask/list.kr">1:1문의</a></li>
	                    </ul>
		        	</c:if>
		        	<c:if test="${sessionScope.memberId eq null }">
       					<h1>로그인 후 이용바랍니다.</h1>
       				</c:if>
	            </div>
	            <div id="centerSearch">
	            	<form action="/ask/search.kr" method="get">
	        			<div id= "searchKeyword">
	                		<input type="search" name="searchKeyword" value="${searchKeyword}" placeholder="궁금한 점을 검색해 보세요.">
	          				<img src="../resources/images/icons/centerSearch.png" alt="검색">
	        			</div>
	        			<div id = "searchCondition">
		             		<select name="searchCondition" style="height:30px">
		             			<option value="all" <c:if test="${searchCondition == 'all'}"> selected </c:if>>전체</option>
								<option value="category" <c:if test="${searchCondition == 'category'}"> selected </c:if>>유형</option>
								<option value="title" <c:if test="${searchCondition == 'title'}"> selected </c:if>>제목</option>
								<option value="content" <c:if test="${searchCondition == 'content'}"> selected </c:if>>내용</option>
		             		</select>
	        			</div>
	                </form>
	            </div>
        	</div>
            <div id = "ask">      
                <table>
                	<colgroup>
                		<col width="10%">
                		<col width="15%">
                		<col width="40%">
                		<col width="15%">
                		<col width="20%">
                	</colgroup>
                	<thead id=tableHead>
	                    <tr>
	                        <th>번호</th>
	                        <th>유형</th>
	                        <th>제목</th>
	                        <th>첨부</th>
	                        <th>등록일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:forEach var="ask" items="${sList }" varStatus="i">
		                    <tr>
		                        <td>${i.count}</td>
		                        <td>${ask.askCategory }</td>
		                        <td><a href="/ask/detail.kr?askNo=${ask.askNo }">${ask.askSubject }</a></td>
		                        <td>
		                        	<c:if test="${ask.askFileName ne null}">
		                        		<img src="../resources/images/icons/file.png" id="file">
		                        	</c:if>
		                        </td>
		                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ask.askDate }" /></td>
		                    </tr>
	                	</c:forEach>
	                </tbody>
                </table>
            </div>
            <div>    
                <a id ="askBtn" href="/ask/insert.kr">문의등록</a>
            </div>
            <div id="pages">
                <ul>
                    <li>
                    	<c:if test="${pInfo.startNavi ne 1}">
                    		<c:url var="pageUrl" value="/ask/search.kr">
                    			<c:param name="page" value="${pInfo.startNavi -1}"></c:param>
                    			<c:param name="searchKeyword" value="${searchKeyword}"></c:param>
                    			<c:param name="searchCondition" value="${searchCondition}"></c:param>
                    		</c:url>
                    		<a href="${pageUrl}">이전</a>
                    	</c:if>
                    	<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
                    		<c:url var="pageUrl" value="/ask/search.kr">
                    			<c:param name="page" value="${p}"></c:param>
                    			<c:param name="searchKeyword" value="${searchKeyword}"></c:param>
                    			<c:param name="searchCondition" value="${searchCondition}"></c:param>
                    		</c:url>
                    		<a href="${pageUrl}">${p}</a>
                    	</c:forEach>
                    	<c:if test="${pInfo.endNavi ne pInfo.naviTotalCount}">
                    		<c:url var="pageUrl" value="/ask/search.kr">
                    			<c:param name="page" value="${pInfo.endNavi +1}"></c:param>
                    			<c:param name="searchKeyword" value="${searchKeyword}"></c:param>
                    			<c:param name="searchCondition" value="${searchCondition}"></c:param>
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