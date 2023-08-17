package kr.co.candytoon.ask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.PageData;
import kr.co.candytoon.ask.service.AskService;

@Controller
public class AskController {
	@Autowired
	private AskService service;
	
	// 문의사항 등록페이지 이동
	@RequestMapping(value="/ask/insert.do", method=RequestMethod.GET)
	public String showAskForm() {
		return "ask/askInsert";
	}
	
	// 문의사항 insert
	@RequestMapping(value="/ask/insert.do", method=RequestMethod.POST)
	public String insertAsk(Ask ask, Model model) {
		try {
			int result = service.insertAsk(ask);
			if(result > 0) {
				model.addAttribute("msg", "1:1문의가 등록되었습니다.");
				model.addAttribute("url", "/ask/askDetail.do?askNo="+ask.getAskNo());
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "1:1문의가 등록되지 않았습니다.");
				model.addAttribute("url", "/ask/insert.do");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	// 문의사항 리스트출력
	@RequestMapping(value="/ask/list.do", method=RequestMethod.GET)
	public String showAskList(
			@RequestParam(value = "currentPage", required = false) String page
			, Model model) {
		try {
			int currentPage = 1;
			if(page != null) {
				currentPage = Integer.parseInt(page);				
			} 
			PageData pData = service.selectAskList(currentPage);	
			if(pData != null) {
				model.addAttribute("aList", pData.getaList());
				model.addAttribute("pageNavi", pData.getPageNavi());
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
	
	// 문의사항 세부내역
	@RequestMapping(value="/ask/detail.do", method=RequestMethod.GET)
	public String showAskDetail(Ask askNo, Model model) {
		try {
			Ask askOne = service.selecAskByNo(askNo);
			if(askOne != null) {
				model.addAttribute("ask", askOne);
				return "ask/askDetail";
			} else {
				model.addAttribute("alertMsg", "문의내역을 불러올 수 없습니다.");
				model.addAttribute("url", "/ask/list.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		} 
	}
	
	// 문의사항 수정페이지 이동
	@RequestMapping(value="/ask/modify.do", method=RequestMethod.GET)
	public String showModifyForm(Ask askNo, Model model) {
		try {
			Ask askOne = service.selecAskByNo(askNo);
			if(askOne != null) {
				model.addAttribute("ask", askOne);
				return "ask/askModify";
			} else {
				model.addAttribute("alertMsg", "문의내역을 불러올 수 없습니다.");
				model.addAttribute("url", "/ask/list.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		} 
	}
	
	// 문의사항 수정
	@RequestMapping(value="/ask/modify.do", method=RequestMethod.POST)
	public String updateAsk(Ask ask, Model model) {
		try {
			int result = service.updateAsk(ask);
			if(result > 0) {
				model.addAttribute("msg", "1:1문의가 수정되었습니다.");
				model.addAttribute("url", "/ask/detail.do?askNo="+ask.getAskNo());
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "문의내역을 불러올 수 없습니다.");
				model.addAttribute("url", "/ask/modify.do");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	// 문의사항 삭제
	@RequestMapping(value="/ask/delete.do", method=RequestMethod.GET)
	public String deleteAsk(Ask askNo, Model model) {
		try {
			int result = service.deleteAsk(askNo);
			if(result > 0) {
				model.addAttribute("msg", "1:1문의가 삭제되었습니다.");
				model.addAttribute("url", "/ask/list.do");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "문의내역을 삭제하지 못했습니다.");
				model.addAttribute("url", "/ask/modify.do?askNo="+askNo);
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스 실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
}
