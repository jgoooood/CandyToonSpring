package kr.co.candytoon.webtoon.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.webtoon.domain.Webtoon;

public interface WebtoonStore {

	List<Webtoon> selectTopList(SqlSession session);

}
