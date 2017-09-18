package message;


import message.dto.MsgBase;
import message.server.msg.MsgFactory;
import message.server.socket.AcceptoCore;
import message.server.socket.PortCore;
import message.server.socket.SendCore;

/**
 * 发消息
 */
public class App {
    public static void main(String[] args) {
        //端口维护线程启动
        new PortCore().start();
        new AcceptoCore().start();
//        DiscoverCore.getInstance();
        for(int i=0;i<3;i++){
            MsgBase msgBase = MsgFactory.creationMsgBase("127.0.0.1");
            msgBase.setResponse("你好"+i);
            SendCore.send(msgBase);
        }
    }
}
