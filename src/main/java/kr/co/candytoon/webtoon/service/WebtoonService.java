package kr.co.candytoon.webtoon.service;

import java.util.List;

import kr.co.candytoon.webtoon.domain.Webtoon;

public interface WebtoonService {

	List<Webtoon> selectTopList();

}
