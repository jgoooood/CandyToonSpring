package kr.co.candytoon.webtoon.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.store.WebtoonStore;

@Repository
public class WebtoonStoreLogic implements WebtoonStore{

	@Override
	public List<Webtoon> selectTopList(SqlSession session) {
		List<Webtoon> wList = session.selectList("WebtoonMapper.selectTopList");
		return wList;
	}

}
