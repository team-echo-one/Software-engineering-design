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
import jbean.JSubmitGrade;
import jbean.Result;
import utils.HibernateUtil;

public class SubmitGrade extends ServerResponse
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
		JSubmitGrade data = gson.fromJson(s, JSubmitGrade.class);
//		System.out.println(data);
		Result result = submitGrade(data)?Result.successInstance():Result.failedInstance();
		String content = gson.toJson(result);
		printContent(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static boolean submitGrade(JSubmitGrade sg)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Course course = (Course)session.get(Course.class, sg.getCourseId());
			for(int i = 0; i<sg.getStudentIds().length; i++)
			{
				Student student = (Student) session.get(Student.class, sg.getStudentIds()[i]);
				student.getCourses().get(course).setGrade(sg.getStudentGrades()[i]);
			}
			tx.commit();
		} catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
