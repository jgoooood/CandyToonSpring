package kr.co.candytoon.ask.service;

import java.util.List;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;

public interface AskService {

	/**
	 * 문의사항 리스트 Service
	 * @param pInfo
	 * @return
	 */
	List<Ask> selectAskList(AskPageInfo pInfo);

	/**
	 * 공지사항 전체 개수 조회 Service
	 * @return
	 */
	int getListCount();
	
	/**
	 * 문의사항 등록 Service
	 * @param ask
	 * @return int result
	 */
	int insertAsk(Ask ask);

	/**
	 * 문의사항 상세 Service
	 * @param askNo
	 * @return Ask askOne
	 */
	Ask selecAskByNo(Ask askNo);

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



}
