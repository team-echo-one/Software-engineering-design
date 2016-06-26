package jbean;

public class JCourse
{
	long id;
	String name;
	int capacity;
	int day;
	int begin;
	int end;
	String teacher;
	long professorId;

	public void setBegin(int begin)
	{
		this.begin = begin;
	}

	public void setProfessorId(long professorId)
	{
		this.professorId = professorId;
	}

	public long getProfessorId()
	{
		return professorId;
	}

	public int getBegin()
	{
		return begin;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public int getDay()
	{
		return day;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public int getEnd()
	{
		return end;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getTeacher()
	{
		return teacher;
	}

	public void setEnd(int end)
	{
		this.end = end;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
	}
}
