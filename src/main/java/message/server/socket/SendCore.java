package message.server.socket;

import message.dto.MsgBase;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2017/9/14.
 * 发送消息
 */
public class SendCore {
    static volatile Map<String, Socket> socketMap = new HashMap<>();

    /*****
     * 发送
     * @param msgBase
     */
    public static void send(MsgBase msgBase) {
        Socket socket;
        try {
            String key = msgBase.getIp() + "@" + msgBase.getPort();
            if (socketMap.containsKey(key)) {
                socket = socketMap.get(key);
            } else {
                //客户端socket指定服务器的地址和端口号
                socket = new Socket(msgBase.getIp(), msgBase.getPort());
                socketMap.put(key, socket);
            }
        } catch (Exception e) {
            throw new RuntimeException("无法建立socket连接", e);
        }
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(msgBase.getResponse());
            pw.flush();
            System.out.println("消息发送成功：" + msgBase.getResponse() + " |" + msgBase.getPort());
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException("消息发送失败", e);
        }
    }
}
