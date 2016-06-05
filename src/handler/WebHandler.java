package handler;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.LOCATION;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import javax.activation.MimetypesFileTypeMap;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

public class WebHandler
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

	protected static void setContentTypeHeader(HttpResponse response, File file, String type)
	{
		//MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		response.headers().set(CONTENT_TYPE, type);
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
