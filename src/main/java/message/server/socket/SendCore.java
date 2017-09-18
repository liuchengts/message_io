package message.server.socket;

import message.dto.MsgBase;

import java.io.*;
import java.net.Socket;

/**
 * Created by apple on 2017/9/14.
 * 发送消息
 */
public class SendCore {
    /*****
     * 发送
     * @param msgBase
     */
    public static void send(MsgBase msgBase) {
        Socket socket;
        try {
            //客户端socket指定服务器的地址和端口号
            socket = new Socket(msgBase.getIp(), msgBase.getPort());
        } catch (Exception e) {
            throw new RuntimeException("发送线程无法建立socket连接", e);
        }
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            pw.println(msgBase.getResponse());
            pw.flush();
            System.out.println("消息发送成功：" + msgBase.getResponse() + " |" + msgBase.getPort());
        } catch (IOException e) {
            throw new RuntimeException("消息发送失败", e);
        }
    }
}
