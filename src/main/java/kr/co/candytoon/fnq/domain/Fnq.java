package kr.co.candytoon.fnq.domain;

import java.sql.Timestamp;

public class Fnq {
	private int fnqNo;
	private String fnqCategory; 
	private String fnqSubject; 
	private String fnqContent; 
	private String fnqWriter; 
	private Timestamp fnqDate;
	private String fnqFileName;
	private String fnqFilePath;
	private long fnqFileLength;
	
	public int getFnqNo() {
		return fnqNo;
	}
	public void setFnqNo(int fnqNo) {
		this.fnqNo = fnqNo;
	}
	public String getFnqCategory() {
		return fnqCategory;
	}
	public void setFnqCategory(String fnqCategory) {
		this.fnqCategory = fnqCategory;
	}
	public String getFnqSubject() {
		return fnqSubject;
	}
	public void setFnqSubject(String fnqSubject) {
		this.fnqSubject = fnqSubject;
	}
	public String getFnqContent() {
		return fnqContent;
	}
	public void setFnqContent(String fnqContent) {
		this.fnqContent = fnqContent;
	}
	public String getFnqWriter() {
		return fnqWriter;
	}
	public void setFnqWriter(String fnqWriter) {
		this.fnqWriter = fnqWriter;
	}
	public Timestamp getFnqDate() {
		return fnqDate;
	}
	public void setFnqDate(Timestamp fnqDate) {
		this.fnqDate = fnqDate;
	}
	
	public String getFnqFileName() {
		return fnqFileName;
	}
	public void setFnqFileName(String fnqFileName) {
		this.fnqFileName = fnqFileName;
	}
	public String getFnqFilePath() {
		return fnqFilePath;
	}
	public void setFnqFilePath(String fnqFilePath) {
		this.fnqFilePath = fnqFilePath;
	}
	public long getFnqFileLength() {
		return fnqFileLength;
	}
	public void setFnqFileLength(long fnqFileLength) {
		this.fnqFileLength = fnqFileLength;
	}
	
	@Override
	public String toString() {
		return "FnQ [FnQ번호=" + fnqNo + ", 카테고리=" + fnqCategory + ", 제목=" + fnqSubject + ", 내용="
				+ fnqContent + ", 작성자=" + fnqWriter + ", 등록일=" + fnqDate + ", 파일명=" + fnqFileName
				+ ", 파일경로=" + fnqFilePath + ", 파일크기=" + fnqFileLength + "]";
	}
	
	
}
