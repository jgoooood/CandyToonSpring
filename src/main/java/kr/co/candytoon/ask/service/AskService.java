package kr.co.candytoon.ask.service;

import java.util.List;
import java.util.Map;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;

public interface AskService {

	/**
	 * 문의사항 등록 Service
	 * @param ask
	 * @return int result
	 */
	int insertAsk(Ask ask);

	/**
	 * 문의사항 수정 Service
	 * @param ask
	 * @return int result
	 */
	int updateAsk(Ask ask);

	/**
	 * 문의사항 삭제 Service
	 * @param askNo
	 * @return int result
	 */
	int deleteAsk(Ask askNo);

	/**
	 * 문의사항 상세 Service
	 * @param askNo
	 * @return Ask askOne
	 */
	Ask selecAskByNo(Ask askNo);

	/**
	 * 문의사항 리스트 Service
	 * @param pInfo
	 * @return
	 */
	List<Ask> selectAskList(AskPageInfo pInfo);

	/**
	 * 검색결과 리스트 Service
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<Ask> selectAskListByKeyword(AskPageInfo pInfo, Map<String, Object> paramMap);

	/**
	 * 문의사항 전체 개수 조회 Service
	 * @return
	 */
	int getListCount();

	/**
	 * 검색결과 전체 개수 조회 Service
	 * @param paramMap
	 * @return
	 */
	int getSearchListCount(Map<String, Object> paramMap);



}
