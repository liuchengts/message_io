package message.server.vote;

import message.dto.Constant;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
        for (int i = start; i <= end; i++) {
            if (!portSet.contains(i) && !isLocalPortUsing(i)) {
                System.out.println("获得可用端口:" + i);
                portSet.add(i);
                return i;
            }
        }
        return 0;
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

    public static void main(String args[]) {
        getUsablePort();
    }

    /**
     * 测试本机端口是否被使用
     *
     * @param port
     * @return
     */
    public static boolean isLocalPortUsing(int port) {
        //如果该端口还在使用则返回true,否则返回false,127.0.0.1代表本机
        return isPortUsing(Constant.LOCALHOST, port);
    }

    /***
     * 测试主机Host的port端口是否被使用
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host, int port) {
        boolean flag;
        try {
            InetAddress Address = InetAddress.getByName(host);
            Socket socket = new Socket(Address, port);  //建立一个Socket连接
            flag = true;
            socket.close();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}