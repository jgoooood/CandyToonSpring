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
                        <div id="total-top1" class="top5-toon">
                        	<c:forEach items="${wList }" var="webtoon">
	                            <div class="top5-Cover">
	                                <a href="/webtoon.jsp">
	                                <img src="${webtoon.webtoonCover }" alt="${webtoon.webtoonTitle }"></a>
	                            </div>
	                            <div class="top5-info">
	                                <div class="top5-info-rank"><p>1</p></div>
	                                <div class="top5-info-title"><a href="/webtoon.jsp">${webtoon.webtoonTitle }</a></div>
	                                <div class="top5-info-writer"><a href="#">${webtoon.webtoonWriter }</a> / <a href="#">${webtoon.webtoonPainter }</a></div>
	                            </div>
                        	</c:forEach>
                        </div>
                        <div id="total-top2" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/total-top5/totalTop2.png" alt="호랑이형님">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>2</p></div>
                                <div class="top5-info-title"><a href="#">호랑이형님</a></div>
                                <div class="top5-info-writer"><a href="#">이상규</a></div>
                            </div>
                        </div>
                        <div id="total-top3" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/total-top5/totalTop3.png" alt="김부장">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>3</p></div>
                                <div class="top5-info-title"><a href="#">김부장</a></div>
                                <div class="top5-info-writer"><a href="#">토이 / 정종택</a></div>
                            </div>
                        </div>
                        <div id="total-top4" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/total-top5/totalTop4.png" alt="마루는강쥐">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>4</p></div>
                                <div class="top5-info-title"><a href="#">마루는 강쥐</a></div>
                                <div class="top5-info-writer"><a href="#">모죠</a></div>
                            </div>
                        </div>
                        <div id="total-top5" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/total-top5/totalTop5.png" alt="나노마신">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>5</p></div>
                                <div class="top5-info-title"><a href="#">나노마신</a></div>
                                <div class="top5-info-writer"><a href="#">한중월야</a></div>
                            </div>
                        </div>
                    </div>
                </section>

                <!-- 신작 -->
                <section>
                    <div class="top5-layer1">
                        <div class="top5-left"><p>신작 웹툰 TOP5</p></div>
                        <div class="top5-right"><p><a href="./ranking/new.jsp">더보기</a></p></div>
                    </div>
                    <div class="top5-layer2">
                        <div id="new-top1" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/new-top5/newTop1.png" alt="사주헌터">
                                
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>1</p></div>
                                <div class="top5-info-title"><a href="#">F급 사주헌터</a></div>
                                <div class="top5-info-writer"><a href="#">우리/아나민</a></div>
                            </div>
                        </div>
                        <div id="new-top2" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/new-top5/newTop2.png" alt="시간여행자">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>2</p></div>
                                <div class="top5-info-title"><a href="#">시간여행자</a></div>
                                <div class="top5-info-writer"><a href="#">Toma/팬더롤링</a></div>
                            </div>
                        </div>
                        <div id="new-top3" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/new-top5/newTop3.png" alt="고기자">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>3</p></div>
                                <div class="top5-info-title"><a href="#">기자생활</a></div>
                                <div class="top5-info-writer"><a href="#">고기자</a></div>
                            </div>
                        </div>
                        <div id="new-top4" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/new-top5/newTop4.png" alt="궁궐의맹수">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>4</p></div>
                                <div class="top5-info-title"><a href="#">궁궐의 맹수</a></div>
                                <div class="top5-info-writer"><a href="#">정오늘/한연</a></div>
                            </div>
                        </div>
                        <div id="new-top5" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/new-top5/newTop5.png" alt="검의구도자">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>5</p></div>
                                <div class="top5-info-title"><a href="#">검의 구도자</a></div>
                                <div class="top5-info-writer"><a href="#">윤소함/이형석</a></div>
                            </div>
                        </div>
                    </div>
                </section>

                <!-- 완결 웹툰 top5 -->
                <section>
                    <div class="top5-layer1">
                        <div class="top5-left"><p>완결 웹툰 TOP5</p></div>
                        <div class="top5-right"><p><a href="/WEB-INF/views/ranking/compleated.jsp">더보기</a></p></div>
                    </div>
                    <div class="top5-layer2">
                        <div id="compleated-top1" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/compleated-top5/compleatedTop1.png" alt="화산귀환">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>1</p></div>
                                <div class="top5-info-title"><a href="#">화산귀환</a></div>
                                <div class="top5-info-writer"><a href="#">LICO/비가</a></div>
                            </div>
                        </div>
                        <div id="compleated-top2" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/compleated-top5/compleatedTop2.png" alt="캐슬">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>2</p></div>
                                <div class="top5-info-title"><a href="#">캐슬</a></div>
                                <div class="top5-info-writer"><a href="#">정연</a></div>
                            </div>
                        </div>
                        <div id="compleated-top3" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/compleated-top5/compleatedTop3.png" alt="별이삼샵">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>3</p></div>
                                <div class="top5-info-title"><a href="#">별이삼샵</a></div>
                                <div class="top5-info-writer"><a href="#">혀노</a></div>
                            </div>
                        </div>
                        <div id="compleated-top4" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/compleated-top5/compleatedTop4.png" alt="치즈인더트랩">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>4</p></div>
                                <div class="top5-info-title"><a href="#">치즈인더트랩</a></div>
                                <div class="top5-info-writer"><a href="#">순끼</a></div>
                            </div>
                        </div>
                        <div id="compleated-top5" class="top5-toon">
                            <div class="top5-Cover">
                                <img src="/resources/images/cover/compleated-top5/compleatedTop5.png" alt="태시트">
                            </div>
                            <div class="top5-info">
                                <div class="top5-info-rank"><p>5</p></div>
                                <div class="top5-info-title"><a href="#">태시트</a></div>
                                <div class="top5-info-writer"><a href="#">김다찌</a></div>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
        </div>
        
		<!-- 푸터 -->
    	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
		
    </body>
</html>