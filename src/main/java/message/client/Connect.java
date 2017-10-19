package message.client;

import com.google.gson.reflect.TypeToken;
import message.dto.Client;
import message.dto.Msg;
import message.dto.User;
import message.server.Distribute;
import message.utils.GsonUtils;
import message.view.Home;
import message.view.Module;
import message.view.Register;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by apple on 2017/10/17.
 *
 * @lc 客户端基础实现
 */
public class Connect extends Thread {
    private static Logger logger = Logger.getLogger(Connect.class);
    private static Client client;

    public static Client getClient() {
        return client;
    }

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
    public static synchronized Client initConnect(String ip, int port, Msg msg) throws Exception {
        Connect core = new Connect();
        Socket socket = new Socket(ip, port);
        Client client = new Client(socket);
        core.setClient(client);
        client.sendMessage(msg);
        logger.debug("创建了一个客户端:" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        core.start();
        return client;
    }

    public void run() {
        while (client.isFag()) {
            try {
                String message = client.getReader().readLine();
                if (null == message) {
                    Thread.sleep(100);
                    continue;
                }
                logger.debug("客户端[" + client.getSocket().getPort() + "]接收到消息：" + message);
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
        if (client.getSocket().getPort() == Distribute.DEFAULT_PORT) {
            if (Msg.GROUP.equals(msg.getOperate())) {
                //创建聊天组
            } else if (Msg.CLOSE.equals(msg.getOperate())) {
                //关闭聊天组
            } else if (Msg.ERROR.equals(msg.getOperate())) {
                //验证异常
                Module.getRegister().setError(msg.getMsg());
                //关闭客户端
                client.close();
            } else {
                //在首页显示注册成功后回传的消息
                Module.getHome().setOnline((Set<User>) GsonUtils.jsonToObject(msg.getMsg(), new TypeToken<HashSet<User>>() {
                }.getType()));
            }
        } else {
            //正常消息通讯
        }
    }
}
