package message;


import message.dto.MsgBase;
import message.server.msg.MsgFactory;
import message.server.socket.AcceptoCore;
import message.server.socket.SendCore;

/**
 * 发消息
 */
public class App {
    public static void main(String[] args) {
        AcceptoCore.getInstance();
//        DiscoverCore.getInstance();
        for(int i=0;i<3;i++){
            MsgBase msgBase = MsgFactory.creationMsgBase("127.0.0.1");
            msgBase.setResponse("你好"+i);
            SendCore.send(msgBase);
        }
    }
}
