package socketio.nettyio;

/**
 * @since 3.0
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

//自定义一个客户端业务处理类
//需要继承InBoundHandle 或者OutBoundHandle
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}