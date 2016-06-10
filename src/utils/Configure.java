package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
}
