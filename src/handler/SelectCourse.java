package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
import jbean.JStudentOnlyName;
import jbean.VariousId;
import utils.HibernateUtil;

public class SelectCourse extends ServerResponse
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
		
		String content = gson.toJson(getStudentList(data));
		
		FullHttpResponse response = createResponse(content, request);
		
		ctx.writeAndFlush(response);
	}
	
	private static List<JStudentOnlyName> getStudentList(VariousId vi)
	{
		List<JStudentOnlyName> result = new ArrayList<>();
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			Course course = (Course) session.get(Course.class, vi.getId());
			
			for(Student student:course.getStudents().keySet())
			{
				result.add(new JStudentOnlyName(student.getId(),student.getName()));
			}
			
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
