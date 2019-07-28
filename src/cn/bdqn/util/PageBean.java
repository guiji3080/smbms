package cn.bdqn.util;

import java.util.List;
//泛型类
public class PageBean<T> {
	private int pageNo=1;//当前页
	private int pageSize=8;//每页的
	private int totalCount;//总记录数
	private int totalpages;//总页数
	private List<T> pageList;//每页对应的内容集合
	
	
	public int getPageNo() {
		return pageNo;
	}
	//对当前页的判断，不能小于0，并且不能大于总页数
	public void setPageNo(int pageNo) {
		if(pageNo<=0){
			this.pageNo=1;
		}else if(pageNo>totalpages&&totalpages>0){
			this.pageNo=totalpages;
		}else{
			this.pageNo = pageNo;
		}
		
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	//总页数有总记录数来计算,只读
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(totalCount>0){
		this.totalpages=(totalCount%pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;
	/*	if(totalCount%pageSize==0){
			this.totalpages=totalCount/pageSize;
		}else{
			this.totalpages=totalCount/pageSize+1;
		}*/
		}
		
	}
	
	public int getTotalpages() {
		return totalpages;
	}

	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

}
