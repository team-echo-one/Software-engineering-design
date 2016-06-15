package utils;


import javax.swing.JTextArea;
import javax.swing.JTextField;


/**   
 * Description:含有应用程序全局变量，全为静态变量
 *<br/> 
 * <p>
 * CodeTime:2015年7月27日下午6:56:25
 * @author  pxh
 */   
public class Var		
{
	public static String Host="imadministrition.xicp.net";		//连接的主机地址和端口
	public static int Port;
	
	public static boolean isForceShutDown = false;
	
	public static JTextArea tarea_info=null;		//用于在其他线程进行更改的组件的引用，JTextArea主要用于调试
	public static JTextField tf_Count=null;		//显示在线用户人数
	
	private static int random=0;
	
	public static String rootPath="D:\\serverfolder\\";
	
	//public static int currentSemester = 6;
	
	synchronized public static String getRandomnum()
	{
		String string=Integer.toString(random);
		for(int i=string.length();i<3;i++)
		{
			string="0"+string;
		}
		if(++random>999)
		{
			random=0;
		}
		return string;
	}
}
