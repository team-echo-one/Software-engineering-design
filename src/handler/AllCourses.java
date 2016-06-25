package handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Course;
import bean.Professor;
import bean.Professor_Course;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import jbean.JCourse;
import utils.HibernateUtil;
import utils.Configure;

public class AllCourses extends ServerResponse
{
	public static void excute(FullHttpRequest request, ChannelHandlerContext ctx)
	{
		Gson gson = new Gson();
		List<JCourse> courses = getCourses();
		String content = gson.toJson(courses);
		System.out.println(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static List<JCourse> getCourses()
	{
		List<JCourse> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			String hql = "from Course";
			Query query = session.createQuery(hql);

			for (Object o : query.list())
			{
				Course course = (Course) o;
				JCourse jCourse = new JCourse();
				/*Map.Entry<Professor, Professor_Course> entry = course.getInfoBySemester(Configure.getSemester());
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
				result.add(jCourse);*/
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
