package jbean;

public class LoginSuccess
{
	String info;
	String id;
	String identity;
	
	public LoginSuccess(String id, int authortiy)
	{
		info = "success";
		this.id = id;
		if(authortiy == 0)
			this.identity = "teacher";
		if(authortiy == 1)
			this.identity = "student";
		if (authortiy == 2)
			this.identity = "registrar";
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	public String getId()
	{
		return id;
	}
	public void setIdentity(String identity)
	{
		this.identity = identity;
	}
	public String getIdentity()
	{
		return identity;
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
