package kr.co.candytoon.webtoon.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.domain.WebtoonPageInfo;
import kr.co.candytoon.webtoon.service.WebtoonService;
import kr.co.candytoon.webtoon.store.WebtoonStore;

@Service
public class WebtoonServiceImpl implements WebtoonService {

	@Autowired
	private SqlSession session;
	@Autowired
	private WebtoonStore wStore;
	
	@Override
	public int insertWebtoon(Webtoon webtoon) {
		int result = wStore.insertWebtoon(session, webtoon);
		return result;
	}

	@Override
	public int updateWebtoon(Webtoon webtoon) {
		int result = wStore.updateWebtoon(session, webtoon);
		return result;
	}

	@Override
	public List<Webtoon> selectListByViewCount() {
		List<Webtoon> topViewList = wStore.selectListByViewCount(session);
		return topViewList;
	}

	@Override
	public List<Webtoon> selectListByNewOpen() {
		List<Webtoon> topNewList = wStore.selectListByNewOpen(session);
		return topNewList;
	}

	@Override
	public List<Webtoon> selectListByEnd() {
		List<Webtoon> topEndList = wStore.selectListByEnd(session);
		return topEndList;
	}

	@Override
	public Webtoon selectOneByNo(int webtoonNo) {
		Webtoon webtoon = wStore.selectOneByNo(session, webtoonNo);
		return webtoon;
	}

	@Override
	public List<Webtoon> selectAllList(WebtoonPageInfo pInfo) {
		List<Webtoon> allList = wStore.selectAllList(session, pInfo);
		return allList;
	}

	@Override
	public int getListCount() {
		int listCount = wStore.selectListCount(session);
		return listCount;
	}

}
