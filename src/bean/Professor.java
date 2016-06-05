package bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Professor
{
	long id;
	String name;
	Date birth;
	String status;
	String ssN;
	String faculty;

	Map<Course, Professor_Course> teach = new HashMap<>();

	public Professor()
	{
	}

	public void setTeach(Map<Course, Professor_Course> teach)
	{
		this.teach = teach;
	}

	public Map<Course, Professor_Course> getTeach()
	{
		return teach;
	}

	public Date getBirth()
	{
		return birth;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}

	public String getFaculty()
	{
		return faculty;
	}

	public void setFaculty(String faculty)
	{
		this.faculty = faculty;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSsN()
	{
		return ssN;
	}

	public void setSsN(String ssN)
	{
		this.ssN = ssN;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
}
