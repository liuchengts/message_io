package message.server.msg;

import message.dto.MsgBase;
import message.dto.MsgVO;
import message.utils.NetworkUtils;
import message.utils.PortUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Created by apple on 2017/9/14.
 * 消息生产，分配端口
 */
public class MsgFactory {
    private static Logger logger = Logger.getLogger(MsgFactory.class.getName());

    private static class LazyHolder {
        private static final MsgFactory INSTANCE = new MsgFactory();
    }

    private MsgFactory() {

    }

    public static final MsgFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static Vector<MsgVO> msgVOS=new Vector<>();

    /**
     * 获得消息存储器
     * @return
     */
    public static Vector<MsgVO> getMsgVOS() {
        return msgVOS;
    }
    /**
     * 塞入消息
     * @param port
     * @param request
     */
    public static  void  put(Integer port,String request){
        msgVOS.add(new MsgVO(port,request));
    }

    public static MsgBase creationMsgBase(String ip,Integer prot,String msg) {
        MsgBase msgBase = new MsgBase();
        msgBase.setResponse(msg);
        msgBase.setIp(ip);
        msgBase.setPort(prot);
        msgBase.setMac(NetworkUtils.getPhysicalNetworkMAC());
        return msgBase;
    }

}
