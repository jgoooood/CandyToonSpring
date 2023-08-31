package kr.co.candytoon.webtoon.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.domain.WebtoonPageInfo;

public interface WebtoonStore {

	/**
	 * 웹툰 등록 Store
	 * @param session
	 * @param webtoon
	 * @return
	 */
	int insertWebtoon(SqlSession session, Webtoon webtoon);

	/**
	 * 웹툰리스트 전체인기작 : home사용 Service
	 * @param session
	 * @return
	 */
	List<Webtoon> selectListByViewCount(SqlSession session);

	/**
	 * 웹툰리스트 최신인기작 : home사용 Service
	 * @param session
	 * @return
	 */
	List<Webtoon> selectListByNewOpen(SqlSession session);

	/**
	 * 웹툰리스트 완결인기작 : home사용 Service
	 * @param session
	 * @return
	 */
	List<Webtoon> selectListByEnd(SqlSession session);
	
	/**
	 * 웹툰리스트 조회 : 관리자페이지 사용 Store
	 * @param session
	 * @param pInfo 
	 * @return
	 */
	List<Webtoon> selectAllList(SqlSession session, WebtoonPageInfo pInfo);

	/**
	 * 웹툰 정보 조회 Store
	 * @param session
	 * @param webtoonNo
	 * @return
	 */
	Webtoon selectOneByNo(SqlSession session, int webtoonNo);

	/**
	 * 웹툰리스트 개수 Store
	 * @param session
	 * @return
	 */
	int selectListCount(SqlSession session);


}
