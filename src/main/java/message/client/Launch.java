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
    private static String IP = "127.0.0.1";
    private static Map<Integer, Connect> mapConnect = new HashMap<>();

    public static void main(String[] args) {
        //启动核心通讯 端口
        launchConnect();
    }

    /**
     * 初始化连接
     */
    private static void launchConnect() {
        Msg msg = new Msg();
        msg.setName("test");
        User user = new User();
        user.setName(msg.getName());
        try {
            user.setIp(InetAddress.getLocalHost().getHostAddress().toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        msg.setMsg(GsonUtils.objectToJson(user));
        connect(IP, Distribute.DEFAULT_PORT, msg);
    }

    /**
     * 在指定端口上启动客户端
     *
     * @param ip   目标ip
     * @param port 目标端口
     * @param msg  消息体
     * @return 返回是否成功
     */
    private static boolean connect(String ip, int port, Msg msg) {
        //启动客户端
        if (mapConnect.containsKey(port)) {
            logger.error("客户端【" + port + "】已存在");
            return false;
        }
        try {
            mapConnect.put(port, Connect.initConnect(ip, port, msg));
        } catch (Exception e) {
            logger.error("客户端启动异常", e);
            return false;
        }
        return true;
    }
}
