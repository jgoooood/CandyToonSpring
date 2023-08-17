package kr.co.candytoon.notice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.notice.service.NoticeService;
import kr.co.candytoon.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeStore nStore;
}
