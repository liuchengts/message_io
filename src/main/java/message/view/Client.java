package message.view;

import message.client.Launch;
import message.dto.Group;
import message.dto.Msg;
import message.utils.GsonUtils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by apple on 2017/10/19.
 */
public class Client {
    private JFrame frame;
    private JList list;
    private JTextArea msg;
    private JTextArea msg_compile;
    private JPanel JP;
    private JToolBar tool_top;
    private JToolBar tool_base;
    private JTextArea log;
    private JPanel JP_log;
    private JButton send;
    private JButton top_add;
    private JButton top_close;
    private JTextField port;
    private JTextField name;
    private Group group;

    public Client(Group group) {
        frame = new JFrame("主页");
        frame.setContentPane(this.JP);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.group=group;
        close();
    }

    /**
     * 点击x关闭窗口
     */
    public void close() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //给服务器发送信号退出房间聊天列表
                Msg msg=new Msg();
                msg.setOperate(Msg.CLOSE);
                Launch.request(group.getPort(),msg);
                //在本地去掉当前实例信息
                Module.removeClient(group.getPort());
            }
        });
    }
}
