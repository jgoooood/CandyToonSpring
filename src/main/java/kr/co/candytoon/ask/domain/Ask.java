package kr.co.candytoon.ask.domain;

import java.sql.Date;

public class Ask {
	private int askNo;
	private String askCategory; 
	private String askSubject; 
	private String askContent; 
	private String askWriter; 
	private Date askDate;
	public int getAskNo() {
		return askNo;
	}
	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}
	public String getAskCategory() {
		return askCategory;
	}
	public void setAskCategory(String askCategory) {
		this.askCategory = askCategory;
	}
	public String getAskSubject() {
		return askSubject;
	}
	public void setAskSubject(String askSubject) {
		this.askSubject = askSubject;
	}
	public String getAskContent() {
		return askContent;
	}
	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}
	public String getAskWriter() {
		return askWriter;
	}
	public void setAskWriter(String askWriter) {
		this.askWriter = askWriter;
	}
	public Date getAskDate() {
		return askDate;
	}
	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}
	
	@Override
	public String toString() {
		return "1:1문의 [번호=" + askNo + ", 카테고리=" + askCategory + ", 제목=" + askSubject + ", 내용="
				+ askContent + ", 작성자=" + askWriter + ", 작성일=" + askDate + "]";
	}
	
	
}
