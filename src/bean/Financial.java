package bean;

public class Financial
{
	long id;
	private int bill;
	private Student student;
	
	public void setBill(int bill)
	{
		this.bill = bill;
	}
	public int getBill()
	{
		return bill;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getId()
	{
		return id;
	}
	public void setStudent(Student student)
	{
		this.student = student;
	}
	public Student getStudent()
	{
		return student;
	}
}
