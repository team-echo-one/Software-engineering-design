package jbean;

public class Login
{
	long id;
	String password;
	
	public Login()
	{
	}

	public String getPassword()
	{
		return password;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	public long getId()
	{
		return id;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
