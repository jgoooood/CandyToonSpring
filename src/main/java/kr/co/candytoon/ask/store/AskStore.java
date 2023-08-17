package kr.co.candytoon.ask.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.ask.domain.Ask;

public interface AskStore {

	/**
	 * 문의사항 리스트 출력
	 * @param session
	 * @param currentPage
	 * @return List<Ask>
	 */
	List<Ask> selectAskList(SqlSession session, int currentPage);
	
	/**
	 * 네비게이터 생성
	 * @param session
	 * @param currentPage
	 * @return String pageNavi
	 */
	public String generateNavi(SqlSession session, int currentPage);

	/**
	 * 문의사항 등록
	 * @param session
	 * @param ask
	 * @return int result
	 */
	int insertAsk(SqlSession session, Ask ask);

	/**
	 * 문의사항 상세
	 * @param session
	 * @param askNo
	 * @return Ask askOne
	 */
	Ask selecAskByNo(SqlSession session, Ask askNo);

	/**
	 * 문의사항 수정
	 * @param session
	 * @param ask
	 * @return int result
	 */
	int updateAsk(SqlSession session, Ask ask);

	/**
	 * 
	 * @param session
	 * @param askNo
	 * @return int result
	 */
	int deleteAsk(SqlSession session, Ask askNo);

}
