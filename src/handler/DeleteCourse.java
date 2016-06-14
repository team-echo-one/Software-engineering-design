package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Course;
import bean.Student;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.JAddCourse;
import jbean.Result;
import utils.HibernateUtil;

public class DeleteCourse extends ServerResponse
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
		JAddCourse data = gson.fromJson(s, JAddCourse.class);
		
		Result result = delete(data)?Result.successInstance():Result.failedInstance();
		String content = gson.toJson(result);
		printContent(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}
	
	private static boolean delete(JAddCourse jcourse)
	{
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			Student student = (Student)session.get(Student.class, jcourse.getId());
			Course course = (Course)session.get(Course.class, jcourse.getCourseId());
			
			student.getCourses().remove(course);
			//session.delete(sc);
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
