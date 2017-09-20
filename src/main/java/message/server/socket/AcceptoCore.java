package message.server.socket;

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
    static volatile Map<Integer, ListenCore> listenCoreMap = new HashMap<>();
    static PortUtils portUtils = PortUtils.getInstance();

    public void run() {
        System.out.println("监听实例维护线程启动...");
        Set<Integer> usablePorts = portUtils.getUsable();
        try {
            for (Integer key : usablePorts) {
                if (!listenCoreMap.containsKey(key)) {
                    listenCoreMap.put(key, ListenCore.initListen(new ServerSocket(key)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        while (true) {
//            try {
//                Set<Integer> usablePorts = portUtils.getUsable();
//                for (Integer key : listenCoreMap.keySet()) {
//                    if (listenCoreMap.get(key).getServerSocket().isClosed()) {
//                        listenCoreStop(key);
//                        usablePorts.add(key);
//                    }
//                }
//                for (Integer key : usablePorts) {
//                    if (!listenCoreMap.containsKey(key)) {
//                        listenCoreMap.put(key, ListenCore.initListen(new ServerSocket(key)));
//                        usablePorts.remove(key);
//                    }
//                }
//                portUtils.setUsable(usablePorts);
//                sleep(Constant.MILLIS);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 停止监听线程并且移除map内的缓存实例
     *
     * @param key
     * @throws Exception
     */
    private void listenCoreStop(Integer key) throws Exception {
        System.out.println("关掉监听" + key);
        listenCoreMap.get(key).interrupt();
        listenCoreMap.get(key).getServerSocket().close();
        listenCoreMap.remove(key);
    }
}
