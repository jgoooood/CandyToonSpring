package kr.co.candytoon.fnq.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.fnq.domain.Fnq;
import kr.co.candytoon.fnq.domain.FnqPageInfo;
import kr.co.candytoon.fnq.service.FnqService;
import kr.co.candytoon.fnq.store.FnqStore;

@Service
public class FnqServiceImpl implements FnqService{

	@Autowired
	private SqlSession session;
	@Autowired
	private FnqStore fStore;
	
	@Override
	public int insertFnq(Fnq fnq) {
		int result = fStore.insertFnq(session, fnq);
		return result;
	}

	@Override
	public int modifyFnq(Fnq fnq) {
		int result = fStore.updateFnq(session, fnq);
		return result;
	}

	@Override
	public int deleteFnq(Fnq fnqNo) {
		int result = fStore.deleteFnq(session, fnqNo);
		return result;
	}

	@Override
	public Fnq selectFnqByNo(Fnq fnqNo) {
		Fnq fnq = fStore.selectFnqByNo(session, fnqNo);
		return fnq;
	}

	@Override
	public List<Fnq> selectFnqList(FnqPageInfo pInfo) {
		List<Fnq> fList = fStore.selectFnqList(session, pInfo);
		return fList;
	}

	@Override
	public List<Fnq> selectFnqListByKeyword(FnqPageInfo pInfo, Map<String, Object> paramMap) {
		List<Fnq> searchList = fStore.selectFnqListByKeyword(session, pInfo, paramMap);
		return searchList;
	}

	@Override
	public int getTotalCount() {
		int totalCount = fStore.selectListCount(session);
		return totalCount;
	}

	@Override
	public int getSearchListCount(Map<String, Object> paramMap) {
		int totalCount = fStore.selectSearchListCount(session, paramMap);
		return totalCount;
	}

}
