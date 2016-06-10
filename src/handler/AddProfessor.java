package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Password;
import bean.Professor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.JProfessor;
import jbean.Result;
import utils.HibernateUtil;

public class AddProfessor extends ServerResponse
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
		JProfessor data = gson.fromJson(s, JProfessor.class);
		
		Result result = add(data)?Result.successInstance():Result.failedInstance();
		String content = gson.toJson(result);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}
	
	private static boolean add(JProfessor jProfessor)
	{
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			Professor professor = new Professor();
			professor.setFromJProfessor(jProfessor);
			professor.setId(System.currentTimeMillis());
			Password password = new Password();
			password.setPassword(String.valueOf(professor.getId()%10000));
			password.setAuthority(0);
			professor.setPassword(password);
			session.save(professor);
			session.save(password);
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
