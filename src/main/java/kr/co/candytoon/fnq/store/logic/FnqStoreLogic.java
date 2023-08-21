package kr.co.candytoon.fnq.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.candytoon.fnq.domain.Fnq;
import kr.co.candytoon.fnq.domain.FnqPageInfo;
import kr.co.candytoon.fnq.store.FnqStore;

@Repository
public class FnqStoreLogic implements FnqStore {

	@Override
	public int insertFnq(SqlSession session, Fnq fnq) {
		int result = session.insert("FnqMapper.insertFnq", fnq);
		return result;
	}

	@Override
	public int updateFnq(SqlSession session, Fnq fnq) {
		int result = session.update("FnqMapper.updateFnq", fnq);
		return result;
	}

	@Override
	public int deleteFnq(SqlSession session, Fnq fnqNo) {
		int result = session.delete("FnqMapper.deleteFnq", fnqNo);
		return result;
	}

	@Override
	public List<Fnq> selectFnqList(SqlSession session, FnqPageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Fnq> fList = session.selectList("FnqMapper.selectFnqList", null, rowBounds);
		return fList;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int totalCount = session.selectOne("FnqMapper.selectListCount");
		return totalCount;
	}

	@Override
	public Fnq selectFnqByNo(SqlSession session, Fnq fnqNo) {
		Fnq fnq = session.selectOne("FnqMapper.selectFnqByNo", fnqNo);
		return fnq;
	}
	
	

}
