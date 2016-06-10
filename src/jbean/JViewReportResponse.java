package jbean;

public class JViewReportResponse
{
	long courseId;
	String name;
	String teacher;

	public JViewReportResponse(long courseId, String name, String teacher)
	{
		this.courseId = courseId;
		this.name = name;
		this.teacher = teacher;
	}

	public void setCourseId(long courseId)
	{
		this.courseId = courseId;
	}

	public long getCourseId()
	{
		return courseId;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
	}

	public String getTeacher()
	{
		return teacher;
	}
}
