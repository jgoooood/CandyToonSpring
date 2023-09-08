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
	public int insertWebtoon(SqlSession session, Webtoon webtoon) {
		int result = session.insert("WebtoonMapper.insertWebtoon", webtoon);
		return result;
	}

	@Override
	public int updateWebtoon(SqlSession session, Webtoon webtoon) {
		int result = session.update("WebtoonMapper.updateWebtoon", webtoon);
		return result;
	}

	@Override
	public int deleteWebtoon(SqlSession session, Integer webtoonNo) {
		int result = session.update("WebtoonMapper.deleteWebtoon", webtoonNo);
		return result;
	}

	@Override
	public List<Webtoon> selectListByViewCount(SqlSession session) {
		List<Webtoon> topViewList = session.selectList("WebtoonMapper.selectListByViewCount");
		return topViewList;
	}

	@Override
	public List<Webtoon> selectListByNewOpen(SqlSession session) {
		List<Webtoon> topNewList = session.selectList("WebtoonMapper.selectListByNewOpen");
		return topNewList;
	}

	@Override
	public List<Webtoon> selectListByEnd(SqlSession session) {
		List<Webtoon> topEndList = session.selectList("WebtoonMapper.selectListByEnd");
		return topEndList;
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
	public Webtoon selectOneByNo(SqlSession session, int webtoonNo) {
		Webtoon webtoon = session.selectOne("WebtoonMapper.selectOneByNo", webtoonNo);
		return webtoon;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int listCount = session.selectOne("WebtoonMapper.selectListCount");
		return listCount;
	}

}
