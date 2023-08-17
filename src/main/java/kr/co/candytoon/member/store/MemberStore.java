package kr.co.candytoon.member.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.member.domain.Member;

public interface MemberStore {
	public int insertMember(SqlSession session, Member member);

	public int changePw(SqlSession session, Member member);

	public Member loginCheck(SqlSession session, Member member);

	public Member selectOneById(SqlSession session, String memberId);

	public Member findId(SqlSession session, Member member);

	public Member confirmPw(SqlSession session, Member member);
}
