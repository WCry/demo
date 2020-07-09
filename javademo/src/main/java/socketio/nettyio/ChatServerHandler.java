package socketio.nettyio;

/**
 * @since 3.0
 */

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

//自定义一个服务器端业务处理类
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static List<Channel> channels = new ArrayList<>();

    @Override  //通道就绪
    public void channelActive(ChannelHandlerContext ctx) {
        Channel inChannel = ctx.channel();
        channels.add(inChannel);
        System.out.println("[Server]:" + inChannel.remoteAddress().toString().substring(1) + "上线");
    }

    @Override  //通道未就绪
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel inChannel = ctx.channel();
        channels.remove(inChannel);
        System.out.println("[Server]:" + inChannel.remoteAddress().toString().substring(1) + "离线");
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel inChannel = ctx.channel();
        System.out.println(inChannel.remoteAddress().toString().substring(1)+"发布消息"+msg);
        for (Channel channel : channels) {
            if (channel != inChannel) {
                channel.writeAndFlush("[" + inChannel.remoteAddress().toString().substring(1)
                        + "]" + "说：" + msg + "\n");
            }
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
         //关闭连接 主动调用Ctx的关闭
        ctx.close();
    }
}


