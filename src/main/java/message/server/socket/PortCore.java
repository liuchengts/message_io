package message.server.socket;

import message.dto.Constant;
import message.utils.PortUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by apple on 2017/9/18.
 * 管理端口
 */
public class PortCore extends Thread{
    //start--end是所要检测的端口范围
    static final int start = Constant.arrayProt[0];
    static final int end = Constant.arrayProt[1];

    public void run() {
        while (true){
            Set<Integer> disabled =new HashSet<>();
            Set<Integer> usable =new HashSet<>();
            try {
                for (int i = start; i <= end; i++) {
                    if (PortUtils.isLocalPortUsing(i)) {
                        usable.add(i);
                    }else{
                        disabled.add(i);
                    }
                }
                PortUtils.setUsable(usable);
                PortUtils.setDisabled(disabled);
//                System.out.println("端口维护线程休息" + Constant.MILLIS_PROT + "ms");
                sleep(Constant.MILLIS_PROT);
            }catch (Exception e){
                PortUtils.setUsable(usable);
                PortUtils.setDisabled(disabled);
                System.out.println("端口维护线程异常");
                e.printStackTrace();
            }
        }
    }

}
