<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
	<!-- head -->
	<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
	<link href="/resources/CSS/index.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<title>CANDY TOON</title>
	<style>
		* {
			box-sizing: border-box;
		}
	</style>
    <body>
		<!-- 헤더, 네비 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
        
        <!-- 이벤트 배너 -->
        <div id="event">
			<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
			  <div class="carousel-indicators">
			    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
			    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
			    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
			  </div>
			  <div class="carousel-inner">
			    <div class="carousel-item active" data-bs-interval="4000">
			      <img src="/resources/images/event/event1.jpg" class="d-block" alt="event1">
			    </div>
			    <div class="carousel-item" data-bs-interval="4000">
			      <img src="/resources/images/event/event2.jpg" class="d-block" alt="event2">
			    </div>
			    <div class="carousel-item" data-bs-interval="4000">
			      <img src="/resources/images/event/event3.jpg" class="d-block" alt="event3">
			    </div>
			    <div class="carousel-item" data-bs-interval="4000">
			      <img src="/resources/images/event/event4.jpg" class="d-block" alt="event4">
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>        
        </div>
        <div class="main_container">

            <!-- 메인 -->
            <main>
                <!-- 전체 웹툰 top5 -->
                <section>
                    <div class="top5-layer1">
                        <div class="top5-left"><p>전체 웹툰 TOP5</p></div>
                        <div class="top5-right"><a href="./ranking/total.jsp">더보기</a></div>
                    </div>
                    <div class="top5-layer2">
                     	<c:forEach items="${topViewList }" var="top5" begin="0" end="4" varStatus="i">
                       		<div class="top5-toon">
	                            <div class="top5-Cover">
	                                <a href="/webtoon.jsp">
	                                <img src="../resources/uploadFiles/${top5.wCoverRename }" alt="${top5.webtoonTitle }"></a>
	                            </div>
	                            <div class="top5-info">
	                                <div class="top5-info-rank"><p>${i.count }</p></div>
		                            <div class="top5-info-title"><a href="/webtoon.jsp">${top5.webtoonTitle }</a></div>
	                                <c:if test="${top5.webtoonWriter ne top5.webtoonPainter }">
		                                <div class="top5-info-writer"><a href="#">${top5.webtoonWriter }</a> / <a href="#">${top5.webtoonPainter }</a></div>
	                                </c:if>
	                                <c:if test="${top5.webtoonWriter eq top5.webtoonPainter }">
		                                <div class="top5-info-writer"><a href="#">${top5.webtoonWriter }</a></div>
	                                </c:if>
	                            </div>
                       		</div>
                      	</c:forEach>	
                    </div>
                </section>

                <!-- 신작 -->
                <section>
                    <div class="top5-layer1">
                        <div class="top5-left"><p>신작 웹툰 TOP5</p></div>
                        <div class="top5-right"><p><a href="./ranking/new.jsp">더보기</a></p></div>
                    </div>
                    <div class="top5-layer2">
	                    <c:forEach items="${topNewList }" var="new5" begin="0" end="4" varStatus="i">
							<div class="top5-toon">
							    <div class="top5-Cover">
							        <a href="/webtoon.jsp">
							        <img src="../resources/uploadFiles/${new5.wCoverRename }" alt="${new5.webtoonTitle }"></a>
							    </div>
							    <div class="top5-info">
							        <div class="top5-info-rank"><p>${i.count }</p></div>
							     <div class="top5-info-title"><a href="/webtoon.jsp">${new5.webtoonTitle }</a></div>
							        <c:if test="${new5.webtoonWriter ne new5.webtoonPainter }">
							         <div class="top5-info-writer"><a href="#">${new5.webtoonWriter }</a> / <a href="#">${new5.webtoonPainter }</a></div>
							        </c:if>
							        <c:if test="${new5.webtoonWriter eq new5.webtoonPainter }">
							         <div class="top5-info-writer"><a href="#">${new5.webtoonWriter }</a></div>
							        </c:if>
							    </div>
							</div>
						</c:forEach>
                    </div>
                </section>

                <!-- 완결 웹툰 top5 -->
                <section>
                    <div class="top5-layer1">
                        <div class="top5-left"><p>완결 웹툰 TOP5</p></div>
                        <div class="top5-right"><p><a href="/WEB-INF/views/ranking/compleated.jsp">더보기</a></p></div>
                    </div>
                    <div class="top5-layer2">
                        <c:forEach items="${topEndList }" var="end5" begin="0" end="4" varStatus="i">
							<div class="top5-toon">
							    <div class="top5-Cover">
							        <a href="/webtoon.jsp">
							        <img src="../resources/uploadFiles/${end5.wCoverRename }" alt="${end5.webtoonTitle }"></a>
							    </div>
							    <div class="top5-info">
							        <div class="top5-info-rank"><p>${i.count }</p></div>
							     <div class="top5-info-title"><a href="/webtoon.jsp">${end5.webtoonTitle }</a></div>
							        <c:if test="${end5.webtoonWriter ne end5.webtoonPainter }">
							         <div class="top5-info-writer"><a href="#">${end5.webtoonWriter }</a> / <a href="#">${end5.webtoonPainter }</a></div>
							        </c:if>
							        <c:if test="${end5.webtoonWriter eq end5.webtoonPainter }">
							         <div class="top5-info-writer"><a href="#">${end5.webtoonWriter }</a></div>
							        </c:if>
							    </div>
							</div>
						</c:forEach>
                    </div>
                </section>
            </main>
        </div>
        
		<!-- 푸터 -->
    	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
		
    </body>
</html>