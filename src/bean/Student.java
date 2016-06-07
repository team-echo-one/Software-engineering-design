package bean;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

public class Student
{
	long id;
	String name;
	Date birth;
	String ssN;
	String status;
	Date graduationDate;
	Map<Course, Student_Course> courses = new HashMap<>();
	Financial financial;

	public static void main(String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		/*
		 * Professor professor = new Professor();
		 * professor.setId(System.currentTimeMillis());
		 * professor.setName("adf"); professor.setFaculty("123");
		 * session.save(professor);
		 * 
		 * Course course = (Course) session.get(Course.class, 1L);
		 * Professor_Course pCourse = new Professor_Course();
		 * pCourse.setBegin(1); pCourse.setEnd(4); pCourse.setPrice(123);
		 * pCourse.setSemester(1); pCourse.setDay(1);
		 * professor.getTeach().put(course, pCourse);
		 */
		Course course = (Course) session.get(Course.class, 1L);
		// Student student = (Student) session.get(Student.class, (long)1);
		for (Map.Entry<Student, Student_Course> entry : course.students.entrySet())
		{
			System.out.println("key:" + entry.getKey().name + "\nvalue:" + entry.getValue().grade);
		}

		tx.commit();
		HibernateUtil.closeSessionFactory();
		// init();
	}

	private static void init()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Student student = new Student();
		Calendar calendar = Calendar.getInstance();
		calendar.set(1990, 1, 2);
		student.setBirth(calendar.getTime());
		student.setName("zyz");
		student.setSsN("123123123");
		calendar.set(2010, 1, 2);
		student.setGraduationDate(calendar.getTime());
		student.setStatus("无");
		session.save(student);

		student = new Student();
		student.setName("zdf");
		student.setSsN("123123123");
		student.setStatus("无");
		session.save(student);

		student = new Student();
		student.setName("asd");
		student.setSsN("123123123");
		student.setStatus("无");
		session.save(student);

		student = new Student();
		student.setName("qwe");
		student.setSsN("123123123");
		student.setStatus("无");
		session.save(student);

		student = new Student();
		student.setName("阿斯蒂芬");
		student.setSsN("123123123");
		student.setStatus("无");
		session.save(student);

		Course course = new Course();
		course.setName("chinese");
		session.save(course);

		course = new Course();
		course.setName("pyshic");
		session.save(course);

		course = new Course();
		course.setName("math");
		session.save(course);

		course = new Course();
		course.setName("english");
		session.save(course);

		Student student1 = (Student) session.get(Student.class, 3L);
		for (long i = 1; i < 5; i++)
		{
			Student_Course student_Course = new Student_Course();
			student_Course.setGrade("A+");
			Course course1 = (Course) session.get(Course.class, i);
			student1.getCourses().put(course1, student_Course);
		}

		tx.commit();
		HibernateUtil.closeSessionFactory();
	}

	public Student()
	{
	}

	public void setFinancial(Financial financial)
	{
		this.financial = financial;
	}

	public Financial getFinancial()
	{
		return financial;
	}

	public void setCourses(Map<Course, Student_Course> courses)
	{
		this.courses = courses;
	}

	public Map<Course, Student_Course> getCourses()
	{
		return courses;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}

	public Date getBirth()
	{
		return birth;
	}

	public void setGraduationDate(Date graduationDate)
	{
		this.graduationDate = graduationDate;
	}

	public Date getGraduationDate()
	{
		return graduationDate;
	}

	public void setSsN(String ssN)
	{
		this.ssN = ssN;
	}

	public String getSsN()
	{
		return ssN;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getStatus()
	{
		return status;
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
}
