package kr.co.candytoon.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.candytoon.member.domain.Member;
import kr.co.candytoon.member.service.MemberService;
import kr.co.candytoon.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService {
	//Service는 sql연결과 DAO연결을 같이 해야하기 때문에 의존성주입을 두번해줌
	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertMember(Member member) {
		int result = mStore.insertMember(sqlSession, member);
		return result;
	}

	@Override
	public int changePw(Member member) {
		int result = mStore.changePw(sqlSession, member);
		return result;
	}

	@Override
	public int changeEmail(Member member) {
		int result = mStore.changeEmail(sqlSession, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(sqlSession, memberId);
		return result;
	}

	@Override
	public Member loginCheck(Member member) {
		Member mOne = mStore.loginCheck(sqlSession, member);
		return mOne;
	}

	@Override
	public Member selectOneById(String memberId) {
		Member mOne = mStore.selectOneById(sqlSession, memberId);
		return mOne;
	}

	@Override
	public Member findId(Member member) {
		Member mOne = mStore.findId(sqlSession, member);
		return mOne;
	}

	@Override
	public Member confirmPw(Member member) {
		Member mOne = mStore.confirmPw(sqlSession, member);
		return mOne;
	}

}
