package message.server.vote;

import message.dto.Constant;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by apple on 2017/9/14.
 * 进行可用端口选举，及端口管理
 */
public class PortVote {
    //start--end是所要检测的端口范围
    static int start = Constant.arrayProt[0];
    static int end = Constant.arrayProt[1];
    static Set<Integer> portSet = new HashSet<>();

    /***
     * 获得一个本机可用的端口，调用此端口后会将当前返回的端口放入已使用中
     * @return 可用端口
     */
    public static int getUsablePort() {
        int prot = 0;
        for (int i = start; i <= end; i++) {
            if (!portSet.contains(i) && !isLocalPortUsing(i)) {
                System.out.println("获得可用端口:" + i);
                portSet.add(i);
                prot = i;
                break;
            }
        }
        // 调用服务监听程序，初始化监听
        return prot;
    }

    /**
     * 获得不可用端口
     *
     * @return 不可用端口列表
     */
    public static Set<Integer> getdisabledPort() {
        return portSet;
    }

    /**
     * 清除已使用端口列表
     */
    public static void clearPortSet() {
        portSet.clear();
    }

    /**
     * 校验端口是否可用
     *
     * @param prot 端口
     * @return true表示可用 false表示不可用
     */
    public static boolean checkPort(int prot) {
        return (prot >= start && prot <= end && !portSet.contains(prot) && !isLocalPortUsing(prot));
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
    public static boolean isPortUsing(String host, int port) {
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
        System.out.println("耗时：" + (System.currentTimeMillis() - t1));
        return flag;
    }
}