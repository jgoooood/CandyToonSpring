package kr.co.candytoon.member.service;

import kr.co.candytoon.member.domain.Member;

public interface MemberService {
	
	/**
	 * 회원등록 Service
	 * @param member
	 * @return
	 */
	public int insertMember(Member member);

	/**
	 * 비밀번호 변경Service
	 * @param member
	 * @return
	 */
	public int changePw(Member member);

	/**
	 * 이메일주소 변경 Service
	 * @param member
	 * @return
	 */
	public int changeEmail(Member member);

	/**
	 * 회원탈퇴 Service
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId);

	/**
	 * 로그인 체크 Service
	 * @param member
	 * @return
	 */
	public Member loginCheck(Member member);

	/**
	 * ID로 회원조회 Service
	 * @param memberId
	 * @return
	 */
	public Member selectOneById(String memberId);

	/**
	 * 비밀번호 체크 Service
	 * @param member
	 * @return
	 */
	public Member confirmPw(Member member);

	/**
	 * 아이디 찾기 Service
	 * @param member
	 * @return
	 */
	public Member findId(Member member);
}
