package kr.co.candytoon.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.candytoon.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/notice/list.do", method=RequestMethod.GET)
	public String showNoticeList() {
		return "notice/noticeList";
	}
		
	
}
