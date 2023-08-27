<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>이메일 변경</title>
        <link rel="stylesheet" href="../resources/CSS/reset.css">
        <link rel="stylesheet" href="../resources/CSS/member/confirmPw.css">
    </head>
    <body>
        <script>
            
//             이메일전송버튼
            function sendEmail(event) {
                const inputId = document.querySelector("#inputId").value;
                const inputEmail = document.querySelector("#inputEmail").value;
                const checkId = document.querySelector("#checkId").value;
                const checkEmail = document.querySelector("#checkEmail").value;

                const idRegExp = /^[a-z0-9]{5,10}$/g;
                const emailRegExp = /^[a-zA-Z0-9]{4,20}@[a-z]+\.[a-z]{3}/g;

                
                if(!idRegExp.test(inputId)) {
                    alert("아이디는 영문, 숫자 조합 5~10자 입력해주세요.")
                    event.preventDefault();
                } else if (inputId !== checkId) {
                	alert("로그인한 아이디와 일치하지 않습니다.")
                    event.preventDefault();
                } else if (!emailRegExp.test(inputEmail)) {
                    alert("올바른 이메일형식이 아닙니다.")
                    event.preventDefault();
                } else if (inputEmail === checkEmail) {
                	alert("이미 등록되어 있는 이메일 주소입니다.")
                    event.preventDefault();
                } else {
                    alert("인증키가 발송되었습니다.")
                    event.preventDefault();
                }
            }
          //form태그 생성 후 submit
			function changeEmail() {
				const form = document.createElement("form");
				form.action = "/member/changeEmail.kr";
				form.method = "post";
				//생성되어있는 input태그를 가져오기
				const inputId = document.querySelector("#inputId");
				const inputEmail = document.querySelector("#inputEmail");
				//input태그를 form태그와 연결    
				form.appendChild(inputId);
				form.appendChild(inputEmail);
				//form태그를 body태그에 연결
				document.body.appendChild(form);
				form.submit();
			}
        </script>
        
        <div id="confirm">
            <div id="confirmPw">
                <h1>이메일 변경</h1>
            </div>
			<input type="hidden" id="checkId" value=${member.memberId }>
			<input type="hidden" id="checkEmail" value=${member.memberEmail }>
            <div id="inputInfo">
                <div id="userId">
                    <input type="text" id="inputId" name="memberId" placeholder="아이디"><img src="../resources/images/icons/login.png" alt="로그인">
                </div>
                <div id="email">
                    <input type="email" id="inputEmail" name="memberEmail" placeholder="변경할 이메일 주소"><img src="../resources/images/icons/email.png" alt="이메일">
                </div>
                <div>
                    <button onclick="sendEmail();">인증요청</button>
                </div>
                <br><hr>
                <div id="emailKey">
                    <input type="text" id="key" placeholder="이메일로 발송된 인증키"><img src="../resources/images/icons/key.png" alt="인증키">
                </div>
            </div>
            <div id="findBtn">
                <button type="button" onclick="changeEmail();">변경</button>
            </div>
        </div>  
        

    </body>
</html>