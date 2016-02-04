package action;

import java.util.List;

import javax.annotation.Resource;

import biz.PersonBiz;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Person;

public class PersonAction extends BaseAction {
	@Resource
	private PersonBiz personBiz;
	private Person person;
	private List<Person> list;
	private int pageSize=2;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Person> getList() {
		return list;
	}
	public void setList(List<Person> list) {
		this.list = list;
	}
	
	public String update(){
		boolean flag=personBiz.updatePerson(person);
		this.setMessage(flag?"修改成功":"修改失败");
		return "mymsg";
	}
	
	public String view(){
		
		if(this.getPage()==0) this.setPage(1);
		
		list=personBiz.getPagePerson(this.getPage(), pageSize);
		Long size=personBiz.getPersonCount();
		
		this.setPageCount(size%pageSize==0?size/pageSize:size/pageSize+1);
		return "manager";
		
		
	}
	
	public String delete(){
		personBiz.deletePerson(Person.class,this.getId());
		return view();
	}
	public String register(){
		boolean flag=personBiz.addPerson(person);
		if(flag==true)
		  return "registerOk";
		else return "registerFalse";
	}
	
	
	
	
	

}
