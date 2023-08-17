package kr.co.candytoon.ask.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.ask.domain.Ask;
import kr.co.candytoon.ask.domain.PageData;
import kr.co.candytoon.ask.service.AskService;
import kr.co.candytoon.ask.store.AskStore;

@Service
public class AskServiceImpl implements AskService{

	@Autowired
	private SqlSession session;
	@Autowired
	private AskStore aStore;
	
	@Override
	public PageData selectAskList(int currentPage) {
		List<Ask> aList = aStore.selectAskList(session, currentPage);
		String pageNavi = aStore.generateNavi(session, currentPage);
		PageData pData = new PageData(aList, pageNavi);
		return pData;
	}

	@Override
	public int insertAsk(Ask ask) {
		int result = aStore.insertAsk(session, ask);
		return result;
	}

	@Override
	public Ask selecAskByNo(Ask askNo) {
		Ask askOne = aStore.selecAskByNo(session, askNo);
		return askOne;
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

}
