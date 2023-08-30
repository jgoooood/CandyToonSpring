package kr.co.candytoon.webtoon.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.service.WebtoonService;
import kr.co.candytoon.webtoon.store.WebtoonStore;

@Service
public class WebtoonServiceImpl implements WebtoonService {

	@Autowired
	private SqlSession session;
	@Autowired
	private WebtoonStore wStore;
	
	@Override
	public List<Webtoon> selectTopList() {
		List<Webtoon> wList = wStore.selectTopList(session);
		return wList;
	}

}
