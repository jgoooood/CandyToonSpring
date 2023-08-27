package kr.co.candytoon.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.co.candytoon.member.domain.Member;
import kr.co.candytoon.member.service.MemberService;

@Controller
@SessionAttributes("memberId")
public class MemberController {

	// 의존성주입 : service를 알고있어야함->필드에 Autowired사용
	@Autowired
	private MemberService service;
	
	// 회원가입창 접속
	@RequestMapping(value ="/member/register.kr", method=RequestMethod.GET)
	public String showRegisterForm() {
		return "member/register";
	}
	// 회원가입 입력정보 전송
	@RequestMapping(value="member/register.kr", method=RequestMethod.POST)
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
				model.addAttribute("url", "/member/register.kr");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "회원가입이 완료되지 않았습니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	//비밀번호 재설정
	@RequestMapping(value="/member/changePw.kr", method=RequestMethod.GET)
	public String showChangePwForm() {
		return "member/changePw";
	}
	
	//비밀번호재설정진행
	@RequestMapping(value="/member/changePw.kr", method=RequestMethod.POST)
	public String changePw(
			@RequestParam("memberId") String memberId
			, @RequestParam("newPW") String memberPw
			, SessionStatus session
			, Model model) {
		try {
			Member member = new Member(memberId, memberPw);
			int result = service.changePw(member);
			if(result > 0) {
				model.addAttribute("msg", "비밀번호가 변경되었습니다. 로그인해주세요.");
				this.memberLogout(session, model);
				model.addAttribute("url", "/member/login.kr");
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
	
	//이메일주소 변경페이지 이동
	@RequestMapping(value="/member/changeEmail.kr", method=RequestMethod.GET)
	public ModelAndView showchangeEmailForm(
			ModelAndView mv
			, HttpSession session) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			if(memberId != "" && memberId != null) {
				Member member = service.selectOneById(memberId);
				if(member != null) {
					mv.addObject("member", member);
					mv.setViewName("member/changeEmail");
				} else {
					mv.addObject("alertMsg", "회원정보를 불러올 수 없습니다.");
					mv.addObject("url", "/index.jsp");
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("alertMsg", "로그인 후 이용해 주시기 바랍니다.");
				mv.addObject("url", "/member/login.kr");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			e.getStackTrace();
			mv.addObject("alertMsg", "[서비스실패] 관리자에게 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/serviceFailed");
		}
			
		return mv;
	}
	
	//이메일주소 변경진행
	@RequestMapping(value="/member/changeEmail.kr", method=RequestMethod.POST)
	public ModelAndView changeEmail(
			ModelAndView mv
			, @RequestParam(value="memberEmail") String memberEmail
			, HttpSession session) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			if(memberId != "" && memberId != null) {
				Member member = new Member();
				member.setMemberId(memberId);
				member.setMemberEmail(memberEmail);
				int result = service.changeEmail(member);
				if(result > 0) {
					mv.addObject("msg", "이메일주소가 변경되었습니다.");
					mv.addObject("url", "/member/myinfo.kr");
					mv.setViewName("common/serviceSuccess");
				} else {
					mv.addObject("alertMsg", "이메일주소가 변경되지 않았습니다.");
					mv.addObject("url", "/member/changeEmail.kr");
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("alertMsg", "로그인 후 이용해 주시기 바랍니다.");
				mv.addObject("url", "member/login.kr");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			e.getStackTrace();
			mv.addObject("alertMsg", "[서비스실패] 관리자에게 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	//회원탈퇴
	@RequestMapping(value="/member/delete.kr", method=RequestMethod.GET) 
	public ModelAndView deleteMember(
			ModelAndView mv
			, HttpSession session
			, SessionStatus sessionStatus) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			if(memberId != "" & memberId != null) {
				int result = service.deleteMember(memberId);
				if(result > 0) {
					sessionStatus.setComplete();
					mv.addObject("msg", "회원탈퇴가 완료되었습니다.");
					mv.addObject("url", "/member/deleteResult.kr");
					mv.setViewName("common/serviceSuccess");
				} else {
					mv.addObject("alertMsg", "회원탈퇴가 완료되지 않았습니다.");
					mv.addObject("url", "member/myinfo.kr");
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("alertMsg", "로그인 후 이용해 주시기 바랍니다.");
				mv.addObject("url", "/member/login.kr");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			e.getStackTrace();
			mv.addObject("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	//회원탈퇴 완료 페이지 이동
	@RequestMapping(value="/member/deleteResult.kr", method=RequestMethod.GET)
	public String showDeleteResult() {
		return "member/deleteResult";
	}
	
	// 로그인 창 이동
	@RequestMapping(value = "/member/login.kr", method = RequestMethod.GET)
	public String showLoginForm() {
		return "member/login";
	}
	
	// 로그인 진행 기존방식
	@RequestMapping(value="/member/login.kr", method=RequestMethod.POST)
	public String memberLogin(
			HttpServletRequest request
			, @RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, HttpSession session
			, Model model
			) {
		try {
			Member member = new Member(memberId, memberPw);
			Member mOne = service.loginCheck(member);
			if(mOne != null) {
				session.setAttribute("memberId", member.getMemberId());
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("alertMsg", "일치하는 정보가 없습니다.");
				model.addAttribute("url", "/member/login.kr");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	// 로그아웃
	@RequestMapping(value="/member/logout.kr", method=RequestMethod.GET)
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
	
	/*마이페이지 이동 : 기존 방식은 JSP에서 memberId를 가지고 옴
	 ->쿼리스트링 조작으로 로그이없이도 페이지 조회 가능 -> 세션에서 가지고 오는 것으로 수정 
	@RequestMapping(value="/member/myPage.kr", method=RequestMethod.GET)
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
	} */
	
	//session에서 id가지고 오는 것으로 수정
	@RequestMapping(value="/member/myPage.kr", method= {RequestMethod.GET, RequestMethod.POST})
	public String showMyPage(
			HttpSession session
			, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			Member member = null;
			//memberId가 session에 남아있으면 service호출
			if(memberId != "" && memberId != null) {
				member = service.selectOneById(memberId);				
				//가지고온 member정보가 null이 아니면 mypage이동
				if(member != null) {
					model.addAttribute("member", member);
					return "member/myPage";
				} else {
					model.addAttribute("alertMsg", "회원정보를 불러올 수 없습니다.");
					model.addAttribute("url", "/index.jsp");
					return "common/serviceFailed";
				}
			} else {
				model.addAttribute("alertMsg", "로그인 후 이용해 주시기 바랍니다.");
				model.addAttribute("url", "/member/login.kr");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.getStackTrace();
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFailed";
		}
	}
	
	//마이인포 페이지 : 회원정보조회, 정보수정페이지
	@RequestMapping(value="/member/myinfo.kr",method=RequestMethod.GET)
	public ModelAndView showMyinfoForm(
			ModelAndView mv
			, HttpSession session) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			Member member = null;
			if(memberId != "" && memberId != null) {
				member = service.selectOneById(memberId);			
				if(member != null) {
					mv.addObject("member", member);
					mv.setViewName("member/myInfo");
				} else {
					mv.addObject("alertMsg", "회원정보를 불러올 수 없습니다.");
					mv.addObject("url", "/index.jsp");
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("alertMsg", "로그인 후 이용해 주시기 바랍니다.");
				mv.addObject("url", "/member/login.kr");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			e.getStackTrace();
			mv.addObject("alertMsg", "[서비스실패] 관리자에게 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	//아이디찾기 페이지
	@RequestMapping(value="/member/findId.kr",method=RequestMethod.GET)
	public String showFindIdForm() {
		return "member/findId";
	}
	
	//아이디찾기 결과
	@RequestMapping(value="/member/findId.kr", method=RequestMethod.POST)
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
				model.addAttribute("url", "/member/findId.kr");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	//비밀번호 재설정 확인페이지 이동
	@RequestMapping(value="/member/confirmPw.kr", method=RequestMethod.GET)
	public String showConfirmPwForm() {
		return "member/confirmPw";
	}
	
	//비밀번호 재설정 확인
	@RequestMapping(value="/member/confirmPw.kr", method=RequestMethod.POST)
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
				model.addAttribute("url", "/member/confirmPw.kr");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
}
