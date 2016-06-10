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
import bean.Student;
import bean.Student_Course;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.JCourse;
import jbean.VariousId;
import utils.HibernateUtil;
import utils.Configure;

public class GetCourses extends ServerResponse
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

		String content = gson.toJson(getCourses(data));
		System.out.println(content);
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
			Student student = (Student) session.get(Student.class, vid.getId());

			JCourse jCourse = new JCourse();
			for (Map.Entry<Course, Student_Course> e : student.getCourses().entrySet())
			{
				Course course = e.getKey();
				jCourse = new JCourse();
				System.out.println(course);
				Map.Entry<Professor, Professor_Course> entry = course.getInfoBySemester(Configure.getSemester());
				if (entry == null)
				{
					continue;
				}
				jCourse.setTeacher(entry.getKey().getName());
				jCourse.setCapacity(entry.getValue().getCapacity());
				jCourse.setBegin(entry.getValue().getBegin());
				jCourse.setDay(entry.getValue().getDay());
				jCourse.setEnd(entry.getValue().getEnd());
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
