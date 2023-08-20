package kr.co.candytoon.notice.service;

import java.util.List;

import kr.co.candytoon.notice.domain.Notice;
import kr.co.candytoon.notice.domain.NoticePageInfo;

public interface NoticeService {

	/**
	 * 공지사항 리스트 출력 Service
	 * @param pInfo 
	 * @return
	 */
	List<Notice> selectNoticeList(NoticePageInfo pInfo);

	/**
	 * 공지사항 전체 행 구하는 Service
	 * @return
	 */
	int getListCount();

	/**
	 * 공지사항 등록 Service
	 * @param notice
	 * @return
	 */
	int insertNotice(Notice notice);

	/**
	 * 공지사항 조회 Service
	 * @param noticeNo
	 * @return
	 */
	Notice selectNoticeByNo(int noticeNo);

	/**
	 * 공지사항 수정 Service
	 * @param notice
	 * @return
	 */
	int modifyNotice(Notice notice);

	/**
	 * 공지사항 삭제 Service
	 * @param noticeNo
	 * @return
	 */
	int deleteNotice(Notice noticeNo);

}
