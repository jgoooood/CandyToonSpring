package kr.co.candytoon.fnq.service;

import java.util.List;

import kr.co.candytoon.fnq.domain.Fnq;
import kr.co.candytoon.fnq.domain.FnqPageInfo;

public interface FnqService {

	/**
	 * FnQ 등록 Service
	 * @param fnq
	 * @return
	 */
	int insertFnq(Fnq fnq);

	/**
	 * FnQ 리스트 출력 Service
	 * @param pInfo
	 * @return
	 */
	List<Fnq> selectFnqList(FnqPageInfo pInfo);

	/**
	 * 전체 게시물 수 구하는 Service
	 * @return
	 */
	int getTotalCount();

	/**
	 * FnQ 상세 조회 Service
	 * @param fnqNo
	 * @return
	 */
	Fnq selectFnqByNo(Fnq fnqNo);

	/**
	 * Fnq 수정 Service
	 * @param fnq
	 * @return
	 */
	int modifyFnq(Fnq fnq);

	/**
	 * Fnq 삭제 Service
	 * @param fnqNo
	 * @return
	 */
	int deleteFnq(Fnq fnqNo);

}
