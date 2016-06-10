package handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Professor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import utils.HibernateUtil;

public class GetProfessors extends ServerResponse
{
	public static void excute(FullHttpRequest request, ChannelHandlerContext ctx)
	{
		Gson gson = new Gson();
		List<Professor> professors = getProfessors();
		String content = gson.toJson(Professor.toJProfessorList(professors));
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}
	
	private static List<Professor> getProfessors()
	{
		List<Professor> result = new ArrayList<>();
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			String hql = "from Professor";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			
			result = query.list();

			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
