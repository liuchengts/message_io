package message.server.socket;

/**
 * Created by apple on 2017/9/18.
 * 这是线程启动的调用链
 */
public class Launch {
    static boolean acceptoCore, portCore;

//    public static void portCore() {
//        if (!portCore) {
//            new PortCore().start();
//            portCore = true;
//        }
//    }

    public static void acceptoCore() {
        if (!acceptoCore) {
            new AcceptoCore().start();
            acceptoCore = true;
        }

    }
}
