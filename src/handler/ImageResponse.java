package handler;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;

public class ImageResponse extends ServerResponse
{
	public static void excute(FullHttpRequest request, String imageName, ChannelHandlerContext ctx)
	{
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
		File file = new File(imageName);
		long length = file.length();
		if (HttpHeaders.isKeepAlive(request))
		{
			response.headers().set(CONNECTION, Values.KEEP_ALIVE);
		}
		HttpHeaders.setContentLength(response, length);
		setContentTypeHeader(response, file);
		try
		{
			FileInputStream fis = new FileInputStream(file);
			byte[] buff = new byte[(int) length];
			fis.read(buff);
			fis.close();
			response.content().writeBytes(buff);
			ctx.writeAndFlush(response);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
