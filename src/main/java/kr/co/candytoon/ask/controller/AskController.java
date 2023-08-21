package kr.co.candytoon.ask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;
import kr.co.candytoon.ask.service.AskService;

@Controller
public class AskController {
	@Autowired
	private AskService service;
	
	// 문의사항 등록페이지 이동
	@RequestMapping(value="/ask/insert.kr", method=RequestMethod.GET)
	public String showAskForm() {
		return "ask/askInsert";
	}
	
	// 문의사항 insert
	@RequestMapping(value="/ask/insert.kr", method=RequestMethod.POST)
	public String insertAsk(Ask ask, Model model) {
		try {
			int result = service.insertAsk(ask);
			if(result > 0) {
				model.addAttribute("msg", "1:1문의가 등록되었습니다.");
				model.addAttribute("url", "/ask/list.kr");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "1:1문의가 등록되지 않았습니다.");
				model.addAttribute("url", "/ask/insert.kr");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	// 문의사항 수정
	@RequestMapping(value="/ask/modify.kr", method=RequestMethod.POST)
	public String updateAsk(Ask ask, Model model) {
		try {
			int result = service.updateAsk(ask);
			if(result > 0) {
				model.addAttribute("msg", "1:1문의가 수정되었습니다.");
				model.addAttribute("url", "/ask/detail.kr?askNo="+ask.getAskNo());
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "문의내역을 불러올 수 없습니다.");
				model.addAttribute("url", "/ask/modify.kr");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}

	// 문의사항 삭제
	@RequestMapping(value="/ask/delete.kr", method=RequestMethod.GET)
	public String deleteAsk(Ask askNo, Model model) {
		try {
			int result = service.deleteAsk(askNo);
			if(result > 0) {
				model.addAttribute("msg", "1:1문의가 삭제되었습니다.");
				model.addAttribute("url", "/ask/list.kr");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "문의내역을 삭제하지 못했습니다.");
				model.addAttribute("url", "/ask/modify.kr?askNo="+askNo);
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}

	// 문의사항 리스트출력
	@RequestMapping(value="/ask/list.kr", method=RequestMethod.GET)
	public String showAskList(
			/*
			 * @RequestParam(value="page", required=false, defaultValue="1") Integer currentpage
			 * 넘어오는 page값이 0이 아니면 page에 넣고, 아니면 1을 넣음
			 * int currentPage = page != 0 ? page : 1; 대신 사용함
			 * page값이 없으면 defaultValue값이 1로 세팅되고 Integer는 null체크를 위해서 사용
			 */
			@RequestParam(value = "page", required = false, defaultValue="1") Integer currentPage
			, Model model) {
		try {
			//전체 행 구하는 메소드 실행 getListCount();
			int totalCount = service.getListCount();
			//getPageInfo 메소드 실행 : currentPage, totalCount 값 전달 후 페이징객체로 리턴받음
			AskPageInfo pInfo = getPageInfo(currentPage, totalCount);
			//service에 리턴받은 페이징객체를 전달->리스트 불러오기
			List<Ask> aList = service.selectAskList(pInfo);
			if(aList.size() > 0) {
				// 객체를 JSP에 심어서 출력하도록 함
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("aList", aList);
				return "ask/askList";
			} else {
				model.addAttribute("alertMsg", "문의사항 리스트를 불러올 수 없습니다.");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		} 
	}
	
	//페이지 네비게이터 생성메소드 : 기존 DAO에서 컨트롤러로 이동함
	public AskPageInfo getPageInfo(int currentPage, int totalCount) {
		//네비게이터 정보를 담을 PageInfo 객체 생성(기존 PageData Vo클래스)
		AskPageInfo pInfo = null;
		//네비게이터 생성에 필요한 변수 선언
		//고정값 설정
		int recordCountPerPage = 5; //페이지당 보여줄 게시물 수
		int naviCountPerPage = 5; //페이지당 보여줄 네비게이터 수
		//계산식
		int naviTotalCount;
		int startNavi;
		int endNavi;
		naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		startNavi = (((int)((double)currentPage / naviCountPerPage + 0.9))-1) * naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		//PageInfo에 변수값 전달 후 리턴
		pInfo = new AskPageInfo(currentPage, totalCount, recordCountPerPage, naviCountPerPage, naviTotalCount, startNavi, endNavi);
		return pInfo;
	}
	
	
	// 문의사항 세부내역
	@RequestMapping(value="/ask/detail.kr", method=RequestMethod.GET)
	public String showAskDetail(Ask askNo, Model model) {
		try {
			Ask askOne = service.selecAskByNo(askNo);
			if(askOne != null) {
				model.addAttribute("ask", askOne);
				return "ask/askDetail";
			} else {
				model.addAttribute("alertMsg", "문의내역을 불러올 수 없습니다.");
				model.addAttribute("url", "/ask/list.kr");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		} 
	}
	
	// 문의사항 수정페이지 이동
	@RequestMapping(value="/ask/modify.kr", method=RequestMethod.GET)
	public String showModifyForm(Ask askNo, Model model) {
		try {
			Ask askOne = service.selecAskByNo(askNo);
			if(askOne != null) {
				model.addAttribute("ask", askOne);
				return "ask/askModify";
			} else {
				model.addAttribute("alertMsg", "문의내역을 불러올 수 없습니다.");
				model.addAttribute("url", "/ask/list.kr");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		} 
	}
}
