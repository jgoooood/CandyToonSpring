package kr.co.candytoon.webtoon.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.domain.WebtoonPageInfo;
import kr.co.candytoon.webtoon.store.WebtoonStore;

@Repository
public class WebtoonStoreLogic implements WebtoonStore{

	@Override
	public List<Webtoon> selectListByViewCount(SqlSession session) {
		List<Webtoon> wList = session.selectList("WebtoonMapper.selectListByViewCount");
		return wList;
	}

	@Override
	public List<Webtoon> selectAllList(SqlSession session, WebtoonPageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Webtoon> allList = session.selectList("WebtoonMapper.selectAllList", null, rowBounds);
		return allList;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int listCount = session.selectOne("WebtoonMapper.selectListCount");
		return listCount;
	}

	@Override
	public int insertWebtoon(SqlSession session, Webtoon webtoon) {
		int result = session.insert("WebtoonMapper.insertWebtoon", webtoon);
		return result;
	}

}
