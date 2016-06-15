package handler;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.stream.ChunkedFile;

public class WebHandler extends ServerResponse
{
	static public void sendHtml(FullHttpRequest request, String fileName, ChannelHandlerContext ctx)
	{
		// System.out.println("fileName:" + fileName);
		File file = new File(fileName);
		if (file.isHidden() || !file.exists())
		{
			sendError(ctx, NOT_FOUND);
			return;
		}
		if (!file.isFile())
		{
			sendError(ctx, FORBIDDEN);
			return;
		}
		RandomAccessFile randomAccessFile = null;
		try
		{
			randomAccessFile = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException fnfe)
		{
			sendError(ctx, NOT_FOUND);
			return;
		}
		long fileLength;
		// long stratSize = getStartSize(request);
		try
		{
			// randomAccessFile.seek(stratSize);
			fileLength = randomAccessFile.length();
			HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
			// setContentLength(response, fileLength - stratSize);
			setContentLength(response, fileLength);
			// System.out.println(fileName+"length:"+fileLength);
			setContentTypeHeader(response, file,"text/html");
			if (isKeepAlive(request))
			{
				response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			}
			ctx.write(response);
			ChannelFuture sendFileFuture;
			/*
			 * sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile,
			 * stratSize, fileLength - stratSize, 50 * 1024),
			 * ctx.newProgressivePromise());
			 */
			sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 50 * 1024),
					ctx.newProgressivePromise());
			ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
			// System.out.println("send completed\n\n\n\n");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void sendCss(FullHttpRequest request, String fileName, ChannelHandlerContext ctx)
	{
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
		File file = new File(fileName);
		long length = file.length();
		if (HttpHeaders.isKeepAlive(request))
		{
			//response.headers().set(CONNECTION, Values.KEEP_ALIVE);
		}
		HttpHeaders.setContentLength(response, length);
		setContentTypeHeader(response, file,"text/css");
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
	
	public static void sendJs(FullHttpRequest request, String fileName, ChannelHandlerContext ctx)
	{
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
		File file = new File(fileName);
		long length = file.length();
		if (HttpHeaders.isKeepAlive(request))
		{
			//response.headers().set(CONNECTION, Values.KEEP_ALIVE);
		}
		HttpHeaders.setContentLength(response, length);
		setContentTypeHeader(response, file,"text/javascript");
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