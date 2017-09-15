package message.server.socket;

import message.server.msg.MsgFactory;

/**
 * Created by apple on 2017/9/14.
 * 自我注册发现
 */
public class DiscoverCore {
    private static class LazyHolder {
        private static final DiscoverCore INSTANCE = new DiscoverCore();
    }

    private DiscoverCore() {

    }

    public static final DiscoverCore getInstance() {
        return DiscoverCore.LazyHolder.INSTANCE;
    }


}
