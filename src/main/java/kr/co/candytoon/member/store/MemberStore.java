package kr.co.candytoon.member.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.candytoon.member.domain.Member;

public interface MemberStore {
	/**
	 * 회원등록 Store
	 * @param session
	 * @param member
	 * @return
	 */
	public int insertMember(SqlSession session, Member member);

	/**
	 * 비밀번호 변경 Store
	 * @param session
	 * @param member
	 * @return
	 */
	public int changePw(SqlSession session, Member member);

	/**
	 * 이메일주소 변경 Store
	 * @param sqlSession
	 * @param member
	 * @return
	 */
	public int changeEmail(SqlSession session, Member member);

	/**
	 * 회원탈퇴 Store
	 * @param sqlSession
	 * @param memberId
	 * @return
	 */
	public int deleteMember(SqlSession sqlSession, String memberId);

	/**
	 * 로그인 체크 Store
	 * @param session
	 * @param member
	 * @return
	 */
	public Member loginCheck(SqlSession session, Member member);

	/**
	 * ID로 회원조회 Store
	 * @param session
	 * @param memberId
	 * @return
	 */
	public Member selectOneById(SqlSession session, String memberId);

	/**
	 * 비밀번호 체크
	 * @param session
	 * @param member
	 * @return
	 */
	public Member confirmPw(SqlSession session, Member member);

	/**
	 * 아이디 찾기
	 * @param session
	 * @param member
	 * @return
	 */
	public Member findId(SqlSession session, Member member);
}
