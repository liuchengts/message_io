package message.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by apple on 2017/9/14.
 * 右侧菜单
 */
public class Right {
    JPanel p;
    public Right(JFrame frame,JPanel panel,Integer width,Integer height) {
        p.add(new JLabel("右边内容"));
        panel.add(p);
        frame.add(panel, BorderLayout.EAST);//消息输入框
    }
}
