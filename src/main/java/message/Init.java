package message;
import message.server.socket.Launch;


/**
 * Created by apple on 2017/9/18.
 */
public class Init {
    public static void main(String[] args) {
        //启动服务
        Launch.acceptoCore();
        Launch.msgRecaption();
//        try {
//            ListenCore.initListen(new ServerSocket(8000));
//        }catch (Exception e){
//
//        }
    }
}
