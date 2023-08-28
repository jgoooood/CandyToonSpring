package kr.co.candytoon.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.notice.domain.Notice;
import kr.co.candytoon.notice.domain.NoticePageInfo;

public interface NoticeStore {

	/**
	 * 공지사항 등록 Store
	 * @param session
	 * @param notice
	 * @return
	 */
	int insertNotice(SqlSession session, Notice notice);

	/**
	 * 공지사항 수정 Store
	 * @param session
	 * @param notice
	 * @return
	 */
	int updateNotice(SqlSession session, Notice notice);

	/**
	 * 공지사항 삭제 Store
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	int deleteNotice(SqlSession session, Notice noticeNo);

	/**
	 * 공지사항 리스트 출력 Store
	 * @param session
	 * @param pInfo 
	 * @return
	 */
	List<Notice> selectNoticeList(SqlSession session, NoticePageInfo pInfo);

	/**
	 * 공지사항 전체 행 구하기 Store
	 * @param session
	 * @return
	 */
	int selectListCount(SqlSession session);

	/**
	 * 공지사항 조회 Store
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	Notice selectNoticeByNo(SqlSession session, int noticeNo);

	/**
	 * 검색결과에 따른 전체 행 구하기 Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectSearchListCount(SqlSession session, Map<String, Object> paramMap);

	/**
	 * 검색결과 리스트 출력 Store
	 * @param session
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<Notice> searchNoticesByKeyword(SqlSession session, NoticePageInfo pInfo, Map<String, Object> paramMap);

}
