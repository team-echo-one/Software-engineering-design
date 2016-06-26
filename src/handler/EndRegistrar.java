package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Course;
import bean.Message;
import bean.Password;
import bean.Student;
import bean.Student_Course;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.Login;
import jbean.Result;
import utils.HibernateUtil;
import utils.Var;

public class EndRegistrar extends ServerResponse
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
		Login data = gson.fromJson(s, Login.class);

		
		Result result = terminateAndCheck(data) ? Result.successInstance() : Result.failedInstance();
		String content = gson.toJson(result);
		printContent(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static boolean terminateAndCheck(Login login)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		ArrayList<Course> courses = new ArrayList<>();
		if(!loginResult(login.getId(), login.getPassword(), session))
		{
			return false;
		}
		try
		{
			String hql = "from Course";
			Query query = session.createQuery(hql);
			for (Object o : query.list())
			{
				Course course = (Course) o;
				if (course.getStudents().size() <= 3)
				{
					courses.add(course);
					for (Map.Entry<Student, Student_Course> entry : course.getStudents().entrySet())
					{
						entry.getKey().getCourses().remove(course);
					}
				}
			}
			String title = "Notification : Course has been canceled";
			StringBuilder content = new StringBuilder("");
			content.append("Course Id").append("\t").append("Course Name\n");
			for (Course course : courses)
			{
				content.append(course.getId()).append("\t").append(course.getName()).append("\n");
			}
			content.append("because of the number of selected students is too little,these courses has been canceled");
			Message message = new Message();
			message.setContent(content.toString());
			message.setTitle(title);
			message.setReleaseDate(new Date());
			session.save(message);
			tx.commit();
			Var.isForceShutDown = true;
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		return true;
	}

	private static boolean loginResult(long id, String password, Session session)
	{
		boolean result = false;
		try
		{
			Password pswd = (Password) session.get(Password.class, id);
			if (pswd == null)
			{
				result = false;
			} else
			{
				if (password.equals(pswd.getPassword()))
				{
					int authority = pswd.getAuthority();
					if (authority == 2)
						result = true;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
