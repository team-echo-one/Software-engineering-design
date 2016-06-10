package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;

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
import utils.HibernateUtil;

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
			Student student = (Student) session.get(Student.class, id);
			
			if(student == null){
				result = LoginDeny.newNotExist();
			}else {
				if(password.equals(student.getPassword().getPassword()))
				{
					result  = new LoginSuccess(student.getName(),student.getPassword().getAuthority());
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
}
