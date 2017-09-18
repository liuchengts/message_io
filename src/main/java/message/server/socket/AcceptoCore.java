package message.server.socket;

import message.utils.PortUtils;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by apple on 2017/9/14.
 * 消息接收，根据当前占用的端口开出对应的接收服务
 */
public class AcceptoCore extends Thread {
    static volatile Map<Integer, ServerSocket> cacheServerSocket = new HashMap<>();
    static volatile Map<Integer, ListenCore> cacheListen = new HashMap<>();

    /***
     * 维护端口及cacheServerSocket实例缓存
     */
    public void run() {
        System.out.println("监听实例维护线程启动...");
        while (true) {
            try {
                Set<Integer> createProt = maintain();
                if (createProt.size() <= 0) {
                    continue;
                }
                for (Integer key : createProt) {
                    ServerSocket serverSocket = new ServerSocket(key);
                    cacheServerSocket.put(key, serverSocket);
                    cacheListen.put(key, ListenCore.initListen(serverSocket));
                }
                System.out.println("新建监听数量:" + createProt.size());
            } catch (Exception e) {
                System.out.println("服务监听端口创建异常");
            }
        }
    }

    /**
     * 端口实例维护
     *
     * @return
     */
    private static synchronized Set<Integer> maintain() {
        Set<Integer> set = new HashSet<>();
        Set<Integer> disabledPorts = PortUtils.getDisabled();
        try {
            for (Integer key : cacheServerSocket.keySet()) {
                if (disabledPorts.contains(key)) {
                    //当前端口还在使用
                    continue;
                }
                //端口已经释放了，这里需要停止线程及关闭掉对应的cacheServerSocket
                cacheListen.get(key).interrupt();
                cacheServerSocket.get(key).close();
                cacheServerSocket.remove(key);
                disabledPorts.remove(key);
            }
            for (Integer key : disabledPorts) {
                //不可用端口不在cacheServerSocket列表中，需要创建
                if (!cacheServerSocket.containsKey(key)) {
                    set.add(key);
                }
            }
        } catch (Exception e) {
            System.out.println("维护cacheServerSocket异常");
            e.printStackTrace();
        }
        return set;
    }
}
