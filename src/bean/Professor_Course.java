package bean;

import org.hibernate.Session;

public class Professor_Course
{
	int capacity;
	int price;
	int day;
	int begin;
	int end;

	public Professor_Course()
	{
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setBegin(int begin)
	{
		this.begin = begin;
	}

	public int getBegin()
	{
		return begin;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public int getDay()
	{
		return day;
	}

	public int getEnd()
	{
		return end;
	}

	public void setEnd(int end)
	{
		this.end = end;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getPrice()
	{
		return price;
	}
	
	public static void addCourseOffering(long id,Professor professor, Session session)
	{
		Course course = (Course) session.get(Course.class, id);
		Professor_Course pCourse = new Professor_Course();
		pCourse.setBegin(0);
		pCourse.setCapacity(0);
		pCourse.setDay(0);
		pCourse.setEnd(0);
		pCourse.setPrice(0);
		professor.getTeach().put(course, pCourse);
	}
}
