package action;

import java.util.Map;

import javax.annotation.Resource;

import biz.PersonBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Person;

public class LoginAction extends ActionSupport{
	@Resource
	private PersonBiz personBiz;
	private String aname;
	private String apwd;
	private String message;
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String execute() throws Exception {
		Person p=personBiz.checkLogin(aname, apwd);
		if(p==null){
			this.setMessage("µÇÂ¼Ê§°Ü£¬ÓÃ»§ÃûÃÜÂë´íÎó");
			return "fail";
		}else{
			if(aname.equals("admin")){
			return "admin";
			}
			ActionContext context=ActionContext.getContext();
			Map session=context.getSession();
			session.put("person", p);
			this.setMessage("µÇÂ½³É¹¦£¬»¶Ó­Äú£º" + p.getAname());
			return "ok";
			
		}
		
		
	}

}
