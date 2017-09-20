package message.server.socket;

import message.server.msg.MsgFactory;

import java.io.*;
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
     * @param serverSocket
     * @return
     */
    public static synchronized ListenCore initListen(ServerSocket serverSocket) throws Exception {
        ListenCore core = new ListenCore();
        System.out.println("创建了一个监听:" + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort());
        core.setServerSocket(serverSocket);
        core.start();
        return core;
    }

    public void run() {
        while (true) {
            try {
                Socket socket = getServerSocket().accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuffer lineBuffer = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    lineBuffer.append(line);
                }
                String str = lineBuffer.toString();
                MsgFactory.put(socket.getPort(),str);
                System.out.println("******************************listen["+socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort()+"]服务端接受到的消息 :" + str);
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
