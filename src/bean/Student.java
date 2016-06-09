package bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sound.midi.VoiceStatus;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jbean.JStudent;
import jbean.VariousId;
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
	Password password;

	public static void main(String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		// init(session);
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
		// Course course = (Course) session.get(Course.class, 1L);
		// Student student = new Student();
		// student.setName("yyc");
		// student.setId(System.currentTimeMillis());
		/*
		 * for (Map.Entry<Student, Student_Course> entry :
		 * course.students.entrySet()) { System.out.println("key:" +
		 * entry.getKey().name + "\nvalue:" + entry.getValue().grade); }
		 */
		// session.save(student);
		/*
		 * Financial financial = new Financial(); financial.setBill(123);
		 * student.setFinancial(financial); financial.setStudent(student);
		 * session.save(student);
		 */
		Student student = (Student) session.get(Student.class, 1L);
		Password password = new Password();
		password.setPassword("zxcvasdf");
		student.setPassword(password);
		// session.saveOrUpdate(student);*
		// System.out.println(student.name);
		tx.commit();
		HibernateUtil.closeSessionFactory();

	}

	private static void init(Session session)
	{
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
			System.out.println(e);
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
}
