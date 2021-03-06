package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.google.gson.Gson;

import bean.Course;
import bean.Professor;
import bean.Student;
import bean.Student_Course;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.JViewReport;
import jbean.JViewReportResponse;
import jbean.Result;
import utils.HibernateUtil;

public class ViewReport extends ServerResponse
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
		System.out.println(s);
		JViewReport data = gson.fromJson(s, JViewReport.class);

		String content = gson.toJson(getViewReport(data));
		System.out.println(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static Object getViewReport(JViewReport request)
	{
		List<JViewReportResponse> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try
		{
			session.beginTransaction();
			Student student = (Student) session.get(Student.class, request.getId());
			if (student == null)
			{
				return new Result("student information does not exist");
			}
			System.out.println(student.getCourses());
			if (student.getCourses() == null || student.getCourses().isEmpty())
			{
				return new Result("this student doesn't have history course");
			}
			for (Map.Entry<Course, Student_Course> entry : student.getCourses().entrySet())
			{
				if (entry.getKey().getSemester() != request.getSemester())
				{
					continue;
				}
				long pid = entry.getValue().getPid();
				Professor professor = (Professor) session.get(Professor.class, pid);
				result.add(new JViewReportResponse(entry.getKey().getId(),
						entry.getKey().getName(), professor.getName(),entry.getValue().getGrade()));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new ArrayList<>();
		} finally
		{
			session.close();
		}
		return result;
	}
}
