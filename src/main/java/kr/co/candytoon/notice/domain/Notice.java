package kr.co.candytoon.notice.domain;

import java.sql.Timestamp;

public class Notice {
	private int noticeNo;
	private String noticeSubject; 
	private String noticeContent; 
	private String noticeWriter; 
	private Timestamp noticeDate;
	private Timestamp noticeUpdate;
	private String noticeFileName;
	private String noticeFileRename;
	private String noticeFilePath;
	private long noticeFileLength;
	
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
	
	public Timestamp getNoticeUpdate() {
		return noticeUpdate;
	}
	public void setNoticeUpdate(Timestamp noticeUpdate) {
		this.noticeUpdate = noticeUpdate;
	}
	public String getNoticeFileName() {
		return noticeFileName;
	}
	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}
	
	
	public String getNoticeFileRename() {
		return noticeFileRename;
	}
	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}
	public String getNoticeFilePath() {
		return noticeFilePath;
	}
	public void setNoticeFilePath(String noticeFilePath) {
		this.noticeFilePath = noticeFilePath;
	}
	public long getNoticeFileLength() {
		return noticeFileLength;
	}
	public void setNoticeFileLength(long noticeFileLength) {
		this.noticeFileLength = noticeFileLength;
	}
	
	
	@Override
	public String toString() {
		return "공지 [번호=" + noticeNo + ", 제목=" + noticeSubject + ", 내용=" + noticeContent
				+ ", 작성자=" + noticeWriter + ", 등록일=" + noticeDate + ", 수정일=" + noticeUpdate+", 파일명=" + noticeFileName
				+ ", 파일리네임=" + noticeFileRename + ", 파일경로=" + noticeFilePath + ", 파일크기=" + noticeFileLength + "]";
	}
	
	
	
	
}
