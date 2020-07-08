package socketio.nettyio;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author zhangxuepei
 * @since 3.0
 * bossGroup 事件循环，主要处理Accept事件
 * WorkGroup 事件循环，主要处理Write，Reader事件
 * ServerBootstrap 启动加载器
 * NioServerSocketChannel 处理通道
 * ChannelInitializer 链接通道初始化操作
 *
 * 这里Parent和Children主要是处理Parent处理Accept和处Children理Write和Reader事件
 * 管道处理按照顺序进行处理 首先进行编解码处理，然后自己具体业务处理
 *
 */

public class ChatServer {
    private int port; //服务器端端口号

    public ChatServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new ChatServer(9999).run();
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 128).
                    childOption(ChannelOption.SO_KEEPALIVE, true).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ChannelPipeline pipeline = ch.pipeline();
                    //往pipeline链中添加一个解码器
                    pipeline.addLast("decoder", new StringDecoder());
                    //往pipeline链中添加一个编码器
                    pipeline.addLast("encoder", new StringEncoder());
                    //往pipeline链中添加自定义的handler(业务处理类)
                    pipeline.addLast(new ChatServerHandler());
                }
            });
            System.out.println("Netty Chat Server启动......");
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            //优雅的关闭线程操作
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("Netty Chat Server关闭......");
        }
    }
}
