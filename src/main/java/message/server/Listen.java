package message.server;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by apple on 2017/9/18.
 * 调用initListen()创建监听
 */
public class Listen extends Thread {
    private static Logger logger = Logger.getLogger(Listen.class);
    static ServerSocket serverSocket;

    /***
     * 启动一个服务监听并且返回当前实例
     * @param port
     * @return
     */
    public static synchronized Listen initListen(int port) throws Exception {
        Listen core = new Listen();
        serverSocket=new ServerSocket(port);
        logger.debug("创建了一个监听:" + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort());
        core.start();
        return core;
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuffer lineBuffer = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    lineBuffer.append(line);
                }
                String str = lineBuffer.toString();
                logger.debug("******************************listen["+socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort()+"]服务端接受到的消息 :" + str);
                Distribute.init(str,socket);
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
