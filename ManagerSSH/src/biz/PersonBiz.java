package biz;

import java.util.List;

import entity.Person;

public interface PersonBiz {
	public Person checkLogin(String name,String pwd);
	
	public boolean updatePerson(Person p);
	
	public <T> boolean deletePerson(Class<T> cla,int id);
	
	public Person queryPersonById(int id);
	
	public Long getPersonCount();
	
	public List<Person> getPagePerson(int page,int size);
	
	/**
	 * 
	 * ÃÌº”’ ∫≈
	 * @param p
	 * @return
	 */
	public boolean addPerson(Person p);

}
