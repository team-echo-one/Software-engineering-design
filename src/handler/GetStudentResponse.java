package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Course;
import bean.Student;
import bean.Student_Course;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.JCourseStudent;
import jbean.JStudentOnlyName;
import utils.HibernateUtil;

public class GetStudentResponse extends ServerResponse
{
	public static void excute(FullHttpRequest request, ChannelHandlerContext ctx)
	{
		if (request.getMethod() != HttpMethod.POST)
		{
			sendError(ctx, BAD_REQUEST);
			return;
		}
		Gson gson = new Gson();
		ByteBuf buf = request.content();
		String s = buf.toString(Charset.forName("utf-8"));
		printRequest(s);
		JCourseStudent data = gson.fromJson(s, JCourseStudent.class);

		List<JStudentOnlyName> students = getStudens(data);
		String content = gson.toJson(students);
		printContent(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static List<JStudentOnlyName> getStudens(JCourseStudent cs)
	{
		List<JStudentOnlyName> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Course course = (Course) session.get(Course.class,cs.getCourseId());

			for (Map.Entry<Student, Student_Course> entry : course.getStudents().entrySet())
			{
				result.add(new JStudentOnlyName(entry.getKey().getId(), entry.getKey().getName()));
			}
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}
		return result;
	}
}
