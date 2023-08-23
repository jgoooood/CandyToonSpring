<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
    <!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="../resources/CSS/customerCenter/notice.css">
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
	                    <ul>
	                        <li><a href="/notice/list.kr">공지사항</a></li>
	                        <li><a href="/fnq/list.kr">FAQ</a></li>
	                    </ul>
		            </c:if>
	            </div>
	            <div id="centerSearch">
	            	<form action="/notice/search.kh" method="get">
	        			<div id= "searchKeyword">
	                		<input type="search" name="searchKeyword" placeholder="궁금한 점을 검색해 보세요.">
	          				<img src="../resources/images/icons/centerSearch.png" alt="검색">
	        			</div>
	        			<div id = "searchCondition">
		             		<select name="searchCondition" style="height:30px">
		             			<option value="all">전체</option>
								<option value="writer">작성자</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
		             		</select>
	        			</div>
	                </form>
	            </div>
        	</div>
            <div id = "notice">
                <table>
                	<colgroup>
                		<col width="15%">
                		<col width="50%">
                		<col width="15%">
                		<col width="20%">
                	</colgroup>
                	<thead id=tableHead>
	                    <tr>
	                        <th>번호</th>
	                        <th>제목</th>
	                        <th>첨부</th>
	                        <th>등록일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${nList}" var="notice">
		                    <tr>
		                        <td>${notice.noticeNo }</td>
		                        <td><a href="/notice/detail.kr?noticeNo=${notice.noticeNo }">${notice.noticeSubject }</a></td>
		                        <td>
		                        	<c:if test="${notice.noticeFileName ne null}">
		                        		<img src="../resources/images/icons/file.png" id="file">
		                        	</c:if>
		                        	<c:if test="${notice.noticeFileName eq null}">
		                        		-
		                        	</c:if>
		                        </td>
		                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.noticeDate }"/></td>
		                    </tr>	                	
	                	</c:forEach>
	                </tbody>
                </table>
            </div>
            <div>    
                <a id="noticeBtn" href="/notice/insert.kr">공지등록</a>
            </div>
            <div id="pages">
                <ul>
                	<li>
                		<c:if test="${pInfo.startNavi != 1 }">
                			<c:url var="pageUrl" value="/notice/list.kr" >
                				<c:param name="page" value="${pInfo.startNavi -1 }"></c:param>
                			</c:url>
                			<a href="${pageUrl }">이전</a>
                    	</c:if>
                    	<c:forEach begin="${pInfo.startNavi}" end="${pInfo.endNavi }" var="p">
                    		<c:url var="pageUrl" value="/notice/list.kr">
                    			<c:param name="page" value="${p }"></c:param>
                    		</c:url>
                    		<a href="${pageUrl}">${p }</a>
                    	</c:forEach>
                    	<c:if test="${pInfo.endNavi != pInfo.naviTotalCount }">
                			<c:url var="pageUrl" value="/notice/list.kr" >
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