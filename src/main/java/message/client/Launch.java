package message.client;

import message.dto.Msg;
import message.server.Distribute;
import message.server.Listen;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2017/10/11.
 * 启动客户端
 */
public class Launch {
    private static Logger logger = Logger.getLogger(Launch.class);
    private static String IP="127.0.0.1";
    private static Map<Integer, Connect> mapConnect = new HashMap<>();

    public static void main(String[] args) {
        //启动核心通讯 端口6666
        for(int i=0;i<10;i++){
            Msg msg=new Msg();
            msg.setName("test"+i);
            msg.setMsg("第一次连接"+i);
            launchConnect(IP,Distribute.DEFAULT_PORT,msg);
        }

    }


    /**
     * 在指定端口上启动客户端
     * @param port 端口
     */
    private static boolean launchConnect(String ip,int port,Msg msg) {
        //启动客户端
//        if(mapConnect.containsKey(port)){
//            logger.error("客户端【"+port+"】已存在");
//            return false;
//        }
        try {
            mapConnect.put(port, Connect.initConnect(ip,port,msg));
        } catch (Exception e) {
            logger.error("客户端启动异常", e);
            return false;
        }
        return true;
    }
}
