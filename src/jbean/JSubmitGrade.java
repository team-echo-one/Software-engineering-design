package jbean;

import java.util.Arrays;

public class JSubmitGrade
{
	long courseId;
	long professorId;
	long[] studentIds;
	String[] studentGrades;

	public void setCourseId(long courseId)
	{
		this.courseId = courseId;
	}

	public long getCourseId()
	{
		return courseId;
	}

	public void setStudentGrades(String[] studentGrades)
	{
		this.studentGrades = studentGrades;
	}

	public String[] getStudentGrades()
	{
		return studentGrades;
	}

	public void setProfessorId(long professorId)
	{
		this.professorId = professorId;
	}

	public long getProfessorId()
	{
		return professorId;
	}

	public void setStudentIds(long[] studentIds)
	{
		this.studentIds = studentIds;
	}

	public long[] getStudentIds()
	{
		return studentIds;
	}

	public JSubmitGrade()
	{
	}

	@Override
	public String toString()
	{
		return "JSubmitGrade [courseId=" + courseId + ", professorId=" + professorId + ", studentIds="
				+ Arrays.toString(studentIds) + ", StudentGrades=" + Arrays.toString(studentGrades) + "]";
	}
}
