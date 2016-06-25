package bean;

import org.hibernate.Session;

public class Student_Course
{
	String grade;
	long pid;

	public Student_Course()
	{
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public long getPid()
	{
		return pid;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}
	
	public static void addCourse(long studentId,long professorId,long courseId,Session session)
	{
		Student_Course student_Course = new Student_Course();
		student_Course.setGrade("A+");
		student_Course.setPid(professorId);
		Course course = (Course) session.get(Course.class, courseId);
		Student student = (Student) session.get(Student.class, studentId);
		student.getCourses().put(course, student_Course);
	}
}
