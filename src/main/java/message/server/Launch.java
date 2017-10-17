package message.server;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2017/10/11.
 * 启动服务器
 */
public class Launch {
    private static Logger logger = Logger.getLogger(Launch.class);
    private static Map<Integer, Listen> mapListen = new HashMap<>();

    public static void main(String[] args) {
        //启动核心通讯监听 端口6666
        launchListen(Distribute.DEFAULT_PORT);
    }


    /**
     * 在指定端口上启动监听
     * @param port 端口
     */
    private static boolean launchListen(int port) {
        //启动服务器监听
        if(mapListen.containsKey(port)){
            logger.error("监听服务【"+port+"】已存在");
            return false;
        }
        try {
            mapListen.put(port, Listen.initListen(port));
        } catch (Exception e) {
            logger.error("服务器启动异常", e);
            return false;
        }
        return true;
    }
}
