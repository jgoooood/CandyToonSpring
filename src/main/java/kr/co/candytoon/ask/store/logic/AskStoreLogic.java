package kr.co.candytoon.ask.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;
import kr.co.candytoon.ask.store.AskStore;

@Repository
public class AskStoreLogic implements AskStore {

	@Override
	public int insertAsk(SqlSession session, Ask ask) {
		int result = session.insert("AskMapper.insertAsk", ask);
		return result;
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

	@Override
	public List<Ask> selectAskList(SqlSession session, AskPageInfo pInfo) {
		// pInfo에 저장된 페이징에 관련된 정보를 꺼내서 넣어줌
		int limit = pInfo.getRecordCountPerPage(); //페이지당 보여줄 게시물 개수 : 끝값
		int offset = (pInfo.getCurrentPage() - 1) * limit; //시작값
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Ask> aList = session.selectList("AskMapper.selectAskList", null, rowBounds);
		return aList;
	}
	
	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("AskMapper.selectListCount");
		return result;
	}
	
	/*페이징 네비게이터 생성 : 컨트롤러로 이동
	public String generateNavi(SqlSession session, int currentPage) {}
	*/


	@Override
	public Ask selecAskByNo(SqlSession session, Ask askNo) {
		Ask askOne = session.selectOne("AskMapper.selecAskByNo", askNo);
		return askOne;
	}

	@Override
	public int selectSearchListCount(SqlSession session, Map<String, Object> paramMap) {
		int totalCount = session.selectOne("AskMapper.selectSearchListCount", paramMap);
		return totalCount;
	}

	@Override
	public List<Ask> selectAskListByKeyword(SqlSession session, AskPageInfo pInfo, Map<String, Object> paramMap) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage() -1)*limit; 
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Ask> searchList = session.selectList("AskMapper.selectAskListByKeyword", paramMap, rowBounds);
		return searchList;
	}

	
	
	
}
