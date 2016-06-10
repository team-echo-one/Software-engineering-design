package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Password;
import bean.Student;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.JStudent;
import jbean.Result;
import utils.HibernateUtil;

public class AddStudentResponse extends ServerResponse
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
		JStudent data = gson.fromJson(s, JStudent.class);
		
		Result result = add(data)?Result.successInstance():Result.failedInstance();
		String content = gson.toJson(result);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}
	
	private static boolean add(JStudent jStudent)
	{
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			Student student = new Student();
			student.setFromJStudent(jStudent);
			student.setId(System.currentTimeMillis());
			Password password = new Password();
			password.setAuthority(1);
			password.setPassword(String.valueOf(student.getId()%10000));
			student.setPassword(password);
			session.save(student);
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
