package kr.co.candytoon.ask.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;

public interface AskStore {


	/**
	 * 문의사항 등록 Store
	 * @param session
	 * @param ask
	 * @return int result
	 */
	int insertAsk(SqlSession session, Ask ask);

	/**
	 * 문의사항 수정 Store
	 * @param session
	 * @param ask
	 * @return int result
	 */
	int updateAsk(SqlSession session, Ask ask);

	/**
	 * 문의사항 삭제 Store
	 * @param session
	 * @param askNo
	 * @return int result
	 */
	int deleteAsk(SqlSession session, Ask askNo);

	/**
	 * 문의사항 상세 Store
	 * @param session
	 * @param askNo
	 * @return Ask askOne
	 */
	Ask selecAskByNo(SqlSession session, Ask askNo);

	/**
	 * 문의사항 리스트 Store
	 */
	List<Ask> selectAskList(SqlSession session, AskPageInfo pInfo);

	/**
	 * 검색결과 리스트 Store
	 * @param session
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<Ask> selectAskListByKeyword(SqlSession session, AskPageInfo pInfo, Map<String, Object> paramMap);

	/**
	 * 문의사항 게시물 수 조회 Store
	 * @param session
	 * @return
	 */
	int selectListCount(SqlSession session);

	/**
	 * 검색결과 전체 게시물 수 조회 Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectSearchListCount(SqlSession session, Map<String, Object> paramMap);



}
