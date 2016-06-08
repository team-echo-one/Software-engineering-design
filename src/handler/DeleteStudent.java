package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Student;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.VariousId;
import jbean.Result;
import utils.HibernateUtil;

public class DeleteStudent extends ServerResponse
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
		VariousId data = gson.fromJson(s, VariousId.class);
		
		Result result = delete(data)?Result.successInstance():Result.failedInstance();
		String content = gson.toJson(result);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}
	
	private static boolean delete(VariousId jsi)
	{
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			Student stu = (Student) session.get(Student.class, jsi.getId());
			session.delete(stu);
			tx.commit();
		} catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}
}
