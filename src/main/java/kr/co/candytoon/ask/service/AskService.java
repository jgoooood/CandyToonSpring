package kr.co.candytoon.ask.service;

import java.util.List;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;

public interface AskService {

	/**
	 * 문의사항 등록
	 * @param ask
	 * @return int result
	 */
	int insertAsk(Ask ask);

	/**
	 * 문의사항 수정
	 * @param ask
	 * @return int result
	 */
	int updateAsk(Ask ask);

	/**
	 * 문의사항 삭제
	 * @param askNo
	 * @return int result
	 */
	int deleteAsk(Ask askNo);

	/**
	 * 문의사항 리스트
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
	 * 문의사항 상세
	 * @param askNo
	 * @return Ask askOne
	 */
	Ask selecAskByNo(Ask askNo);



}
