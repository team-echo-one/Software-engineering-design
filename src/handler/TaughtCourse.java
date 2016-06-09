package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Course;
import bean.Professor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.CourseOnlyName;
import jbean.VariousId;
import utils.HibernateUtil;

public class TaughtCourse extends ServerResponse
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
		
		String content = gson.toJson(getCourseList(data));
		
		FullHttpResponse response = createResponse(content, request);
		
		ctx.writeAndFlush(response);
	}
	
	private static List<CourseOnlyName> getCourseList(VariousId vi)
	{
		List<CourseOnlyName> result = new ArrayList<>();
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			Professor professor = (Professor) session.get(Professor.class, vi.getId());
			
			for(Course course:professor.getTeach().keySet())
			{
				result.add(new CourseOnlyName(course.getId(),course.getName()));
			}
			
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
