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
        MsgBase msgBase = MsgFactory.creationMsgBase("127.0.0.1", Integer.valueOf("8001"));
        msgBase.setResponse("你好");
        SendCore.send(msgBase);
//        for (int i = 0; i < 3; i++) {
//
//        }
    }
}
