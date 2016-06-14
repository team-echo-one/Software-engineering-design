package handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import bean.Message;
import bean.Password;
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
			String hql = "from Message where releaseDate<=:endDate and releaseDate>=:startDate";
			Query query = session.createQuery(hql);
			query.setDate("startDate", lastDate);
			query.setDate("endDate", nowDate);
			for (Object object : query.list())
			{
				Message message = (Message) object;
				result.add(message);
			}
			password.setLastLogin(nowDate);
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}
		return result;
	}

	public static void main(String[] args)
	{
		try
		{
			Timer timer = new Timer();
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					System.out.println("timer");
				}
			}, 0,3000);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
