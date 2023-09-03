package kr.co.candytoon.webtoon.service;

import java.util.List;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.domain.WebtoonPageInfo;

public interface WebtoonService {

	/**
	 * 웹툰등록 Service
	 * @param webtoon
	 * @return
	 */
	int insertWebtoon(Webtoon webtoon);

	/**
	 * 웹툰 정보 수정 Service
	 * @param webtoon
	 * @return
	 */
	int updateWebtoon(Webtoon webtoon);

	/**
	 * 웹툰리스트 전체인기작 : home사용 Service
	 * @return
	 */
	List<Webtoon> selectListByViewCount();

	/**
	 * 웹툰리스트 최신인기작 : home사용 Service
	 * @return
	 */
	List<Webtoon> selectListByNewOpen();

	/**
	 * 웹툰리스트 완결인기작 : home사용 Service
	 * @return
	 */
	List<Webtoon> selectListByEnd();

	/**
	 * 웹툰리스트 조회 : 관리자페이지 사용 Service
	 * @param pInfo 
	 * @return
	 */
	List<Webtoon> selectAllList(WebtoonPageInfo pInfo);

	/**
	 * 웹툰 정보 조회 Service
	 * @param webtoonNo
	 * @return
	 */
	Webtoon selectOneByNo(int webtoonNo);

	/**
	 * 웹툰리스트 개수 Service
	 * @return
	 */
	int getListCount();

}
