package com.web.example.domain;

public class Page {

	private int pageNum = 10;// ÿҳ����
	private int currentPage;// ��ǰҳ

	private int count;// ����
	
	private int firstPage;	
	private int lastPage;
	private int previouPage;
	private int nextPage;

	private boolean hasPreviouPage;
	private boolean hasNextPage;
	
	private int startPage;//��ʼҳ��
	private int endPage;//����ҳ��
	
	private String url;//���·��
	
	/**
	 * ��������ֲ�����Ĭ��ÿҳ10��
	 * @param currentPage ��ǰҳ
	 * @param count ����
	 */
	public Page(int currentPage,int count,String url){
		this.currentPage = currentPage;
		this.count = count;
		this.url = url;
	}
	/**
	 * ���������ҳ����
	 * @param currentPage ��ǰҳ
	 * @param pageNum ÿҳ����
	 * @param count ����
	 */
	public Page(int currentPage,int pageNum,int count,String url){
		this.currentPage = currentPage;
		this.pageNum = pageNum;
		this.count = count;
		this.url = url;
	}
	
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
	public int getFirstPage() {
		return 1;//��ҳΪ1
	}

	public int getLastPage() {
		if (this.count % this.pageNum > 0) {
			return this.count / this.pageNum + 1 ;
		}
		return this.count / this.pageNum ;
	}

	public int getPreviouPage() {
		if (this.currentPage > 1) {
			return this.currentPage -1;
		}
		return 1;
	}

	public int getNextPage() {
		if (this.currentPage < getLastPage()) {
			return this.currentPage +1;
		}
		return getLastPage();
	}

	public boolean isHasPreviouPage() {
		if (this.currentPage > 1) {
			return true;
		}
		return false;
	}

	public boolean isHasNextPage() {
		if (this.currentPage < getLastPage()) {
			return true;
		}
		return false;
	}
	
	public int getStartPage() {
		if (getLastPage() < pageNum) {
			return 1;
		}
		if (currentPage <= 4) {
			return 1;
		}
		return currentPage - 4;
	}
	public int getEndPage() {
		if (getLastPage() < pageNum) {
			return getLastPage();
		}
		if ((currentPage + 4) >= getLastPage()) {
			return getLastPage();
		}
		
		return currentPage + 4;
	}
	public String getUrl() {
		return url;
	}
	
}
