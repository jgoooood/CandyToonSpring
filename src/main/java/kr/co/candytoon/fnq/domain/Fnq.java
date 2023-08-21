package kr.co.candytoon.fnq.domain;

import java.sql.Timestamp;

public class Fnq {
	private int fnqNo;
	private String fnqCategory; 
	private String fnqSubject; 
	private String fnqContent; 
	private String fnqWriter; 
	private Timestamp fnqDate;
	
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
	
	@Override
	public String toString() {
		return "Fnq [fnqNo=" + fnqNo + ", fnqCategory=" + fnqCategory + ", fnqSubject=" + fnqSubject + ", fnqContent="
				+ fnqContent + ", fnqWriter=" + fnqWriter + ", fnqDate=" + fnqDate + "]";
	}
	
	
}
