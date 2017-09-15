package message;


import message.dto.MsgBase;
import message.server.msg.MsgFactory;
import message.server.socket.AcceptoCore;
import message.server.socket.DiscoverCore;
import message.server.socket.SendCore;

/**
 * 发消息
 */
public class App {
    public static void main(String[] args) {
        new AcceptoCore();
        new DiscoverCore();
        new SendCore();
        MsgBase msgBase = MsgFactory.creationMsgBase("127.0.0.1");
        msgBase.setResponse("你好");
        SendCore.send(msgBase);
        SendCore.send(msgBase);
        SendCore.send(msgBase);
        SendCore.send(msgBase);
    }
}
