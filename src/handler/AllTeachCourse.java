package handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Course;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import jbean.JCourseOnlyName;
import utils.Configure;
import utils.HibernateUtil;

public class AllTeachCourse extends ServerResponse
{
	public static void excute(FullHttpRequest request, ChannelHandlerContext ctx)
	{
		Gson gson = new Gson();
		List<JCourseOnlyName> courses = getCourses();
		String content = gson.toJson(courses);
		System.out.println(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static List<JCourseOnlyName> getCourses()
	{
		List<JCourseOnlyName> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			String hql = "from Course where semester=:sem";
			Query query = session.createQuery(hql);
			query.setInteger("sem", Configure.getSemester());

			for (Object o : query.list())
			{
				Course course = (Course) o;
				System.out.println(course);
				JCourseOnlyName jCourse = new JCourseOnlyName();
				if ( !course.getInfo().isEmpty())
				{
					continue;
				}
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
