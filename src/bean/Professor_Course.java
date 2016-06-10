package bean;

public class Professor_Course
{
	int semester;
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

	public void setSemester(int semester)
	{
		this.semester = semester;
	}

	public int getPrice()
	{
		return price;
	}

	public int getSemester()
	{
		return semester;
	}
}
