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

    static ServerSocket serverSocket;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void setServerSocket(ServerSocket serverSocket) {
        ListenCore.serverSocket = serverSocket;
    }

    /***
     * 启动一个服务监听并且返回当前实例
     * @param prot
     * @return
     */
    public static ListenCore initListen(int prot) throws Exception {
        ListenCore core = new ListenCore();
        core.setServerSocket(new ServerSocket(prot));
        core.start();
        return core;
    }

    public void run() {
        System.out.println("监听启动..."+getServerSocket().getLocalPort());
        while (true) {
            BufferedReader br = null;
            try {
                Socket socket = getServerSocket().accept();
                if (null == socket) {
                    System.out.println("没有监听消息，休息" + Constant.MILLIS + "ms");
                    sleep(Constant.MILLIS);
                    continue;
                }
                System.out.println("接收端ip："+socket.getInetAddress().getHostAddress());
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                if (null == br || null == br.readLine()) {
                    continue;
                }
                String str = br.readLine();
                System.out.println("******************************listen服务端接受到的消息 :" + str);
            } catch (Exception e) {
                try {
                    if (null != br) {
                        br.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
