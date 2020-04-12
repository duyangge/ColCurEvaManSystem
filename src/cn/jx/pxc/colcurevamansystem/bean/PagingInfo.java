package cn.jx.pxc.colcurevamansystem.bean;
/**
 *<p> Title:  PagingInfo.java</p>
 *<p> Description:  分页</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月8日下午2:19:40
 * @version 版本号
 */
@SuppressWarnings("all")
public class PagingInfo {
	private int pageNum;
	private int pageSize;
	private int total;
	private int pages;       //总页数
	private int start;
	private int end;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "PagingInfo [pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", pages=" + pages
				+ ", start=" + start + ", end=" + end + "]";
	}
}
