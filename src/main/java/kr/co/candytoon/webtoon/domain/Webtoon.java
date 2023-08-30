package kr.co.candytoon.webtoon.domain;

import java.sql.Date;

public class Webtoon {
	private int webtoonNo;
	private String webtoonTitle;
	private String webtoonWriter;
	private String webtoonPainter;
	private String webtoonCover;
	private String webtoonGenre;
	private int wLentPrice;
	private int wBuyPrice;
	private Date wOpenDate;
	private Date wUpdateDate;
	private char wEndYn;
	private int wViewCount;
	
	
	public int getWebtoonNo() {
		return webtoonNo;
	}
	public void setWebtoonNo(int webtoonNo) {
		this.webtoonNo = webtoonNo;
	}
	public String getWebtoonTitle() {
		return webtoonTitle;
	}
	public void setWebtoonTitle(String webtoonTitle) {
		this.webtoonTitle = webtoonTitle;
	}
	public String getWebtoonWriter() {
		return webtoonWriter;
	}
	public void setWebtoonWriter(String webtoonWriter) {
		this.webtoonWriter = webtoonWriter;
	}
	public String getWebtoonPainter() {
		return webtoonPainter;
	}
	public void setWebtoonPainter(String webtoonPainter) {
		this.webtoonPainter = webtoonPainter;
	}
	public String getWebtoonCover() {
		return webtoonCover;
	}
	public void setWebtoonCover(String webtoonCover) {
		this.webtoonCover = webtoonCover;
	}
	public String getWebtoonGenre() {
		return webtoonGenre;
	}
	public void setWebtoonGenre(String webtoonGenre) {
		this.webtoonGenre = webtoonGenre;
	}
	public int getwLentPrice() {
		return wLentPrice;
	}
	public void setwLentPrice(int wLentPrice) {
		this.wLentPrice = wLentPrice;
	}
	public int getwBuyPrice() {
		return wBuyPrice;
	}
	public void setwBuyPrice(int wBuyPrice) {
		this.wBuyPrice = wBuyPrice;
	}
	public Date getwOpenDate() {
		return wOpenDate;
	}
	public void setwOpenDate(Date wOpenDate) {
		this.wOpenDate = wOpenDate;
	}
	public Date getwUpdateDate() {
		return wUpdateDate;
	}
	public void setwUpdateDate(Date wUpdateDate) {
		this.wUpdateDate = wUpdateDate;
	}
	public char getwEndYn() {
		return wEndYn;
	}
	public void setwEndYn(char wEndYn) {
		this.wEndYn = wEndYn;
	}
	public int getwViewCount() {
		return wViewCount;
	}
	public void setwViewCount(int wViewCount) {
		this.wViewCount = wViewCount;
	}
	@Override
	public String toString() {
		return "웹툰정보 [번호=" + webtoonNo + ", 제목=" + webtoonTitle + ", 글작가=" + webtoonWriter
				+ ", 그림작가=" + webtoonPainter + ", 표지경로=" + webtoonCover + ", 장르="
				+ webtoonGenre + ", 대여가격=" + wLentPrice + ", 구매가격=" + wBuyPrice + ", 오픈날짜=" + wOpenDate
				+ ", 업데이트날짜=" + wUpdateDate + ", 완결여부=" + wEndYn + ", 조회수=" + wViewCount + "]";
	}

	
}
