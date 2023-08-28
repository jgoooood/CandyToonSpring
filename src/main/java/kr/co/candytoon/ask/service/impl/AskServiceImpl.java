package kr.co.candytoon.ask.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.AskPageInfo;
import kr.co.candytoon.ask.service.AskService;
import kr.co.candytoon.ask.store.AskStore;

@Service
public class AskServiceImpl implements AskService{

	@Autowired
	private SqlSession session;
	@Autowired
	private AskStore aStore;
	

	@Override
	public int insertAsk(Ask ask) {
		int result = aStore.insertAsk(session, ask);
		return result;
	}

	@Override
	public int updateAsk(Ask ask) {
		int result = aStore.updateAsk(session, ask);
		return result;
	}

	@Override
	public int deleteAsk(Ask askNo) {
		int result = aStore.deleteAsk(session, askNo);
		return result;
	}

	@Override
	public Ask selecAskByNo(Ask askNo) {
		Ask askOne = aStore.selecAskByNo(session, askNo);
		return askOne;
	}

	@Override
	public List<Ask> selectAskList(AskPageInfo pInfo) {
		List<Ask> aList = aStore.selectAskList(session, pInfo);
		return aList;
	}

	@Override
	public List<Ask> selectAskListByKeyword(AskPageInfo pInfo, Map<String, Object> paramMap) {
		List<Ask> searchList = aStore.selectAskListByKeyword(session, pInfo, paramMap);
		return searchList;
	}

	@Override
	public int getListCount() {
		int result = aStore.selectListCount(session);
		return result;
	}

	@Override
	public int getSearchListCount(Map<String, Object> paramMap) {
		int totalCount = aStore.selectSearchListCount(session, paramMap);
		return totalCount;
	}





}
