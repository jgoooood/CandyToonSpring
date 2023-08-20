package kr.co.candytoon.notice.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.candytoon.notice.domain.Notice;
import kr.co.candytoon.notice.domain.NoticePageInfo;
import kr.co.candytoon.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {

	@Override
	public List<Notice> selectNoticeList(SqlSession session, NoticePageInfo pInfo) {
		//offset과 limit은 컨트롤러에서 페이징 처리하고 난 후 값을 전달받아야함.
		int limit = pInfo.getRecordCountPerPage(); //페이지당 보여줄 게시물 개수
		int offset = (pInfo.getCurrentPage() - 1) * limit ; //시작값 
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int totalCount = session.selectOne("NoticeMapper.selectListCount");
		return totalCount;
	}

	@Override
	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public Notice selectNoticeByNo(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectNoticeByNo", noticeNo);
		return notice;
	}

	@Override
	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.update("NoticeMapper.updateNotice", notice);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession session, Notice noticeNo) {
		int result = session.delete("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}

}
