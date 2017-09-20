package message.server.msg;

import message.dto.Constant;
import message.dto.MsgVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by apple on 2017/9/20.
 * 根据端口号进行消息取回
 */
public class MsgRecaption extends Thread {
    public void run() {
        try {
            while (true) {
                Vector<MsgVO> vos = MsgFactory.getMsgVOS();
                List<MsgVO> list = new ArrayList<>(vos);
                if (vos.size() <= 0) {
                    System.out.println("没有需要取回的消息，休息" + Constant.MILLIS + "ms");
                    sleep(Constant.MILLIS);
                    continue;
                }
                for (MsgVO vo : list) {
                    System.out.println(vo.getPort() + ":" + vo.getRequest());
                    vos.remove(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
