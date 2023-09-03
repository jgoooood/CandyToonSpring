package kr.co.candytoon.webtoon.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.domain.WebtoonPageInfo;
import kr.co.candytoon.webtoon.service.WebtoonService;

@Controller
@RequestMapping(value="/webtoon")
public class WebtoonController {

	@Autowired
	private WebtoonService wService;
	
	//웹툰 등록 페이지 이동
	@RequestMapping(value="/insert.kr", method=RequestMethod.GET)
	public ModelAndView showInsertForm(ModelAndView mv, HttpSession session) {
		String memberId = (String)session.getAttribute("memberId");
		if(memberId != null && memberId.equals("admin")) {
			mv.setViewName("webtoon/webtoonInsert");
		} else {
			mv.addObject("alertMsg", "관리자만 접근할 수 있습니다.");
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	//웹툰등록
	@RequestMapping(value="/insert.kr", method=RequestMethod.POST)
	public ModelAndView insertWebtoon(
			Webtoon webtoon
			, ModelAndView mv
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpSession session
			, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberId");
		try {
			if(memberId != null && memberId.equals("admin")) {
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					Map<String, Object> fileMap = this.saveFile(uploadFile, request);
					webtoon.setwCoverName((String)fileMap.get("fileName"));
					webtoon.setwCoverRename((String)fileMap.get("fileRename"));
					webtoon.setwCoverPath((String)fileMap.get("filePath"));
				}
				int result = wService.insertWebtoon(webtoon);
				if(result > 0) {
					mv.addObject("msg", "웹툰 등록이 완료되었습니다.");
					mv.addObject("url", "/webtoon/list.kr");
					mv.setViewName("common/serviceSuccess");
				} else {
					mv.addObject("alertMsg", "[서비스실패] 웹툰등록이 완료되지 않았습니다.");
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("alertMsg", "관리자만 접근할 수 있습니다.");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
			
		} catch (Exception e) {
			mv.addObject("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/serviceFailed");
		}
		
		return mv;
			
	}
	
	//웹툰 전체 리스트 출력
	@RequestMapping(value="/list.kr", method=RequestMethod.GET)
	public ModelAndView showWebtoonList(
			ModelAndView mv
			, HttpSession session
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
		String memberId = (String)session.getAttribute("memberId");
		try {
			if(memberId != null && memberId.equals("admin")) {
				int totalCount = wService.getListCount();
				WebtoonPageInfo pInfo = getPageInfo(currentPage, totalCount);
				List<Webtoon> allList = wService.selectAllList(pInfo);
				mv.addObject("pInfo", pInfo);
				mv.addObject("allList", allList);
				mv.setViewName("webtoon/webtoonList");
			} else {
				mv.addObject("alertMsg", "관리자만 접근할 수 있습니다.");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	//웹툰 상세정보조회
	@RequestMapping(value="/info.kr", method=RequestMethod.GET) 
	public ModelAndView showWebtoonInfo(
			ModelAndView mv
			, @RequestParam("webtoonNo") int webtoonNo
			, HttpSession session){
		try {
			String memberId = (String)session.getAttribute("memberId");
			if(memberId != null && memberId.equals("admin")) {
				Webtoon webtoon = wService.selectOneByNo(webtoonNo);
				mv.addObject("webtoon", webtoon);
				mv.setViewName("webtoon/webtoonInfo");
				
			} else {
				mv.addObject("alertMsg", "관리자만 접근할 수 있습니다.");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	// 수정페이지 이동
	@RequestMapping(value="/modify.kr", method=RequestMethod.GET)
	public ModelAndView showModifyForm(
			ModelAndView mv
			, @RequestParam(value = "webtoonNo") Integer webtoonNo
			, HttpSession session) {
		String memberId = (String)session.getAttribute("memberId");
		if(memberId != null && memberId.equals("admin")) {
			Webtoon webtoon = wService.selectOneByNo(webtoonNo);
			mv.addObject("webtoon", webtoon);
			mv.setViewName("webtoon/webtoonModify");
		} else {
			mv.addObject("alertMsg", "관리자만 접근할 수 있습니다.");
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	@RequestMapping(value="/modify.kr", method=RequestMethod.POST)
	public ModelAndView modifyWebtoon(
			ModelAndView mv
			, Webtoon webtoon
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpSession session
			, HttpServletRequest request) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			String url = "/webtoon/info.kr?webtoonNo="+webtoon.getWebtoonNo();
			if(memberId != null && memberId.equals("admin")) {
				if(uploadFile != null && !uploadFile.isEmpty()) { //업로드 파일이 있으면
					//JSP에 히든으로 저장되어있는 기존 파일정보를 가져와서 삭제
					String delFileName = webtoon.getwCoverRename();
					if(delFileName != null) {
						this.deleteFile(request,delFileName);
					}
					Map<String, Object> wMap = this.saveFile(uploadFile, request);
					webtoon.setwCoverName((String)wMap.get("fileName"));
					webtoon.setwCoverRename((String)wMap.get("fileRename"));
					webtoon.setwCoverPath((String)wMap.get("filePath"));
				}	
				int result = wService.updateWebtoon(webtoon);
				if(result > 0) {
					mv.addObject("msg", "웹툰 수정이 완료되었습니다.");
					mv.addObject("url", url);
					mv.setViewName("common/serviceSuccess");
				} else {
					mv.addObject("alertMsg", "[서비스실패] 웹툰수정이 완료되지 않았습니다.");
					mv.addObject("url", url);
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("alertMsg", "관리자만 접근할 수 있습니다.");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("alertMsg", "[서비스실패] 관리자에 문의바랍니다.");
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/serviceFailed");
		} 
		return mv;
	}
	

	private void deleteFile(HttpServletRequest request, String delFileName) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delFilePath = root+"\\uploadFiles\\"+delFileName;
		File file = new File(delFilePath);
		if(file.exists()) {
			file.delete();
		}
		
	}
	//파일저장 메소드 출력
	private Map<String, Object> saveFile(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		String fileName = uploadFile.getOriginalFilename();
		String root = request.getSession().getServletContext().getRealPath("resources");
		String saveFolder = root + "\\uploadFiles";
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdir();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strResult = sdf.format(new Date(System.currentTimeMillis()));
		String fileRename = "c" + strResult +fileName;
		String savePath = saveFolder + "\\" + fileRename;
		File file = new File(savePath);
		uploadFile.transferTo(file);
		
		infoMap.put("fileName", fileName);
		infoMap.put("fileRename", fileRename);
		infoMap.put("filePath", savePath);
		return infoMap;
	}

	//페이징
	private WebtoonPageInfo getPageInfo(Integer currentPage, int totalCount) {
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		int startNavi= (((int)((double)currentPage / naviCountPerPage + 0.9)) -1) * naviCountPerPage +1 ;
		int endNavi = startNavi + naviCountPerPage -1 ;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		WebtoonPageInfo pInfo = new WebtoonPageInfo(currentPage, totalCount, recordCountPerPage, naviCountPerPage, naviTotalCount, startNavi, endNavi);
		return pInfo;
	}
	
}
