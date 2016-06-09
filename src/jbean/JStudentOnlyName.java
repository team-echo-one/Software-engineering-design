package jbean;

public class JStudentOnlyName
{
	long id;
	String name;

	public JStudentOnlyName(long id, String name)
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
