package bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jbean.JStudent;
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
	Password password;

	public static void main(String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		init(session);
		initialCourseOffering(session);

		tx.commit();
		HibernateUtil.closeSessionFactory();
	}

	private static void initialCourseOffering(Session session)
	{
		Registrar registrar = new Registrar();
		registrar.setId(233);
		Password password = new Password();
		password.setPassword("233");
		password.setAuthority(2);
		registrar.setName("registrar");
		registrar.setPassword(password);
		session.save(password);
		session.save(registrar);
	}

	private static void init(Session session)
	{
		Course course = new Course();
		course.setName("data structrue");
		course.setSemester(1);
		session.save(course);

		course = new Course();
		course.setName("advanced mathematics");
		course.setSemester(1);
		session.save(course);

		course = new Course();
		course.setName("linear algebra");
		course.setSemester(1);
		session.save(course);

		course = new Course();
		course.setName("database principle");
		course.setSemester(2);
		session.save(course);

		course = new Course();
		course.setName("database principle");
		course.setSemester(2);
		session.save(course);

		course = new Course();
		course.setName("Network");
		course.setSemester(2);
		session.save(course);

		course = new Course();
		course.setName("operating system");
		course.setSemester(2);
		session.save(course);

		course = new Course();
		course.setName("software engineering");
		course.setSemester(2);
		session.save(course);

		Student student = addStudent("YiZheng Zhang", 123, "123", session);
		
		Professor professor = Professor.addProfessor(456, "God Zhang", "456", session);
		Professor_Course.addCourseOffering(1, professor, session);

		for (long i = 1; i < 9; i++)
		{
			Student_Course student_Course = new Student_Course();
			student_Course.setGrade("A+");
			student_Course.setPid(professor.getId());
			Course course1 = (Course) session.get(Course.class, i);
			student.getCourses().put(course1, student_Course);
		}

		student = new Student();
		student.setName("ZhengYi Zhang");
		student.setSsN("321967411354");
		student.setStatus("无");
		session.save(student);

		for (long i = 1; i < 9; i++)
		{
			Student_Course student_Course = new Student_Course();
			student_Course.setGrade("A+");
			Course course1 = (Course) session.get(Course.class, i);
			student.getCourses().put(course1, student_Course);
		}
	}

	public Student()
	{
	}

	public void setPassword(Password password)
	{
		this.password = password;
	}

	public Password getPassword()
	{
		return password;
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

	public void setFromJStudent(JStudent student)
	{
		try
		{
			setName(student.getName());
			setSsN(student.getSSN());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(student.getBirthday());
			setBirth(date);
			date = sdf.parse(student.getGraduationDate());
			setGraduationDate(date);
			setStatus(student.getStatus());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static JStudent toJStudent(Student student)
	{
		JStudent jStudent = new JStudent();
		jStudent.setId(student.getId());
		jStudent.setName(student.getName());
		jStudent.setSSN(student.getSsN());
		jStudent.setBirthday(student.getBirth().toString());
		jStudent.setGraduationDate(student.getGraduationDate().toString());
		jStudent.setStatus(student.getStatus());
		return jStudent;
	}

	public static List<JStudent> toJStudentList(List<Student> students)
	{
		List<JStudent> list = new LinkedList<>();
		for (Student student : students)
		{
			list.add(toJStudent(student));
		}
		return list;
	}

	public static Student addStudent(String name, long id, String password, Session session)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(1995, 1, 2);
		Date birth = calendar.getTime();
		calendar.set(2017, 7, 1);
		Date graduation = calendar.getTime();
		return addStudent(name, id, password, "54v6a546d8", birth, graduation, session);
	}

	public static Student addStudent(String name, long id, String password, String SSN, Date birth, Date graduation,
			Session session)
	{
		Student student = new Student();
		student.setBirth(birth);
		student.setId(id);
		Password psw = new Password();
		psw.setPassword(password);
		psw.setAuthority(1);
		student.setPassword(psw);
		student.setName(name);
		student.setSsN(SSN);
		student.setGraduationDate(graduation);
		student.setStatus("无");
		session.save(student);
		return student;
	}
}
