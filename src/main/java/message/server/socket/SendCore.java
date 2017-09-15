package message.server.socket;

import message.dto.Constant;
import message.dto.MsgBase;

import java.io.*;
import java.net.Socket;

/**
 * Created by apple on 2017/9/14.
 * 发送消息
 */
public class SendCore {
    private static final Core core;

    static {
        core = new SendCore.Core();
        core.start();
    }

    /*****
     * 发送
     * @param msgBase
     */
    public static void send(MsgBase msgBase) {
        core.setMsgBase(msgBase);
    }

    static class Core extends Thread {
        MsgBase msgBase;

        void setMsgBase(MsgBase msgBase) {
            this.msgBase = msgBase;
        }
        public void run() {
            System.out.println("发送服务线程启动...");
            while (true) {
                try {
                    if (null == msgBase) {
                        System.out.println("消息内容是空的，休息" + Constant.MILLIS + "ms");
                        sleep(Constant.MILLIS);
                        continue;
                    }
                    System.out.println("发送ip：" + msgBase.getIp() + "发送端口号：" + msgBase.getPort());
                } catch (Exception e) {
                    continue;
                }
                Socket socket;
                try {
                    //客户端socket指定服务器的地址和端口号
                    socket = new Socket(msgBase.getIp(), msgBase.getPort());
                    System.out.println("发送端ip：" + socket.getInetAddress().getHostAddress());
                } catch (Exception e) {
                    throw new RuntimeException("发送线程无法建立socket连接", e);
                }
                try {
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                    pw.println(msgBase.getResponse());
                    pw.flush();
                    //重新初始化消息发送参数
                    msgBase = null;
                } catch (IOException e) {
                    throw new RuntimeException("消息发送失败", e);
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
