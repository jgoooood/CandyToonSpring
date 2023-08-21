package kr.co.candytoon.fnq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.candytoon.fnq.domain.Fnq;
import kr.co.candytoon.fnq.domain.FnqPageInfo;
import kr.co.candytoon.fnq.service.FnqService;

@Controller
public class FnqController {
	
	@Autowired
	private FnqService service;
	
	@RequestMapping(value="/fnq/insert.kr", method=RequestMethod.POST)
	public String insertFnq(Fnq fnq, Model model) {
		int result = service.insertFnq(fnq);
		try {
			if(result > 0) {
				model.addAttribute("msg", "FnQ등록이 완료되었습니다.");
				model.addAttribute("url", "fnq/fnqList.kr");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "FnQ등록이 완료되지 않았습니다.");
				model.addAttribute("url", "/fnq/insert.kr");
				return "common/serviceFailed";
			}		
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}

	@RequestMapping(value="/fnq/modify.kr", method=RequestMethod.POST)
	public String modifyFnq(Fnq fnq, Model model) {
		try {
			int result = service.modifyFnq(fnq);
			if(result > 0) {
				model.addAttribute("fnq", fnq);
				model.addAttribute("msg", "수정이 완료되었습니다.");
				model.addAttribute("url", "/fnq/detail.kr?fnqNo="+fnq.getFnqNo());
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "수정이 완료되지 않았습니다.");
				model.addAttribute("url", "/fnq/list.kr");
				return "common/serviceFailed";
			}		
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}

	@RequestMapping(value="/fnq/delete.kr", method=RequestMethod.GET)
	public String deleteFnq(Fnq fnqNo, Model model) {
		try {
			int result = service.deleteFnq(fnqNo);
			if (result > 0) {
				model.addAttribute("msg", "삭제가 완료되었습니다.");
				model.addAttribute("url", "/fnq/list.kr");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "삭제가 완료되지 않았습니다.");
				model.addAttribute("url", "/fnq/list.kr");
				model.addAttribute("msg", "삭제가 완료되지 않았습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
		
	}

	@RequestMapping(value="/fnq/list.kr", method=RequestMethod.GET)
	public String showFnqList(
			@RequestParam(value = "page", required=false, defaultValue="1") int currentPage
			, Model model) {
		try {
			int totalCount = service.getTotalCount();
			FnqPageInfo pInfo = getPageInfo(currentPage, totalCount);
			List<Fnq> fList = service.selectFnqList(pInfo);
			if(fList.size() > 0) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("fList", fList);
				return "fnq/fnqList";
			} else {
				model.addAttribute("alertMsg", "FnQ 내역을 불러올 수 없습니다.");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	private FnqPageInfo getPageInfo(int currentPage, int totalCount) {
		//고정값
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		//계산식
		int naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		int startNavi = (((int)((double)currentPage / naviCountPerPage + 0.9))-1) * naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage -1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		FnqPageInfo pInfo = new FnqPageInfo(currentPage, totalCount, recordCountPerPage, naviCountPerPage, naviTotalCount, startNavi, endNavi);
		return pInfo;
	}

	@RequestMapping(value="/fnq/insert.kr", method=RequestMethod.GET)
	public String showFnqInsertForm() {
		return "fnq/fnqInsert";
	}
	
	@RequestMapping(value="/fnq/detail.kr", method=RequestMethod.GET)
	public String showDetailForm(Fnq fnqNo, Model model) {
		try {
			Fnq fnq = service.selectFnqByNo(fnqNo);
			if(fnq != null) {
				model.addAttribute("fnq", fnq);
				return "fnq/fnqDetail";
			} else {
				model.addAttribute("alertMsg", "상세 내용을 불러올 수 없습니다.");
				model.addAttribute("url", "/fnq/list.kh");
				return "common/serviceFailed";
			}		
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	@RequestMapping(value="/fnq/modify.kr", method=RequestMethod.GET)
	public String showModifyForm(Fnq fnqNo, Model model) {
		try {
			Fnq fnq = service.selectFnqByNo(fnqNo);
			if(fnq != null) {
				model.addAttribute("fnq", fnq);
				return "fnq/fnqModify";
			} else {
				model.addAttribute("alertMsg", "상세 내용을 불러올 수 없습니다.");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}		
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
}
