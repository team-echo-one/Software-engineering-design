package handler;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.LOCATION;
import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.activation.MimetypesFileTypeMap;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.util.CharsetUtil;

/**
 * support some method of create a full Response
 * @author pxh
 *
 */
public class ServerResponse
{
	protected static void setContentTypeHeader(HttpResponse response, File file)
	{
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		response.headers().set(CONTENT_TYPE, mimeTypesMap.getContentType(file.getPath()));
	}
	
	protected static void sendRedirect(ChannelHandlerContext ctx, String newUri)
	{
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
		response.headers().set(LOCATION, newUri);
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	protected static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status)
	{
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status,
				Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	
	protected static FullHttpResponse createResponse(String content, HttpRequest request)
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
}
