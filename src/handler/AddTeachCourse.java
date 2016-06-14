package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;

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
import jbean.JTeacherAddCourse;
import jbean.Result;
import utils.Configure;
import utils.HibernateUtil;

public class AddTeachCourse extends ServerResponse
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
		
		printRequest(s);
		JTeacherAddCourse data = gson.fromJson(s, JTeacherAddCourse.class);
		
		Result result = addTeachCourse(data)?Result.successInstance():Result.failedInstance();
		String content = gson.toJson(result);
		printContent(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}
	
	private static boolean addTeachCourse(JTeacherAddCourse addCourse)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Professor professor = (Professor) session.get(Professor.class, addCourse.getProfessorId());
			Course course = (Course) session.get(Course.class, addCourse.getCourseId());
			Professor_Course pCourse = new Professor_Course();
			pCourse.setBegin(addCourse.getBegin());
			pCourse.setCapacity(addCourse.getCapacity());
			pCourse.setDay(addCourse.getDay());
			pCourse.setEnd(addCourse.getEnd());
			pCourse.setSemester(Configure.getSemester());
			pCourse.setPrice(100);
			professor.getTeach().put(course, pCourse);
			tx.commit();
		} catch (Exception e)
		{
			tx.rollback();
			return false;
		}
		return true;
	}
}
