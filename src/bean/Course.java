package bean;

import java.util.HashMap;
import java.util.Map;

public class Course
{
	long id;
	String name;
	Map<Student, Student_Course> students = new HashMap<>();
	Map<Professor, Professor_Course> info = new HashMap<>();

	public Course()
	{
	}
	
	public Map.Entry<Professor, Professor_Course> getInfoBySemester(int semester)
	{
		for (Map.Entry<Professor, Professor_Course> entry : info.entrySet())
		{
			if (entry.getValue().getSemester() == semester)
			{
				return entry;
			}
		}
		return null;
	}

	public void setInfo(Map<Professor, Professor_Course> info)
	{
		this.info = info;
	}

	public Map<Professor, Professor_Course> getInfo()
	{
		return info;
	}

	public void setStudents(Map<Student, Student_Course> students)
	{
		this.students = students;
	}

	public Map<Student, Student_Course> getStudents()
	{
		return students;
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

	@Override
	public String toString()
	{
		return "Course [id=" + id + ", name=" + name + ", students=" + students + ", info=" + info + "]";
	}
	
}
