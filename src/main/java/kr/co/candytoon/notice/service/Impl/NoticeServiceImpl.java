package kr.co.candytoon.notice.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.notice.domain.Notice;
import kr.co.candytoon.notice.domain.NoticePageInfo;
import kr.co.candytoon.notice.service.NoticeService;
import kr.co.candytoon.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeStore nStore;
	@Autowired
	private SqlSession session;

	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public int modifyNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}

	@Override
	public int deleteNotice(Notice noticeNo) {
		int result = nStore.deleteNotice(session, noticeNo);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(NoticePageInfo pInfo) {
		List<Notice> nList = nStore.selectNoticeList(session, pInfo);
		return nList;
	}

	@Override
	public int getListCount() {
		int totalCount = nStore.selectListCount(session);
		return totalCount;
	}

	@Override
	public Notice selectNoticeByNo(int noticeNo) {
		Notice notice = nStore.selectNoticeByNo(session, noticeNo);
		return notice;
	}

	@Override
	public int getSearchListCount(Map<String, Object> paramMap) {
		int totalCount = nStore.selectSearchListCount(session, paramMap);
		return totalCount;
	}

	@Override
	public List<Notice> searchNoticesByKeyword(NoticePageInfo pInfo, Map<String, Object> paramMap) {
		List<Notice> searchList = nStore.searchNoticesByKeyword(session, pInfo, paramMap);
		return searchList;
	}
}
