package jbean;

public class JViewReportResponse
{
	long courseId;
	String name;
	String teacher;
	String grade;

	public JViewReportResponse(long courseId, String name, String teacher, String grade)
	{
		this.courseId = courseId;
		this.name = name;
		this.teacher = teacher;
		this.grade = grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public String getGrade()
	{
		return grade;
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
