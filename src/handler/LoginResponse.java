package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.Login;
import jbean.LoginDeny;
import jbean.LoginSuccess;
import bean.*;
import utils.Configure;
import utils.HibernateUtil;
import utils.Var;

public class LoginResponse extends ServerResponse
{
	public static void excute(FullHttpRequest request, ChannelHandlerContext ctx)
	{
		if(request.getMethod()!=HttpMethod.POST)
		{
			sendError(ctx, BAD_REQUEST);
			return;
		}
		Gson gson = new Gson();
		ByteBuf buf = request.content();
		String s = buf.toString(Charset.forName("utf-8"));
		System.out.println(s);
		Login data = gson.fromJson(s, Login.class);
		Object result = loginResult(data.getId(), data.getPassword());
		FullHttpResponse response;
		String dataString = gson.toJson(result);
		System.out.println(dataString);
		response = createResponse(dataString, request);

		ctx.writeAndFlush(response);
	}

	private static Object loginResult(long id, String password)
	{
		Object result = LoginDeny.newNotExist();
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			/*String hql = "from Password psw where psw.id=:id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setLong("id", id);*/
			Password pswd = (Password) session.get(Password.class, id);
			
			if(pswd == null){
				result = LoginDeny.newNotExist();
			}else {
				if(password.equals(pswd.getPassword()))
				{
					int authority = pswd.getAuthority();
					String name;
					if(authority == 0)
					{
						Professor professor = (Professor)session.get(Professor.class, pswd.getId());
						name = professor.getName();
					}else if(authority == 1)
					{
						Student student = (Student)session.get(Student.class, pswd.getId());
						name = student.getName();
					}else {
						Registrar registrar = (Registrar)session.get(Registrar.class, pswd.getId());
						name = registrar.getName();
					}
					LoginSuccess ls= new LoginSuccess(name, pswd.getAuthority());
					ls.setSemester(Configure.getSemester());
					ls.setEndTime(Configure.getShutDownDate().toString());
					if(Var.isForceShutDown )
						ls.setEndRegistrar("over");
					result  = ls;
				}else
				{
					result = LoginDeny.newNotMatchInstance();
				}
			}
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Password password  = (Password) session.get(Password.class,1465828095364L);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date lastDate =null;
		try
		{
			lastDate = sdf.parse("1970-05-14");
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		password.setLastLogin(lastDate);
		tx.commit();
	}
}
