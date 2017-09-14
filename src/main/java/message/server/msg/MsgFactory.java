package message.server.msg;

import message.dto.Constant;
import message.dto.MsgBase;
import message.server.vote.PortVote;
import message.utils.NetworkUtils;

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

    public static MsgBase creationMsgBase(String type) {
        MsgBase msgBase = new MsgBase();
//        msgBase.setIp();
        msgBase.setMac(NetworkUtils.getPhysicalNetworkMAC());
        if (Constant.VIDEO.equals(type)) {
            msgBase.setPort_Voice(PortVote.getUsablePort());
        } else if (Constant.VOICE.equals(type)) {
            msgBase.setPort_Voice(PortVote.getUsablePort());
        } else {
            msgBase.setPort_Text(PortVote.getUsablePort());
        }
        return msgBase;
    }

}
