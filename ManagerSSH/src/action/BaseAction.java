package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ���Ʋ����
 * 
 * @author Administrator
 * 
 */
public class BaseAction extends ActionSupport {
	private String message;//��Ϣ
	private int page;//ҳ��
	private Long pageCount;//��ҳ��
	private int id;//����ID

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
