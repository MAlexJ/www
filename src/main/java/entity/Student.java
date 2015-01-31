package entity;


import java.sql.Date;

public class Student extends AbstractModelIdName{

	private static final long serialVersionUID = -5638726927853322152L;
	private String first_name;
	private	String group;
	private Date date;
	
	public Student(){
		super();
	}
	
	public Student(Integer id, String name, String first_name, String group, Date date){
		super(id, name);
		this.first_name = first_name;
		this.group = group;
		this.date = date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
