package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Message;
import bean.Password;
import bean.Student;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import jbean.VariousId;
import utils.HibernateUtil;

public class GetInform extends ServerResponse
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

		String content = gson.toJson(getInformation(data));
		System.out.println(content);
		FullHttpResponse response = createResponse(content, request);
		ctx.writeAndFlush(response);
	}

	private static List<Message> getInformation(VariousId vid)
	{
		List<Message> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Password password = (Password) session.get(Password.class, vid.getId());
			Date lastDate = password.getLastLogin();
			if(lastDate == null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lastDate =  sdf.parse("1970-05-14");
			}
			Date nowDate = new Date();
			nowDate.setDate(nowDate.getDate()+1);
			String hql = "from Message where releaseDate<=:endDate and releaseDate>=:startDate";
			Query query = session.createQuery(hql);
			query.setDate("startDate", lastDate);
			query.setDate("endDate", nowDate);
			System.out.println(nowDate.toString());
			for (Object object : query.list())
			{
				Message message = (Message) object;
				if(message.getTitle().equals("bill"))
				{
					Message msg = new Message();
					msg.setTitle("Semester's bill");
					Student student = (Student) session.get(Student.class, vid.getId());
					int bill = student.getFinancial().getBill();
					String content = "Your bill amount:$"+bill;
					msg.setContent(content);
					msg.setReleaseDate(new Date());
					result.add(msg);
				}else
				{
					result.add(message);
				}
			}
			//password.setLastLogin(nowDate);
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}
		return result;
	}

	public static void main(String[] args) throws ParseException
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		String hql = "from Message where releaseDate>=:startDate and releaseDate<=:endDate";
		Query query = session.createQuery(hql);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastDate =  sdf.parse("1970-05-14 00:00:00");
		Date nowDate = new Date();
		query.setDate("startDate", lastDate);
		query.setDate("endDate", nowDate);
		System.out.println(nowDate.toString());
		for(Object object : query.list())
		{
			Message message = (Message)object;
			System.out.println(message);
		}
		tx.commit();
	}
}
