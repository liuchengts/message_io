package message.client;

import message.dto.Client;
import message.dto.Msg;
import message.server.Distribute;
import message.utils.GsonUtils;
import message.view.Register;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by apple on 2017/10/17.
 *
 * @lc 客户端基础实现
 */
public class Connect extends Thread {
    private static Logger logger = Logger.getLogger(Connect.class);
    static Client client;

    public static void setClient(Client client) {
        Connect.client = client;
    }

    /***
     * 启动一个客户端服务并且返回当前实例
     * @param ip 目标ip
     * @param port 目标端口
     * @param msg 消息对象
     * @return 客户端连接实例
     * @throws Exception
     */
    public static synchronized Connect initConnect(String ip, int port, Msg msg) throws Exception {
        Connect core = new Connect();
        Socket socket = new Socket(ip, port);
        Client client = new Client(socket);
        core.setClient(client);
        client.sendMessage(GsonUtils.objectToJson(msg));
        logger.debug("创建了一个客户端:" + socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort());
        core.start();
        return core;
    }

    public void run() {
        while (true) {
            try {
                String message = client.getReader().readLine();
                if (null == message) {
                    Thread.sleep(100);
                    continue;
                }
                logger.debug("客户端[" + client.getSocket().getLocalPort() + "]接收到消息：" + message);
                dispose(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理服务器返回消息
     *
     * @param message 待处理对象
     */
    private void dispose(String message) {
        Msg msg = (Msg) GsonUtils.jsonToObject(message, Msg.class);
        //判断服务消息
        if (client.getSocket().getLocalPort() == Distribute.DEFAULT_PORT) {
            if (Msg.GROUP.equals(msg.getOperate())) {
                //创建聊天组

            } else if (Msg.CLOSE.equals(msg.getOperate())) {
                //关闭聊天组
            } else if (Msg.ERROR.equals(msg.getOperate())) {
                //验证异常
                Register register = new Register();
                register.setError(msg.getMsg());
            } else {
                //在首页显示消息

            }
        } else {
            //正常消息通讯
        }
    }
}
