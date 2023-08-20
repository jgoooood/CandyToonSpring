package kr.co.candytoon.notice.domain;

import java.sql.Timestamp;

public class Notice {
	private int noticeNo;
	private String noticeSubject; 
	private String noticeContent; 
	private String noticeWriter; 
	private Timestamp noticeDate;
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeSubject() {
		return noticeSubject;
	}
	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	
	public Timestamp getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Timestamp noticeDate) {
		this.noticeDate = noticeDate;
	}
	
	
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeSubject="
				+ noticeSubject + ", noticeContent=" + noticeContent + ", noticeWriter=" + noticeWriter
				+ ", noticeDate=" + noticeDate + "]";
	}
	
	
	
	
}
