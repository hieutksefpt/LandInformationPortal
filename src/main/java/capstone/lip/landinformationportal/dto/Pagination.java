package capstone.lip.landinformationportal.dto;

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
		this.totalPages = totalPages;
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
		return this;
	}
	
	
}
