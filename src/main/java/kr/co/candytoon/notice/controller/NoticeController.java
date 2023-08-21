package kr.co.candytoon.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.candytoon.notice.domain.Notice;
import kr.co.candytoon.notice.domain.NoticePageInfo;
import kr.co.candytoon.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/notice/insert.kr", method=RequestMethod.POST)
	public String insertNotice(Notice notice, Model model) {
		int result = service.insertNotice(notice);
		try {
			if(result > 0) {
				model.addAttribute("msg", "공지사항 등록이 완료되었습니다.");
				model.addAttribute("url", "/notice/list.kr");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "공지사항 등록이 완료되지 않았습니다.");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}


	@RequestMapping(value="/notice/modify.kr", method = RequestMethod.POST)
	public String modifyNotice(Notice notice, Model model) {
		try {
			int result = service.modifyNotice(notice);
			if(result > 0) {
				model.addAttribute("notice", notice);
				model.addAttribute("msg", "수정이 완료되었습니다.");
				model.addAttribute("url", "/notice/detail.kr?noticeNo="+notice.getNoticeNo());
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "수정이 완료되지 않았습니다.");
				return "common/serviceFailed";
			}
			
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}


	@RequestMapping(value="/notice/delete.kr", method=RequestMethod.GET)
	public String deleteNotice(Notice noticeNo, Model model) {
		try {
			int result = service.deleteNotice(noticeNo);
			if(result > 0) {
				model.addAttribute("msg", "삭제가 완료되었습니다.");
				model.addAttribute("url", "/notice/list.kr");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("alertMsg", "삭제가 완료되지 않았습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}


	/*페이징처리
	* 1. page값 jsp에서 넘겨받기->값이 있으면 currentPage에 저장, 없을 경우 디폴트 1세팅 후 저장  
	* 2. 전체 행 구하는 메소드 추가 : getListCount();
	* 3. 페이징에 필요한 domain객체 생성
	* 	-> 필요 변수7개 : currentPage, totalCount, recordCountPerPage, naviCountPerPage, naviTotalCount, startNavi, endNavi
	* 4. 네비게이터 생성메소드(getPageInfo()) 만들기 : 현재페이지값, 전체행개수 전달받아서 나머지 변수 세팅 후 객체로 넘김
	* 5. 리턴받은 pInfo를 jsp에 세팅
	*/ 
	@RequestMapping(value="/notice/list.kr", method=RequestMethod.GET)
	public String showNoticeList(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
			, Model model) {
		try {
			int totalCount = service.getListCount();
			NoticePageInfo pInfo = getPageInfo(currentPage, totalCount);
			List<Notice> nList = service.selectNoticeList(pInfo);
			if(nList.size() > 0) {
				model.addAttribute("nList", nList);
				model.addAttribute("pInfo", pInfo);
				return "notice/noticeList";			
			} else {
				model.addAttribute("alertMsg", "공지 목록을 불러올 수 없습니다.");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}	
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}


	/* 네비게이터 계산 메소드
	 * 1. 고정값 설정 : recordCountPerPage, naviCountPerPage
	 * 2. 계산식 : naviTotalCount, startNavi, endNavi 
	 */
	private NoticePageInfo getPageInfo(Integer currentPage, int totalCount) {
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		int naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		int startNavi= (((int)((double)currentPage / naviCountPerPage + 0.9)) -1) * naviCountPerPage +1 ;
		int endNavi = startNavi + naviCountPerPage -1 ;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		NoticePageInfo pInfo = new NoticePageInfo(currentPage, totalCount, recordCountPerPage, naviCountPerPage, naviTotalCount, startNavi, endNavi);
		return pInfo;
	}


	@RequestMapping(value="/notice/insert.kr", method=RequestMethod.GET)
	public String showNoticeInsertForm() {
		return "notice/noticeInsert";
	}
	
	@RequestMapping(value="/notice/detail.kr", method = RequestMethod.GET)
	public String showNoticeDetail(@RequestParam("noticeNo") int noticeNo, Model model) {
		try {
			Notice notice = service.selectNoticeByNo(noticeNo);
			if(notice != null) {
				model.addAttribute("notice", notice);
				return "notice/noticeDetail";
			} else {
				model.addAttribute("alertMsg", "공지사항을 불러올 수 없습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	@RequestMapping(value="/notice/modify.kr", method = RequestMethod.GET)
	public String showModifyForm(@RequestParam("noticeNo") int noticeNo, Model model) {
		try {
			Notice notice = service.selectNoticeByNo(noticeNo);
			if(notice != null) {
				model.addAttribute("notice", notice);
				return "notice/noticeModify";
			} else {
				model.addAttribute("alertMsg", "공지사항을 불러올 수 없습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
		
}
