<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="../resources/CSS/include/myPageMenu.css">
 <section id="leftSection">
     <div id="userBox">
         <div><img src="../resources/images/icons/book.png" alt="서재"></div>
         <h1>보관함</h1>
         <ul>
             <li><a href="#">소장작품</a></li>
             <li><a href="#">최근 조회한 작품</a></li>
             <li><a href="#">관심 작품</a></li>
         </ul>
     </div>
     <div id="userPay">
         <div><img src="../resources/images/icons/credit-card.png" alt="결제"></div>
         <h1>결제</h1>
         <ul>
             <li><a href="#">결제내역</a></li><br>
             <li><a href="#">캔디충전</a></li><br>
             <li><a href="#">충전내역</a></li>
         </ul>
     </div>
     <div id="userInfo">
         <div><img src="../resources/images/icons/user.png" alt="개인"></div>
         <h1>개인</h1>
         <ul>
             <li><a href="#">댓글관리</a></li><br>
             <li>
             	<a href="/member/myinfo.kr">회원정보</a>
             </li>
         </ul>
     </div>
 </section>