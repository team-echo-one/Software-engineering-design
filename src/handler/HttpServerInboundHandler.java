package handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.util.CharsetUtil;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


public class HttpServerInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest>
{
	private FullHttpRequest request;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception
	{
		this.request = request;
		if (!request.getDecoderResult().isSuccess())
		{
			sendError(ctx, BAD_REQUEST);
			return;
		}
		String uri = request.getUri();
		/*ArrayList<ResData> resDatas = new ArrayList<>();
		if (uri.equals("/"))
		{
			for (int i = 0; i < 20; i++)
			{
				resDatas.add(new ResData("2016/2/12:" + i,
						"内容内容内容，测试内容 content asdf content sadf content title name ajdlfj,asdf"
								+ "adfjalskjgas;dlkjflkasjg",
						"image/" + i + ".png", "titletitle" + i, "name", (1 + i) * 20, i * 30));
			}
			ctx.writeAndFlush(createResponse(new Gson().toJson(resDatas), request));
		}*/
		List<String> param = splitUri(uri);
		handlerRequest(param, ctx);
	}

	private void handlerRequest(List<String> param, ChannelHandlerContext ctx)
	{
		String function = param.get(0);
		System.out.println("function:"+function);
		switch (function)
		{
			case "image":
				break;
			case "file":
				break;
			case "upload":
				break;
			case "issue":
				break;
			case "resource":
				break;
			case "recommend":
				break;
			case "user":
				break;
			
			default:
				break;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		ctx.close();
	}

	public FullHttpResponse createResponse(String content, HttpRequest request)
	{
		FullHttpResponse response = null;
		try
		{
			response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		response.headers().set(CONTENT_TYPE, "text/plain");
		response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
		if (HttpHeaders.isKeepAlive(request))
		{
			response.headers().set(CONNECTION, Values.KEEP_ALIVE);
		}
		return response;
	}

	private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status)
	{
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status,
				Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	

	private List<String> splitUri(String uri)
	{
		String[] strings = uri.substring(1).split("/");
		return Arrays.asList(strings);
	}
}
