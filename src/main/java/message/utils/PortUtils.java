package message.utils;

import message.dto.Constant;
import message.dto.MsgBase;
import message.server.msg.MsgFactory;
import message.server.socket.Launch;
import message.server.socket.SendCore;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by apple on 2017/9/18.
 */
public class PortUtils {
    private static class LazyHolder {
        private static final PortUtils INSTANCE = new PortUtils();
    }

    private PortUtils() {

    }

    public static final PortUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    static Set<Integer> disabled;
    static Set<Integer> usable;

    static {
        disabled = new HashSet<>();
        usable = new HashSet<>();
    }

    /**
     * 不可用端口
     *
     * @return
     */
    public static Set<Integer> getDisabled() {
        return disabled;
    }

    public synchronized static void setDisabled(Set<Integer> disabled) {
        PortUtils.disabled = disabled;
    }

    /**
     * 可用端口
     *
     * @return
     */
    public static Set<Integer> getUsable() {
        return usable;
    }

    /**
     * 获得一个可用的端口
     *
     * @return
     */
    public static Integer getUsableProt() {
        for (Integer key : usable) {
            return key;
        }
        return null;
    }

    public synchronized static void setUsable(Set<Integer> usable) {
        PortUtils.usable = usable;
    }

    /**
     * 校验中央服务器端口是否可用
     *
     * @return 使用返回true，不在使用返回false
     */
    public static boolean checkPort() {
        return isPortUsing(Constant.SERVERHOST, Constant.SERVERPROT);
    }


    /**
     * 测试本机端口是否被使用
     *
     * @param port
     * @return 在使用返回true，不在使用返回false
     */
    public static boolean isLocalPortUsing(int port) {
        return isPortUsing(Constant.LOCALHOST, port);
    }

    /**
     * 测试主机Host的port端口是否被使用
     *
     * @param host host
     * @param port 端口
     * @return 在使用返回true，不在使用返回false
     */
    public synchronized static boolean isPortUsing(String host, int port) {
        boolean flag;
        long t1 = System.currentTimeMillis();
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);
            socket.connect(socketAddress, Constant.TIMEOUT);
            flag = true;
            socket.close();
        } catch (Exception e) {
            flag = false;
        }
        System.out.println("端口【" + port + "】检测耗时：" + (System.currentTimeMillis() - t1));
        return flag;
    }

}
