package message.server.socket;

import message.dto.Constant;
import message.utils.PortUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by apple on 2017/9/18.
 * 管理端口
 */
public class PortCore extends Thread {
    //start--end是所要检测的端口范围
    static final int start = Constant.arrayProt[0];
    static final int end = Constant.arrayProt[1];
    static PortUtils portUtils = PortUtils.getInstance();

    public void run() {
        System.out.println("端口维护线程启动...");
        while (true) {
            Set<Integer> disabled = new HashSet<>();
            Set<Integer> usable = new HashSet<>();
            try {
                for (int i = start; i <= end; i++) {
                    if (portUtils.isLocalPortUsing(i)) {
                        disabled.add(i);
                    } else {
                        usable.add(i);
                    }
                }
                portUtils.setUsable(usable);
                portUtils.setDisabled(disabled);
                Launch.acceptoCore();
                sleep(Constant.MILLIS);
                System.out.println("端口维护线程完成");
            } catch (Exception e) {
                System.out.println("端口维护线程异常");
                e.printStackTrace();
            }
        }

    }

}
