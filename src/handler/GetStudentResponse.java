package handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Student;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import utils.HibernateUtil;

public class GetStudentResponse extends ServerResponse
{
	public static void excute(FullHttpRequest request, ChannelHandlerContext ctx)
	{
		Gson gson = new Gson();
		List<Student> students = getStudens();
		String content = gson.toJson(Student.toJStudentList(students));
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}
	
	private static List<Student> getStudens()
	{
		List<Student> result = new ArrayList<>();
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			String hql = "from Student";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			
			result = query.list();

			tx.commit();
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return result;
	}
}
