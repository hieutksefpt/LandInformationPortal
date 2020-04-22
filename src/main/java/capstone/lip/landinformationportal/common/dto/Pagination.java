package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;

public class Pagination implements Serializable{

	private static final long serialVersionUID = 1L;
	private int totalRow;
	private int firstRow;
	private int rowsPerPage;
	private int totalPages;
	private int pageRange;
	private Integer[] pages;
	private int currentPage;
	public Pagination() {
		super();
	}
	public int getTotalRow() {
		return totalRow;
	}
	public Pagination setTotalRow(int totalRow) {
		this.totalRow = totalRow;
		return this;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public Pagination setFirstRow(int firstRow) {
		this.firstRow = firstRow;
		return this;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public Pagination setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
		return this;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public Pagination setTotalPages(int totalPages) {
		this.totalPages = totalPages+1;
		return this;
	}
	public int getPageRange() {
		return pageRange;
	}
	public Pagination setPageRange(int pageRange) {
		this.pageRange = pageRange;
		return this;
	}
	public Integer[] getPages() {
		return pages;
	}
	public Pagination setPages(Integer[] pages) {
		this.pages = pages;
		return this;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public Pagination setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		int pageLength = Math.min(pageRange, totalPages);
		
		Integer[] pages = new Integer[pageLength];
		
		int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pageLength);

		for (int i = 0; i < pageLength; i++) {
			pages[i] = ++firstPage;
		}
		
		setPages(pages);
		return this;
	}

}
