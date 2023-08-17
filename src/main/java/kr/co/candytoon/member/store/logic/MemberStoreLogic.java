package kr.co.candytoon.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.candytoon.member.domain.Member;
import kr.co.candytoon.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}

	@Override
	public int changePw(SqlSession session, Member member) {
		int result = session.update("MemberMapper.changePw", member);
		return result;
	}

	@Override
	public Member loginCheck(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.loginCheck", member);
		return mOne;
	}

	@Override
	public Member selectOneById(SqlSession session, String memberId) {
		Member mOne = session.selectOne("MemberMapper.selectOneById", memberId);
		return mOne;
	}

	@Override
	public Member findId(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.findId", member);
		return mOne;
	}

	@Override
	public Member confirmPw(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.confirmPw", member);
		return mOne;
	}

}
