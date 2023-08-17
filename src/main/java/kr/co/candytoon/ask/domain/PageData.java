package kr.co.candytoon.ask.domain;

import java.util.List;

public class PageData {
	
	private List<Ask> aList;
	private String pageNavi;
	
	public PageData() {}

	public PageData(List<Ask> aList, String pageNavi) {
		super();
		this.aList = aList;
		this.pageNavi = pageNavi;
	}

	public List<Ask> getaList() {
		return aList;
	}

	public void setaList(List<Ask> aList) {
		this.aList = aList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	@Override
	public String toString() {
		return "PageData [aList=" + aList + ", pageNavi=" + pageNavi + "]";
	}

	
	
	
}
