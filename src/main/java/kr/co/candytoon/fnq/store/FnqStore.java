package kr.co.candytoon.fnq.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.fnq.domain.Fnq;
import kr.co.candytoon.fnq.domain.FnqPageInfo;

public interface FnqStore {

	/**
	 * FnQ 등록 Store
	 * @param session
	 * @param fnq
	 * @return
	 */
	int insertFnq(SqlSession session, Fnq fnq);

	/**
	 * FnQ 수정 Service
	 * @param session
	 * @param fnq
	 * @return
	 */
	int updateFnq(SqlSession session, Fnq fnq);

	/**
	 * FnQ 삭제 Serivce
	 * @param session
	 * @param fnqNo
	 * @return
	 */
	int deleteFnq(SqlSession session, Fnq fnqNo);

	/**
	 * FnQ 리스트 출력 Store
	 * @param session
	 * @param pInfo
	 * @return
	 */
	List<Fnq> selectFnqList(SqlSession session, FnqPageInfo pInfo);

	/**
	 * 전체 게시물 수 구하는 Store
	 * @return
	 */
	int selectListCount(SqlSession session);
	
	/**
	 * FnQ 상세 조회 Store
	 * @param session
	 * @param fnqNo
	 * @return
	 */
	Fnq selectFnqByNo(SqlSession session, Fnq fnqNo);

}
