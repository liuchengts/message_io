package message;


import message.dto.MsgBase;
import message.server.msg.MsgFactory;
import message.server.socket.SendCore;

/**
 * 发消息
 */
public class App {
    public static void main(String[] args) {
        //测试发送消息
        MsgBase msgBase = MsgFactory.creationMsgBase("127.0.0.1", Integer.valueOf("8000"),"你好1");
        SendCore.send(msgBase);
    }
}
