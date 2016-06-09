package bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jbean.JProfessor;

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
	
	public void setFromJProfessor(JProfessor professor)
	{
		try
		{
			setName(professor.getName());
			setSsN(professor.getSSN());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date date = sdf.parse(professor.getBirthday());  
			setBirth(date);
			setStatus(professor.getStatus());
			setFaculty(professor.getDepartment());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static JProfessor toJProfessor(Professor professor)
	{
		JProfessor jProfessor = new JProfessor();
		jProfessor.setId(professor.getId());
		jProfessor.setName(professor.getName());
		jProfessor.setSSN(professor.getSsN());
		jProfessor.setBirthday(professor.getBirth().toString());
		jProfessor.setStatus(professor.getStatus());
		jProfessor.setDepartment(professor.faculty);
		return jProfessor;
	}

	public static List<JProfessor> toJProfessorList(List<Professor> professors)
	{
		List<JProfessor> list = new LinkedList<>();
		for (Professor professor : professors)
		{
			list.add(toJProfessor(professor));
		}
		return list;
	}
}
