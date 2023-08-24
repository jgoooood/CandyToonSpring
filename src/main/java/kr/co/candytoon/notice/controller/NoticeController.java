package kr.co.candytoon.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.candytoon.notice.domain.Notice;
import kr.co.candytoon.notice.domain.NoticePageInfo;
import kr.co.candytoon.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	
	@RequestMapping(value="/notice/insert.kr", method=RequestMethod.POST)
	public String insertNotice(
			Notice notice
			, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			//uploadFile null체크
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				//Map사용->saveFile메소드 호출
				Map<String, Object> nMap = this.saveFile(uploadFile, request);
				String fileName = (String)nMap.get("fileName");
				String fileRename = (String)nMap.get("fileRename");
				String filePath = (String)nMap.get("filePath");
				long fileLength = (long)nMap.get("fileLength");
				//Map에서 값을 가져와서 setter메소드로 세팅
				notice.setNoticeFileName(fileName);
				notice.setNoticeFileRename(fileRename);
				notice.setNoticeFilePath(filePath);
				notice.setNoticeFileLength(fileLength);
			}
			int result = service.insertNotice(notice);
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
	public String modifyNotice(
			Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			/*JSP->hidden으로 값을 불러왔으면 기존파일정보 새로 불러오지 않아도 됨
			Notice existedNotice = service.selectNoticeByNo(notice.getNoticeNo());
			if(existedNotice.getNoticeFileName() != null) {
				File existedNoticeFilePath = new File(existedNotice.getNoticeFilePath());
				if(existedNoticeFilePath.exists()) {
					existedNoticeFilePath.delete();
				}
			}*/
	
			//uploadFile null체크
			if(uploadFile != null && !uploadFile.isEmpty()) {
				// 기존 업로드된 파일 있는지 체크
				String existedfileName = notice.getNoticeFileRename();
				if(existedfileName != null) { //업로드된 파일이 있으면
					this.deleteFile(request, existedfileName); //삭제메소드 호출(request, fileName 전달)
				}
				//uploadFile은 메소드 + HashMap사용으로 저장 -> saveFile
				Map<String, Object> infoMap = saveFile(uploadFile, request);
				String noticeFileName = (String)infoMap.get("fileName"); //변수에 따로 저장해서 set가능
				notice.setNoticeFileName(noticeFileName);
				notice.setNoticeFileRename((String)infoMap.get("fileRename"));
				notice.setNoticeFilePath((String)infoMap.get("filePath")); //한줄로 set
				notice.setNoticeFileLength((long)infoMap.get("fileLength"));
			} 
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
	public String deleteNotice(
			Notice notice
			, HttpServletRequest request
			, Model model ) {
		try {
			int result = service.deleteNotice(notice);
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
	
	@RequestMapping(value="/notice/search.kr", method=RequestMethod.GET)
	public void searchNoticeList() {
		
	}


	private Map<String, Object> saveFile(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		// 파일경로와 이름 생성
		String fileName = uploadFile.getOriginalFilename();
		String root = request.getSession().getServletContext().getRealPath("resources");
		String saveFolder = root + "\\uploadFiles";
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdir();
		}
		// 파일 업로드시 중복을 피하기 위해 리네임해야함-> 1. 랜덤함수+for문 2.업로드 시각에 따른 Date객체 사용
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strResult = sdf.format(new Date(System.currentTimeMillis()));
		// 사용자가 업로드한 실제파일의 확장자 추출 : .을 포함하지 않고 자르기
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		// 리네임설정 : 업로드파일 식별하기 위한 N+Rename 값에 추출된 확장자 붙여주기
		String fileRename = "N" + strResult + "." + ext;
		//파일 경로설정 : \\uploadFiles 폴더에 리네임한 파일명을 넣어줌
		String savePath = saveFolder + "\\" + fileRename;
		// 파일객체 생성
		File file = new File(savePath);
		// 실제 파일 저장 : transferTo메소드는 호출한 곳으로 예외던지기
		uploadFile.transferTo(file);
		long fileLength = uploadFile.getSize();
		//map 저장 : 파일명, 파일리네임, 파일경로, 파일크기 저장
		infoMap.put("fileName", fileName);
		infoMap.put("fileRename", fileRename);
		infoMap.put("filePath", savePath);
		infoMap.put("fileLength", fileLength);

		return infoMap;
	}

	//파일삭제 메소드 
	private void deleteFile(HttpServletRequest request, String existedfileName) {
		// request로 resources 경로 가져와서 저장
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 삭제할 파일경로 
		String delFilepath = root+"\\uploadFiles\\"+existedfileName;
		File file = new File(delFilepath);
		if(file.exists()) {
			file.delete();
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
}
