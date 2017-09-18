package message.server.socket;

import message.dto.Constant;
import message.utils.PortUtils;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by apple on 2017/9/14.
 * 维护监听服务，并且开出对应的监听服务线程
 */
public class AcceptoCore extends Thread {
    static Map<Integer, ListenCore> listenCoreMap = new HashMap<>();



    public void run() {
        while (true) {
            try {
                Set<Integer> usablePorts = PortUtils.getUsable();
                Set<Integer> disabledPorts = PortUtils.getDisabled();
                for (Integer key : disabledPorts) {
                    if (listenCoreMap.containsKey(key)) {
                        listenCoreStop(key);
                    }
                }
                for (Integer key : usablePorts) {
                    if (!listenCoreMap.containsKey(key)) {
                        listenCoreMap.put(key, ListenCore.initListen(key));
                    }
                }
                System.out.println("端口监听实例维护完成，休息" + Constant.MILLIS + "ms");
                sleep(Constant.MILLIS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 停止监听线程并且移除map内的缓存实例
     * @param key
     * @throws Exception
     */
    private void listenCoreStop(Integer key) throws Exception {
        listenCoreMap.get(key).interrupt();
        listenCoreMap.get(key).getServerSocket().close();
        listenCoreMap.remove(key);
    }
}
