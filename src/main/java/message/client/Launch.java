package message.client;

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
    private static String IP = "www.modaolc.com";
    private static Map<Integer, Connect> mapConnect = new HashMap<>();

    public static Connect getMapConnect(int port) {
        return mapConnect.get(port);
    }
    public static void removeMapConnect(int port) {
         mapConnect.remove(port);
    }
    /**
     * 初始化连接
     */
    public static String launchConnect(String name) {
        if(null==name || "".equals(name)){
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
        return  connect(IP, Distribute.DEFAULT_PORT, msg);
    }
    /**
     * 连接指定端口
     */
    public static String launchConnect(int port,Msg msg) {
        return  connect(IP, port, msg);
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
