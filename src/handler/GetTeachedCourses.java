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
import bean.Professor;
import bean.Professor_Course;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.JCourse;
import jbean.VariousId;
import utils.Configure;
import utils.HibernateUtil;

public class GetTeachedCourses extends ServerResponse
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
		VariousId data = gson.fromJson(s, VariousId.class);
		printRequest(s);
		String content = gson.toJson(getCourses(data));
		printContent(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static List<JCourse> getCourses(VariousId vid)
	{
		List<JCourse> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Professor professor = (Professor) session.get(Professor.class, vid.getId());

			JCourse jCourse = new JCourse();
			for (Map.Entry<Course, Professor_Course> e : professor.getTeach().entrySet())
			{
				Course course = e.getKey();
				jCourse = new JCourse();
				if(course.getSemester()!=Configure.getSemester())
				{
					continue;
				}
				jCourse.setTeacher(professor.getName());
				jCourse.setCapacity(e.getValue().getCapacity());
				jCourse.setBegin(e.getValue().getBegin());
				jCourse.setDay(e.getValue().getDay());
				jCourse.setEnd(e.getValue().getEnd());
				jCourse.setId(course.getId());
				jCourse.setName(course.getName());
				result.add(jCourse);
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
