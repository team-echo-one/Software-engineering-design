package jbean;

public class CourseOnlyName
{
	long id;
	String name;
	
	public CourseOnlyName()
	{
		// TODO Auto-generated constructor stub
	}
	
	public CourseOnlyName(long id, String name)
	{
		super();
		this.id = id;
		this.name = name;
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
	public String getName()
	{
		return name;
	}
}
