package kr.co.candytoon.ask.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;

public interface AskStore {


	/**
	 * 문의사항 리스트
	 */
	List<Ask> selectAskList(SqlSession session, AskPageInfo pInfo);

	/**
	 * 문의사항 게시물 수 조회
	 * @param session
	 * @return
	 */
	int selectListCount(SqlSession session);
	
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
