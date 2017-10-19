package message.view;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2017/10/19.
 * 客户端组件对象管理
 */
public class Module {
    private static Register localRegister;
    private static Home localHome;
    private static Map<Integer, Client> localClient = new HashMap<>();
    private static final int max = 5;
    private static Logger logger = Logger.getLogger(Module.class);

    /**
     * 发起注册
     *
     * @return
     */
    public static Register getRegister() {
        if (null == localRegister) {
            localRegister = new Register();
        }
        return localRegister;
    }

    /**
     * 发起首页
     *
     * @return
     */
    public static Home getHome() {
        if (null == localHome) {
            localHome = new Home();
        }
        return localHome;
    }

    /**
     * 发起客户端
     *
     * @param port
     * @return
     */
    public static Client getClient(Integer port) {
        if (null == port) {
            logger.error("端口不能为空");
            return null;
        }
        Client client;
        if (null == localClient.get(port)) {
            if (localClient.size() >= max) {
                logger.error("客户端超过了连接最大限制");
                return null;
            }
            client = new Client(port);
            localClient.put(port, client);
        } else {
            client = localClient.get(port);
        }
        return client;
    }


}
