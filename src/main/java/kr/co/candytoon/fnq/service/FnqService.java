package kr.co.candytoon.fnq.service;

import java.util.List;
import java.util.Map;

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

	/**
	 * FnQ 상세 조회 Service
	 * @param fnqNo
	 * @return
	 */
	Fnq selectFnqByNo(Fnq fnqNo);

	/**
	 * FnQ 리스트 출력 Service
	 * @param pInfo
	 * @return
	 */
	List<Fnq> selectFnqList(FnqPageInfo pInfo);

	/**
	 * 검색리스트 출력 Service
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<Fnq> selectFnqListByKeyword(FnqPageInfo pInfo, Map<String, Object> paramMap);

	/**
	 * 전체 게시물 수 구하기 Service
	 * @return
	 */
	int getTotalCount();

	/**
	 * 검색결과 전체 행 구하기 Service
	 * @param paramMap
	 * @return
	 */
	int getSearchListCount(Map<String, Object> paramMap);

}
