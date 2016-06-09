package jbean;

public class JProfessor
{
	long id;
	String name;
	String birthday;
	String SSN;
	String status;
	String department;
	
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	public String getBirthday()
	{
		return birthday;
	}
	public void setDepartment(String department)
	{
		this.department = department;
	}
	public String getDepartment()
	{
		return department;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getId()
	{
		return id;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSSN(String sSN)
	{
		SSN = sSN;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getName()
	{
		return name;
	}
	public String getSSN()
	{
		return SSN;
	}
	public String getStatus()
	{
		return status;
	}
}
