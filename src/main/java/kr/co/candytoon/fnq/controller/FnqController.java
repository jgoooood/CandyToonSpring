package kr.co.candytoon.fnq.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.candytoon.fnq.domain.Fnq;
import kr.co.candytoon.fnq.domain.FnqPageInfo;
import kr.co.candytoon.fnq.service.FnqService;

@Controller
public class FnqController {
	
	@Autowired
	private FnqService service;
	
	//fnq등록페이지 이동
	@RequestMapping(value="/fnq/insert.kr", method=RequestMethod.GET)
	public String showFnqInsertForm(Model model, HttpSession session) {
		String memberId = (String)session.getAttribute("memberId");
		if(memberId == null || memberId.equals("")) {
			model.addAttribute("alertMsg", "관리자만 접근할 수 있습니다.");
			model.addAttribute("url", "/fnq/list.kr");
			return "common/serviceFailed";
		} else {
			return "fnq/fnqInsert";
		}
	}

	//fnq 등록
	@RequestMapping(value="/fnq/insert.kr", method=RequestMethod.POST)
	public String insertFnq(
			Fnq fnq
			, @RequestParam(value="uploadFile", required = false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			if(uploadFile != null && !uploadFile.isEmpty()) {
				Map<String, Object> fMap = saveFile(uploadFile, request);
				fnq.setFnqFileName((String)fMap.get("fileName"));
				fnq.setFnqFileRename((String)fMap.get("fileRename"));
				fnq.setFnqFilePath((String)fMap.get("filePath"));
				fnq.setFnqFileLength((long)fMap.get("fileLength"));
			} 
			int result = service.insertFnq(fnq);
			if(result > 0) {
				model.addAttribute("msg", "FnQ등록이 완료되었습니다.");
				model.addAttribute("url", "/fnq/list.kr");
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
	
	//수정페이지 이동
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

	//수정
	@RequestMapping(value="/fnq/modify.kr", method=RequestMethod.POST)
	public String modifyFnq(
			Fnq fnq
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			if(uploadFile != null && !uploadFile.isEmpty()) {
				//기존 업로드 파일 있을 경우 삭제 후 새로 등록
				String existedfileName = fnq.getFnqFileRename();
				if(existedfileName != null) {
					this.deleteFile(request, existedfileName);
				}
				//saveFile메소드->Map에 새로운 업로드 파일 정보가 저장됨
				Map<String, Object> infoMap = saveFile(uploadFile, request);
				//Map에 저장된 파일정보를 setter메소드로 세팅
				fnq.setFnqFileName((String)infoMap.get("fileName"));
				fnq.setFnqFileRename((String)infoMap.get("fileRename"));
				fnq.setFnqFilePath((String)infoMap.get("filePath"));
				fnq.setFnqFileLength((long)infoMap.get("fileLength"));
			}
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

	//fnq 삭제
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

	//목록조회
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
	
	//fnq상세페이지 조회
	@RequestMapping(value="/fnq/detail.kr", method=RequestMethod.GET)
	public String showDetailForm(Fnq fnqNo, Model model) {
		try {
			Fnq fnq = service.selectFnqByNo(fnqNo);
			if(fnq != null) {
				model.addAttribute("fnq", fnq);
				return "fnq/fnqDetail";
			} else {
				model.addAttribute("alertMsg", "상세 내용을 불러올 수 없습니다.");
				model.addAttribute("url", "/fnq/list.kr");
				return "common/serviceFailed";
			}		
		} catch (Exception e) {
			model.addAttribute("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}

	//검색
	@RequestMapping(value="/fnq/search.kr", method=RequestMethod.GET)
	public ModelAndView searchFnqList(
			ModelAndView mv
			, @RequestParam("searchKeyword") String searchKeyword
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
		//검색조건, 검색키워드 MAP에 저장
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchKeyword", searchKeyword);
		paramMap.put("searchCondition", searchCondition);
		//페이징처리를 위한 검색결과 개수 구하기
		int totalCount = service.getSearchListCount(paramMap);
		FnqPageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Fnq> searchList = service.selectFnqListByKeyword(pInfo, paramMap);
		if(!searchList.isEmpty()) {
			mv.addObject("searchKeyword", searchKeyword);
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("sList", searchList);
			mv.addObject("pInfo", pInfo);
			mv.setViewName("fnq/fnqSearchList");
		} else {
			mv.addObject("alertMsg", "검색 결과를 불러올 수 없습니다.");
			mv.addObject("url", "/fnq/list.kr");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	//페이징
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

	//파일저장
	private Map<String, Object> saveFile(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		//업로드 파일 파일명, 저장할 폴더 지정
		String fileName = uploadFile.getOriginalFilename();
		String root = request.getSession().getServletContext().getRealPath("resources");
		String saveFolder = root + "\\uploadFiles";
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdir();
		}
		//파일명 중복을 피하기 위한 리네임->업로드시각에 따른 SimpleDateFormat + Date 객체사용 + 업로드파일 확장자추출
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strResult = sdf.format(new Date(System.currentTimeMillis()));
		String ext = fileName.substring(fileName.lastIndexOf(".")+1); // +1 : . 포함x
		String fileRename = "F"+strResult+"."+ext; //식별자+파일리네임(date)+"."+확장자
		//경로생성->Rename값 , 생성된 경로로 uploadFile값을 대체, fileLength 크기 저장
		String savePath = saveFolder + "\\" + fileRename;
		File file = new File(savePath);
		uploadFile.transferTo(file);
		long fileLength = uploadFile.getSize();
		//Map 저장
		infoMap.put("fileName", fileName);
		infoMap.put("fileRename", fileRename);
		infoMap.put("filePath", savePath);
		infoMap.put("fileLength", fileLength);
		
		return infoMap;
	}

	//파일삭제
	private void deleteFile(HttpServletRequest request, String existedfileName) {
		// request : 경로가져오기
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delFilepath = root+"\\uploadFiles\\"+existedfileName;
		File file = new File(delFilepath);
		if(file.exists()) {
			file.delete();			
		}
	}
}
