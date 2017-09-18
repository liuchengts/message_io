package message.server.socket;

import message.dto.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by apple on 2017/9/18.
 */
public class ListenCore extends Thread {

    private static ServerSocket serverSocket;

    /***
     * 启动一个服务监听并且返回当前实例
     * @param serverSocket
     * @return
     */
    public static ListenCore initListen(ServerSocket serverSocket) {
        ListenCore core = new ListenCore();
        core.serverSocket = serverSocket;
        core.start();
        return core;
    }

    public void run() {
        while (true) {
            BufferedReader br = null;
            try {
                Socket socket = serverSocket.accept();
                if (null == socket) {
                    System.out.println("没有监听消息，休息" + Constant.MILLIS + "ms");
                    sleep(Constant.MILLIS);
                    continue;
                }
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = br.readLine();
                System.out.println("listen服务端接受到的消息 :" + str);
            } catch (Exception e) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
