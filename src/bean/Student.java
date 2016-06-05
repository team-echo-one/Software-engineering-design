package bean;

public class Student
{
	long id;
	String name;
	
	public Student()
	{
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId()
	{
		return id;
	}
}
