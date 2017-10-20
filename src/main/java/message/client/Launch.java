package message.client;

import message.dto.Client;
import message.dto.Msg;
import message.dto.User;
import message.server.Distribute;
import message.utils.GsonUtils;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2017/10/11.
 * 启动客户端
 */
public class Launch {
    private static Logger logger = Logger.getLogger(Launch.class);
    private static String IP = "127.0.0.1";//"www.modaolc.com";
    private static Map<Integer, Client> mapConnect = new HashMap<>();
    private static String nick;

    public static Client getMapConnect(int port) {
        return mapConnect.get(port);
    }

    public static void removeMapConnect(int port) {
        mapConnect.remove(port);
    }

    public static void request(Msg msg) {
        if (null != msg && null == msg.getName()) {
            msg.setName(nick);
        }
        Launch.getMapConnect(Distribute.DEFAULT_PORT).sendMessage(msg);
    }

    /**
     * 初始化连接
     */
    public static String launchConnect(String name) {
        if (null == name || "".equals(name)) {
            return "昵称不能为空";
        }
        Msg msg = new Msg();
        msg.setName(name);
        User user = new User();
        user.setName(msg.getName());
        try {
            user.setIp(InetAddress.getLocalHost().getHostAddress().toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        msg.setMsg(GsonUtils.objectToJson(user));
        String rs = connect(IP, Distribute.DEFAULT_PORT, msg);
        if (null == rs) {
            nick = msg.getName();
        }
        return rs;
    }

    /**
     * 连接指定端口
     */
    public static String launchConnect(int port, Msg msg) {
        if (null != msg && null == msg.getName()) {
            msg.setName(nick);
        }
        return connect(IP, port, msg);
    }

    /**
     * 在指定端口上启动客户端
     *
     * @param ip   目标ip
     * @param port 目标端口
     * @param msg  消息体
     * @return 返回是否成功
     */
    private static String connect(String ip, int port, Msg msg) {
        //启动客户端
        if (mapConnect.containsKey(port)) {
            logger.error("客户端【" + port + "】已存在");
            return "客户端【" + port + "】已存在";
        }
        try {
            mapConnect.put(port, Connect.initConnect(ip, port, msg));
        } catch (Exception e) {
            logger.error("客户端启动异常", e);
            return "客户端启动异常";
        }
        return null;
    }
}
