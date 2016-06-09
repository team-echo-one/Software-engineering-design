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
		System.out.println(uri);
		if (uri.equals("/"))
		{
			WebHandler.sendHtml(request, "web/login.html", ctx);
		}
		handlerRequest(uri, ctx);
	}

	private void handlerRequest(String uri, ChannelHandlerContext ctx)
	{
		List<String> param = splitUri(uri);
		String function = param.get(0);
		// System.out.println("function:"+function);
		try
		{
			switch (function)
			{
				case "login":
					LoginResponse.excute(request, ctx);
					break;
				case "getStudents":
					GetStudentResponse.excute(request, ctx);
					break;
				case "updateStudent":
					UpdateStudent.excute(request, ctx);
					break;
				case "deleteStudent":
					DeleteStudent.excute(request, ctx);
					break;
				case "getProfessors":
					GetProfessors.excute(request, ctx);
					break;
				case "updateProfessor":
					UpdateProfessor.excute(request, ctx);
					break;
				case "deleteProfessor":
					DeleteProfessor.excute(request, ctx);
					break;
				case "js":
					WebHandler.sendJs(request, "web" + uri, ctx);
					break;
				case "css":
					WebHandler.sendCss(request, "web" + uri, ctx);
					break;
				case "images":
					ImageResponse.excute(request, "web" + uri, ctx);
					break;
				default:
					sendError(ctx, HttpResponseStatus.NOT_FOUND);
					break;
			}
		} catch (Exception exception)
		{
			System.out.println(exception.toString());
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
