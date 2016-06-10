package jbean;

public class JTeacherAddCourse
{
	long professorId;
	long courseId;
	int capacity;
	int day;
	int begin;
	int end;

	public void setBegin(int begin)
	{
		this.begin = begin;
	}

	public int getBegin()
	{
		return begin;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public long getCourseId()
	{
		return courseId;
	}

	public int getDay()
	{
		return day;
	}

	public void setCourseId(long courseId)
	{
		this.courseId = courseId;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public void setEnd(int end)
	{
		this.end = end;
	}

	public void setProfessorId(long professorId)
	{
		this.professorId = professorId;
	}

	public int getEnd()
	{
		return end;
	}

	public long getProfessorId()
	{
		return professorId;
	}
}
