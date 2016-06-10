package bean;

public class Registrar
{
	long id;
	String name;
	Password password;

	public Registrar()
	{
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setPassword(Password password)
	{
		this.password = password;
	}

	public Password getPassword()
	{
		return password;
	}
}
