package jbean;

public class LoginDeny
{
	String info;
	String error;
	
	public LoginDeny(String error)
	{
		info = "error";
		this.error = error;
	}
	
	public static LoginDeny newNotMatchInstance()
	{
		return new LoginDeny("password not match");
	}
	
	public static LoginDeny newNotExist()
	{
		return new LoginDeny("username not exist");
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getError()
	{
		return error;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public String getInfo()
	{
		return info;
	}
}
