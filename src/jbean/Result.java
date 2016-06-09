package jbean;

public class Result
{
	String info;
	
	public static Result successInstance()
	{
		return new Result("success");
	}
	
	public static Result failedInstance()
	{
		return new Result("failed");
	}
	
	public Result()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Result(String info)
	{
		this.info = info;
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
