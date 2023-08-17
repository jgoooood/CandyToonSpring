package kr.co.candytoon.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.candytoon.member.domain.Member;
import kr.co.candytoon.member.service.MemberService;

@Controller
@SessionAttributes("memberId")
public class MemberController {

	// 의존성주입 : service를 알고있어야함->필드에 Autowired사용
	@Autowired
	private MemberService service;
	
	// 회원가입창 접속
	@RequestMapping(value ="/member/register.do", method=RequestMethod.GET)
	public String showRegisterForm() {
		return "member/register";
	}
	// 회원가입 입력정보 전송
	@RequestMapping(value="member/register.do", method=RequestMethod.POST)
	public String registerMember(
			@RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, @RequestParam("memberEmail") String memberEmail
			, @RequestParam("memberName") String memberName
			, Model model) {
		try {
			Member member = new Member(memberId, memberPw, memberEmail, memberName);
			int result = service.insertMember(member);
			if(result > 0) {
				model.addAttribute("msg", "회원가입이 완료되었습니다.");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "회원가입이 완료되지 않았습니다.");
				model.addAttribute("url", "/member/register.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "회원가입이 완료되지 않았습니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	@RequestMapping(value="/member/changePw.do", method=RequestMethod.POST)
	public String changePw(
			@RequestParam("memberId") String memberId
			, @RequestParam("newPW") String memberPw
			, Model model) {
		try {
			Member member = new Member(memberId, memberPw);
			int result = service.changePw(member);
			if(result > 0) {
				model.addAttribute("msg", "비밀번호가 변경되었습니다. 로그인해주세요.");
				model.addAttribute("url", "/member/login.do");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "비밀번호가 변경되지 않았습니다.");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "비밀번호가 변경되지 않았습니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	//비밀번호 재설정
	@RequestMapping(value="/member/changePw.do", method=RequestMethod.GET)
	public String showChangePwForm() {
		return "member/changePw";
	}
	// 로그인 창 이동
	@RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
	public String showLoginForm() {
		return "member/login";
	}
	// 로그인 진행
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String memberLogin(
			HttpServletRequest request
			, @RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, Model model
			) {
		try {
			Member member = new Member(memberId, memberPw);
			Member mOne = service.loginCheck(member);
			if(mOne != null) {
				model.addAttribute("memberId", mOne.getMemberId());
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("alertMsg", "일치하는 정보가 없습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "일치하는 정보가 없습니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	// 로그아웃
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout(
			SessionStatus session
			, Model model) {
		if(session != null) {
			//sessionPrev.invalidate();
			session.setComplete();
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("alertMsg", "로그아웃을 완료하지 못했습니다.");
			return "common/serviceFailed";
		}
	}
	
	// 마이페이지 이동
	@RequestMapping(value="/member/myPage.do", method=RequestMethod.GET)
	public String showMyPage(
			@RequestParam("memberId") String memberId
			, Model model
			) {
		try {
			Member member = service.selectOneById(memberId);
			if(member != null) {
				model.addAttribute("member", member);
				return "member/myPage";
			} else {
				model.addAttribute("alertMsg", "회원정보를 불러올 수 없습니다.");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.getStackTrace();
			model.addAttribute("alertMsg", "회원정보를 불러올 수 없습니다.");
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFailed";
		}
	}
	
	//아이디찾기 페이지
	@RequestMapping(value="/member/findId.do",method=RequestMethod.GET)
	public String showFindIdForm() {
		return "member/findId";
	}
	//아이디찾기 결과
	@RequestMapping(value="/member/findId.do", method=RequestMethod.POST)
	public String findId(
			@RequestParam("memberName") String memberName
			, @RequestParam("memberEmail") String memberEmail
			, Model model) {
		try {
			Member member = new Member();
			member.setMemberName(memberName);
			member.setMemberEmail(memberEmail);
			Member mOne = service.findId(member);
			if(mOne != null) {
				model.addAttribute("memberId", mOne.getMemberId());
				return "member/findIdResult";
			} else {
				model.addAttribute("alertMsg", "일치하는 정보가 없습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "일치하는 정보가 없습니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	//비밀번호 재설정 확인페이지 이동
	@RequestMapping(value="/member/confirmPw.do", method=RequestMethod.GET)
	public String showConfirmPwForm() {
		return "member/confirmPw";
	}
	
	//비밀번호 재설정 확인
	@RequestMapping(value="/member/confirmPw.do", method=RequestMethod.POST)
	public String confirmPw(
			@RequestParam("memberId") String memberId
			, @RequestParam("memberEmail") String memberEmail
			, Model model) {
		try {
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberEmail(memberEmail);
			Member mOne = service.confirmPw(member);
			if(mOne != null) {
				model.addAttribute("memberId", mOne.getMemberId());
				return "member/changePw";
			} else {
				model.addAttribute("alertMsg", "일치하는 정보가 없습니다.");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "일치하는 정보가 없습니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
}
