package message.server;

import org.apache.log4j.Logger;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2017/10/11.
 * 启动服务器
 */
public class Launch {
    private static Logger logger = Logger.getLogger(Launch.class);
    private static Map<Integer, ServerSocket> mapListen = new HashMap<>();

    public static ServerSocket getListen(int port) {
        return mapListen.get(port);
    }

    /**
     * 在指定端口上启动监听
     * @param port 端口
     */
    public static String launchListen(int port) {
        //启动服务器监听
        if(mapListen.containsKey(port)){
            logger.error("监听服务【"+port+"】已存在");
            return "监听服务【"+port+"】已存在";
        }
        try {
            mapListen.put(port, Listen.initListen(port));
        } catch (Exception e) {
            logger.error("服务器启动异常", e);
            return "服务器启动异常";
        }
        return null;
    }
}
