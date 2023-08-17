package kr.co.candytoon.ask.service;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.PageData;

public interface AskService {

	/**
	 * 문의사항 리스트 출력
	 * @param currentPage
	 * @return PageDate aList, pageNavi
	 */
	PageData selectAskList(int currentPage);

	/**
	 * 문의사항 등록
	 * @param ask
	 * @return int result
	 */
	int insertAsk(Ask ask);

	/**
	 * 문의사항 상세
	 * @param askNo
	 * @return Ask askOne
	 */
	Ask selecAskByNo(Ask askNo);

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

}
