package jbean;

public class JStudent
{
	long id;
	String name;
	String birthday;
	String SSN;
	String status;
	String graduationDate;
	
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	public void setGraduationDate(String graduationDate)
	{
		this.graduationDate = graduationDate;
	}
	public void setId(long id)
	{
		this.id = id;
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
	public String getBirthday()
	{
		return birthday;
	}
	public String getGraduationDate()
	{
		return graduationDate;
	}
	public long getId()
	{
		return id;
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
