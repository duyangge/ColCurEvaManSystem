package cn.jx.pxc.colcurevamansystem.utils;

import java.util.Collections;
import java.util.List;
/**
 *<p> Title:  ListPageUtil.java</p>
 *<p> Description:  list分页工具类</p>
 * @package   cn.jx.pxc.otsystem.utils
 * @author    黄信胜
 * @date      209年6月1日上午9:34:59
 * @version 版本号
 */
public class ListPageUtil<T> {
	
	private List<T> data;//根据条件返回的list集合
	  
	 /** 上一页 */
     private int lastPage;
  
     /** 当前页 */
     private int currentPage;
  
     /** 下一页 */
     private int nextPage;

     /** 每页条数 */
      private int pageSize;
 
      /** 总页数 */
     private int totalPage;
 
     /** 总数据条数 */
     private int totalCount;
 
     public ListPageUtil(List<T> data,int currentPage,int pageSize) {
          if (data == null || data.isEmpty()) {
              throw new IllegalArgumentException("data must be not empty!");
          }
 
          this.data = data;
          this.pageSize = pageSize;
          this.currentPage = currentPage;
          this.totalCount = data.size();
          this.totalPage = (totalCount + pageSize - 1) / pageSize;
          this.lastPage = currentPage-1>1? currentPage-1:1;
          this.nextPage = currentPage>=totalPage? totalPage: currentPage + 1;
 
    }
  
   public int getPageSize() {
          return pageSize;
    }
	  
  public List<T> getData() {
	  
      int fromIndex = (currentPage - 1) * pageSize;
      
      if (fromIndex >= data.size()) {
          return Collections.emptyList();//空数组
      }
	  if(fromIndex<0){
	      return Collections.emptyList();//空数组
	     }
	         int toIndex = currentPage * pageSize;
	         if (toIndex >= data.size()) {
	             toIndex = data.size();
	          }
	          return data.subList(fromIndex, toIndex);
	}
  
      public int getLastPage() {
          return lastPage;
      }
 
     public int getCurrentPage() {
          return currentPage;
      }

      public int getNextPage() {
          return nextPage;
      }
  
      public int getTotalPage() {
          return totalPage;
     }

     public int getTotalCount() {
        return totalCount;
     }
}
