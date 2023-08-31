package kr.co.candytoon.webtoon.service;

import java.util.List;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.domain.WebtoonPageInfo;

public interface WebtoonService {

	/**
	 * 웹툰리스트 조회수로 조회 : 메인사용 Service
	 * @return
	 */
	List<Webtoon> selectListByViewCount();

	/**
	 * 웹툰리스트 조회 : 관리자페이지 사용 Service
	 * @param pInfo 
	 * @return
	 */
	List<Webtoon> selectAllList(WebtoonPageInfo pInfo);

	/**
	 * 웹툰리스트 개수 Service
	 * @return
	 */
	int getListCount();

	/**
	 * 웹툰등록 Service
	 * @param webtoon
	 * @return
	 */
	int insertWebtoon(Webtoon webtoon);

}
