package message.server.socket;

import message.dto.Constant;
import message.server.vote.PortVote;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by apple on 2017/9/14.
 * 消息接收，根据当前占用的端口开出对应的接收服务
 */
public class AcceptoCore {
    private static final AcceptoCore.Core core;
    private static final AcceptoCore.CoreListen coreListen;
    private static volatile Map<Integer, ServerSocket> cacheServerSocket = new HashMap<>();

    static {
        core = new AcceptoCore.Core();
        core.start();
        coreListen = new AcceptoCore.CoreListen();
        coreListen.start();
    }

    /***
     * 维护端口及cacheServerSocket实例缓存的内部类
     */
    static class Core extends Thread {
        public void run() {
            System.out.println("监听服务维护线程启动...");
            while (true) {
                try {
                    Set<Integer> createProt = maintain();
                    if (createProt.size() <= 0) {
                        System.out.println("没有需要监听的端口，休息" + Constant.MILLIS + "ms");
                        sleep(Constant.MILLIS);
                        continue;
                    }
                    for (Integer key : createProt) {
                        cacheServerSocket.put(key, new ServerSocket(key));
                    }
                    System.out.println("新建监听数量:" + createProt.size());
                } catch (Exception e) {
                    System.out.println("服务监听端口创建异常");
                }
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
        Set<Integer> disabledPorts = PortVote.getdisabledPort();
        try {
            for (Integer key : cacheServerSocket.keySet()) {
                if (disabledPorts.contains(key)) {
                    //当前端口还在使用
                    continue;
                }
                //端口已经释放了，这里需要关闭掉对应的cacheServerSocket
                cacheServerSocket.get(key).close();
                cacheServerSocket.remove(key);
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

    /**
     * 监听消息执行的内部类
     */
    static class CoreListen extends Thread {
        public void run() {
            System.out.println("监听服务线程启动...");
            while (true) {
                for (ServerSocket serverSocket : cacheServerSocket.values()) {
                    BufferedReader br = null;
                    try {
                        Socket socket = serverSocket.accept();
                        if (null == socket) {
                            System.out.println("没有消息，休息" + Constant.MILLIS + "ms");
                            sleep(Constant.MILLIS);
                            continue;
                        }
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String str = br.readLine();
                        System.out.println("listen服务端接受到的消息 :" + str);
                    } catch (Exception e) {
                        try {
                            br.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
