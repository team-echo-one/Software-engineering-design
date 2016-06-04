package listener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import utils.Var;
import handler.HttpServerInboundHandler;

public class HttpListenThread extends Thread
{
	//private static final String DEFAULT_URL = "/src/classes";
	EventLoopGroup bossGroup = new NioEventLoopGroup(); // 创建两个Reactor线程组
	EventLoopGroup workerGroup = new NioEventLoopGroup();

	public void run()
	{
		try
		{
			ServerBootstrap Serverbs = new ServerBootstrap();
			Serverbs.group(bossGroup, workerGroup);
			// 设定通道类型，绑定端口，设定通道初始化，添加pipeline
			Serverbs.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				protected void initChannel(SocketChannel ch) throws Exception
				{
					System.out.println("A http request has been catched");
					ch.pipeline().addLast("1", new HttpRequestDecoder());
					ch.pipeline().addLast("2", new HttpObjectAggregator(Integer.MAX_VALUE));
					ch.pipeline().addLast("3", new HttpResponseEncoder());
					ch.pipeline().addLast("4", new ChunkedWriteHandler());
					ch.pipeline().addLast("5", new HttpServerInboundHandler());
				}
			});
			ChannelFuture future = Serverbs.bind(Var.Port);
			future.channel().closeFuture().sync();
		} catch (Exception e1)
		{
			e1.printStackTrace();
		} finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public void close() throws InterruptedException
	{
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
