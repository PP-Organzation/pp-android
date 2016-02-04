package entity;

public class Person {
	private int aid;
	private String aname;
	private String apwd;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
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
	public Person(int aid, String aname, String apwd) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.apwd = apwd;
	}
	public Person() {
		super();
	}
	

}
