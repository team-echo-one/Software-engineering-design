package jbean;

public class JAddCourse
{
	long id;
	long courseId;
	long professorId;

	public void setCourseId(long courseId)
	{
		this.courseId = courseId;
	}
	
	public void setProfessorid(long professorId)
	{
		this.professorId = professorId;
	}
	public long getProfessorid()
	{
		return professorId;
	}

	public long getCourseId()
	{
		return courseId;
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
