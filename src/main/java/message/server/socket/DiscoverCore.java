package message.server.socket;


import message.dto.Constant;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by apple on 2017/9/14.
 * 自我注册成中央服务器，本地ip+Constant.SERVERPROT端口组成
 * 其他人需要通过中央服务器进行隧道协议连接到此
 */
public class DiscoverCore {
    private static final DiscoverCore.Core core;

    static {
        core = new DiscoverCore.Core();
        core.start();
    }

    static class Core extends Thread {
        public void run() {
            BufferedReader br = null;
            PrintWriter pw = null;
            Socket socket = null;
            ServerSocket s = null;
            int i = 1;
            try {
                s = new ServerSocket(Constant.SERVERPROT);
            } catch (Exception e) {
                System.out.println("服务监听端口异常" + Constant.SERVERPROT);
            }
            System.out.println("服务监听启动，监听端口：" + Constant.SERVERPROT);
            while (true) {
                try {
                    assert s != null;
                    socket = s.accept();
                    if (null == socket) {
                        sleep(Constant.MILLIS);
                        continue;
                    }
                    System.out.println("listen服务端线程接受到第" + i + "个请求，socket:" + socket);
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    String str = br.readLine();
                    System.out.println("listen服务端接受到的消息 :" + str);
                    pw.flush();
                } catch (Exception e) {
                    try {
                        br.close();
                        pw.close();
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                i++;
            }
        }
    }
}
