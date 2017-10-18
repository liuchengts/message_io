import message.server.Distribute;
import message.server.Launch;

/**
 * Created by apple on 2017/10/18.
 * main
 */
public class Server {
    public static void main(String[] args) {
        //启动核心通讯监听 端口6666
        Launch.launchListen(Distribute.DEFAULT_PORT);
    }

}
