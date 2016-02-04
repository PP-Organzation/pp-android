package biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.BaseDao;

import entity.Person;
import biz.PersonBiz;
@Service
public class PersonBizImpl implements PersonBiz {
	@Resource(name="absBaseDao")
	private BaseDao dao;
	

	public Person checkLogin(String name, String pwd) {
		// TODO Auto-generated method stub
		List<Person> list= dao.queryByHQL("from Person where aname=? and apwd=?", Person.class, name,pwd);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}


	public boolean updatePerson(Person p) {
		
		return dao.update(p);
	}


	public <T> boolean deletePerson(Class<T> cla,int id) {
		// TODO Auto-generated method stub
		return dao.delete(cla,id);
	}


	public Person queryPersonById(int id) {
		// TODO Auto-generated method stub
		return dao.get(Person.class, id);
	}


	public Long getPersonCount() {
		return dao.getCount("select count(*) from Person");
	}


	public List<Person> getPagePerson(int page, int size) {
		
		return dao.queryByHQLForPage("from Person", Person.class, page, size);
	}


	public boolean addPerson(Person p) {
		// TODO Auto-generated method stub
		
		return dao.save(p);
	}
	
	

}
