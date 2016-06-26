package jbean;

import utils.Configure;

public class LoginSuccess
{
	String info;
	String name;
	String identity;
	int semester;
	String endTime;
	String endRegistrar;

	public LoginSuccess(String name, int authortiy)
	{
		info = "success";
		this.name = name;
		if (authortiy == 0)
			this.identity = "teacher";
		if (authortiy == 1)
			this.identity = "student";
		if (authortiy == 2)
			this.identity = "registrar";
		semester = Configure.getSemester();
	}
	
	public void setEndRegistrar(String endRegistrar)
	{
		this.endRegistrar = endRegistrar;
	}
	
	public String getEndRegistrar()
	{
		return endRegistrar;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setSemester(int semester)
	{
		this.semester = semester;
	}

	public int getSemester()
	{
		return semester;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
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
