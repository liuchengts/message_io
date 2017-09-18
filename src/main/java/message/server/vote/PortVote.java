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
    static volatile Set<Integer> portSet = new HashSet<>();




}