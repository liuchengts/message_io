package message.server.msg;

import message.dto.MsgBase;
import message.utils.NetworkUtils;
import message.utils.PortUtils;

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

    public static MsgBase creationMsgBase(String ip) {
        MsgBase msgBase = new MsgBase();
        msgBase.setIp(ip);
        msgBase.setPort(PortUtils.getUsableProt());
        msgBase.setMac(NetworkUtils.getPhysicalNetworkMAC());
        return msgBase;
    }

}
