package bean;

import java.util.Date;

public class Password
{
	long id;
	String password;
	int authority;
	Date lastLogin;

	public Password()
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

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	public int getAuthority()
	{
		return authority;
	}

	public void setAuthority(int authority)
	{
		this.authority = authority;
	}

	public void setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	public Date getLastLogin()
	{
		return lastLogin;
	}
}
