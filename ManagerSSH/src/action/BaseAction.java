package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 控制层基类
 * 
 * @author Administrator
 * 
 */
public class BaseAction extends ActionSupport {
	private String message;//消息
	private int page;//页数
	private Long pageCount;//总页数
	private int id;//主键ID

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
