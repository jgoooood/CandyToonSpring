package kr.co.candytoon.ask.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.store.AskStore;

@Repository
public class AskStoreLogic implements AskStore {

	@Override
	public int insertAsk(SqlSession session, Ask ask) {
		int result = session.insert("AskMapper.insertAsk", ask);
		return result;
	}
	
	@Override
	public List<Ask> selectAskList(SqlSession session, int currentPage) {
		int limit = 5; //페이지당 보여줄 게시물 개수 : 끝값
		int offset = (currentPage - 1) * limit; //시작값
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Ask> aList = session.selectList("AskMapper.selectAskList", null, rowBounds);
		return aList;
	}
	//페이징 네비게이터 생성
	public String generateNavi(SqlSession session, int currentPage) {
		int totalCount = getTotalCount(session);
		// -> 2)페이지당 보여줄 목록 수
		int recordCountPerPage = 5;
		// -> 3)네비게이터 수 계산
		int naviTotalCount = 0;
		if(totalCount % recordCountPerPage > 0) {
			naviTotalCount = totalCount / recordCountPerPage +1;
		} else {
			naviTotalCount = totalCount / recordCountPerPage;		
		}
		//-------------------------- 1. 네비게이터 수 완료 -------------------------
		
		//------------------------- 2. 네비게이터 범위설정 -------------------------
		// -> 1) 한 범위당 보여줄 네비게이터 개수
		int naviCountPerPage = 5;
		// -> 2) 범위별(페이지당)로 보여질 네비게이터 시작값, 마지막값 구하기
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		//---------------------- 2. 네비게이터 범위설정 완료 ----------------------
		
		//---------------------- 3. 이전버튼, 다음버튼 생성 ----------------------
		// -> 1) 이전버튼, 다음버튼 생성여부 판단
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == naviTotalCount) {
			needNext = false;
		}
		// -> 2) StringBuffer로 이전, 다음버튼 및 네비게이터 연결
		StringBuffer result = new StringBuffer();
		if(needPrev) {
			result.append("<a href='/ask/list.do?currentPage="+(startNavi-1)+"'><</a> ");
		}
		for(int i = startNavi; i < naviTotalCount; i++) {
			result.append("<a href='/ask/list.do?currentPage="+i+"'>" + i + "</a>");
		}
		if(needNext) { 
			result.append("<a href='/ask/list.do?currentPage="+(endNavi+1)+"'>></a>");
		}
		return result.toString();
	}
	// 전체 행 구하는 메소드
	private int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("AskMapper.getTotalCount");
		return totalCount;
	}

	@Override
	public Ask selecAskByNo(SqlSession session, Ask askNo) {
		Ask askOne = session.selectOne("AskMapper.selecAskByNo", askNo);
		return askOne;
	}

	@Override
	public int updateAsk(SqlSession session, Ask ask) {
		int result = session.update("AskMapper.updateAsk", ask);
		return result;
	}

	@Override
	public int deleteAsk(SqlSession session, Ask askNo) {
		int result = session.delete("AskMapper.deleteAsk", askNo);
		return result;
	}
	
	
	
}
