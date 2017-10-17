package message.server;

import message.dto.Client;
import message.dto.Msg;
import message.dto.Res;
import message.dto.User;
import message.utils.GsonUtils;

import java.net.Socket;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Created by apple on 2017/10/11.
 * 分发服务器收到的消息
 */
public class Distribute {
    private static Logger logger = Logger.getLogger(Distribute.class);
    public static int DEFAULT_PORT = 6666;//获得服务器当前可用端口列表，推送当前端口到客户端

    /**
     * 接收客户端消息后的分发处理
     *
     * @param message
     * @param socket
     */
    public static void init(String message, Socket socket) {
        Msg msg= (Msg) GsonUtils.jsonToObject(message,Msg.class);
        //判断服务消息
        if (socket.getLocalPort() == DEFAULT_PORT) {
            defautDispose(msg,socket);
        } else if (Msg.GROUP.equals(msg.getOperate())) {
            //创建聊天组
        } else if (Msg.CLOSE.equals(msg.getOperate())) {
            //关闭聊天组
        } else {
            //发送消息
        }
    }

    /**
     * 核心通讯6666端口处理
     *
     * @param socket
     */
    public static void defautDispose(Msg msg, Socket socket) {
        if (Msg.GROUP.equals(msg.getOperate())) {
            //创建聊天组
        } else if (Msg.CLOSE.equals(msg.getOperate())) {
            //关闭聊天组
        } else {
            //是核心线程进行客户端连接注册，加入客户注册中心
            Client client = null;
            try {
                Res res = Register.regClient(DEFAULT_PORT, msg, socket);
                client = (Client) res.getObject();
                if (!res.isFag()) {
                    //客户端检查name不通过
                    send(client, res.getMsg());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("连接服务器失败", e);
            }
            //TODO 当前只列出了在线人群，其他功能还未实现，需要扩展user类
            Set<User> users = Register.getUsers();
            send(GsonUtils.objectToJson(users));
        }
    }

    /**
     * 给当前在线用户发送消息
     */
    public static void send(String msg) {
        Map<String, Client> map = Register.getClients(DEFAULT_PORT);
        for (String name : map.keySet()) {
            logger.debug("群发消息："+name);
            send(map.get(name), msg);
        }
    }

    /**
     * 给特定用户发送消息
     */
    public static void send(Client client, String message) {
        Msg msg=new Msg();
        msg.setMsg(message);
        client.sendMessage(GsonUtils.objectToJson(msg));
    }
}