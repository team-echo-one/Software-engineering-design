package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.Properties;

public class Configure
{
	static private String fileName = "configration.properties";
	static Properties properties;
	static FileInputStream fileInputStream;
	
	static private void initial()
	{
		properties = new Properties();
		try
		{
			fileInputStream = new FileInputStream(fileName);
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	static public String getString(String key)
	{
		initial();
		if (key == null || key.equals("") || key.equals("null"))
		{
			return "";
		}
		String result = "";
		try
		{
			result = properties.getProperty(key);
		} catch (MissingResourceException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	static public int getSemester()
	{
		return Integer.parseInt(getString("semester"));
	}
	
	static public int getCheckPeriod()
	{
		return Integer.parseInt(getString("checkPeriod"));
	}
	
	static public Date getShutDownDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try
		{
			date = sdf.parse(getString("shutdown"));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}
}
