package kr.co.candytoon.fnq.domain;

public class FnqPageInfo {
	private int currentPage;
	private int totalCount;
	private int recordCountPerPage;
	private int naviCountPerPage;
	private int naviTotalCount;
	private int startNavi;
	private int endNavi;
	
	
	
	public FnqPageInfo() {
		super();
	}

	public FnqPageInfo(int currentPage, int totalCount, int recordCountPerPage, int naviCountPerPage,
			int naviTotalCount, int startNavi, int endNavi) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.recordCountPerPage = recordCountPerPage;
		this.naviCountPerPage = naviCountPerPage;
		this.naviTotalCount = naviTotalCount;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getNaviCountPerPage() {
		return naviCountPerPage;
	}
	public void setNaviCountPerPage(int naviCountPerPage) {
		this.naviCountPerPage = naviCountPerPage;
	}
	public int getNaviTotalCount() {
		return naviTotalCount;
	}
	public void setNaviTotalCount(int naviTotalCount) {
		this.naviTotalCount = naviTotalCount;
	}
	public int getStartNavi() {
		return startNavi;
	}
	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}
	public int getEndNavi() {
		return endNavi;
	}
	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}
	
	@Override
	public String toString() {
		return "FnqPageInfo [currentPage=" + currentPage + ", totalCount=" + totalCount + ", recordCountPerPage="
				+ recordCountPerPage + ", naviCountPerPage=" + naviCountPerPage + ", naviTotalCount=" + naviTotalCount
				+ ", startNavi=" + startNavi + ", endNavi=" + endNavi + "]";
	}
	
	
}
