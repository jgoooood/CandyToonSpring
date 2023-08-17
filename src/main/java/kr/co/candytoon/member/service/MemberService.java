package kr.co.candytoon.member.service;

import kr.co.candytoon.member.domain.Member;

public interface MemberService {
	public int insertMember(Member member);

	public int changePw(Member member);

	public Member loginCheck(Member member);

	public Member selectOneById(String memberId);

	public Member confirmPw(Member member);

	public Member findId(Member member);
}
