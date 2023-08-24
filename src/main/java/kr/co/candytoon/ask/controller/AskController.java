package kr.co.candytoon.ask.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	// 파일등록 순서
	// 1. pom.xml : 파일업로드 라이브러리 다운 
	// 2. root-context.xml : 파일업로드 관련 빈등록 진행->uploadFile 객체받을 수 있음 MultipartResolver
	// 3. JSP에서 넘어오는 파일정보 RequestParam으로 받기 (uploadFile)

	@RequestMapping(value="/ask/insert.kr", method=RequestMethod.POST)
	public String insertAsk(
			@ModelAttribute Ask ask
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			//파일정보가 있으면 각 변수에 저장
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				//saveFile메소드가 Map에 파일정보 저장함
				Map<String, Object> aMap = this.saveFile(request, uploadFile);
				// setter메소드로 파일 정보 저장
				ask.setAskFileName((String)aMap.get("fileName"));
				ask.setAskFileRename((String)aMap.get("fileRename"));
				ask.setAskFilePath((String)aMap.get("filePath"));
				ask.setAskFileLength((long)aMap.get("fileLength"));
			}
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
	
	private Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
		Map<String, Object> aMap = new HashMap<String, Object>();
		// 1. 파일명 저장
		String fileName = uploadFile.getOriginalFilename();
		//resources폴더 경로 가져오기 : request.getSession()->getServletContext()->getRealPath
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 업로드파일 저장할 폴더경로 변수 저장
		String saveFolder = root + "\\uploadFiles";
		// 저장폴더 없을 경우 생성
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdir();
		}
		// 2. 파일리네임
		String extension = fileName.substring(fileName.lastIndexOf(".")+1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = "A" + sdf.format(new Date(System.currentTimeMillis())) + "."+extension;		
		// 3. 저장폴더에 파일 저장->업로드 파일 경로생성
		String savePath = saveFolder + "\\" + fileRename;
		File file = new File(savePath); // 파일 생성
		uploadFile.transferTo(file); // 파일저장
		// 3. 파일크기 저장
		long fileLength = uploadFile.getSize();
		// 4. Map저장
		aMap.put("fileName", fileName);
		aMap.put("fileRename", fileRename);
		aMap.put("filePath", savePath);
		aMap.put("fileLength", fileLength);
		return aMap;
	}

	// 문의사항 수정
	@RequestMapping(value="/ask/modify.kr", method=RequestMethod.POST)
	public String updateAsk(
			Ask ask
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				// 기존 업로드 파일 여부 체크
				String existedfileName = uploadFile.getOriginalFilename();
				this.deleteFile(request, existedfileName);
				// uploadFile객체를 저장할 수 있는 saveFile메소드 호출
				Map<String, Object> aMap = this.saveFile(request, uploadFile);
				// aMap에서 값을 가져와서 업로드 파일정보 새로 세팅
				ask.setAskFileName((String)aMap.get("fileName"));
				ask.setAskFileRename((String)aMap.get("fileRename"));
				ask.setAskFilePath((String)aMap.get("filePath"));
				ask.setAskFileLength((long)aMap.get("fileLength"));

			} 
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

	private void deleteFile(HttpServletRequest request, String existedfileName) {
		//resources경로가져오기
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delFilePath = root + "\\uploadFiles\\"+existedfileName;
		File file = new File(delFilePath);
		if(file.exists()) {
			file.delete();
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
