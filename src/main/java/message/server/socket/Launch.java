package message.server.socket;

import message.server.msg.MsgRecaption;

/**
 * Created by apple on 2017/9/18.
 * 这是线程启动的调用链
 */
public class Launch {
    static boolean acceptoCore, msgRecaption;

    public static void acceptoCore() {
        if (!acceptoCore) {
            new AcceptoCore().start();
            acceptoCore = true;
        }

    }

    public static void msgRecaption() {
        if (!msgRecaption) {
            new MsgRecaption().start();
            msgRecaption = true;
        }

    }
}
