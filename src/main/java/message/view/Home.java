package message.view;

import message.client.Connect;
import message.client.Launch;
import message.dto.Group;
import message.dto.Msg;
import message.dto.User;
import message.server.Distribute;
import message.utils.DateUtils;
import message.utils.GsonUtils;

import javax.swing.*;
import java.util.Set;

/**
 * Created by apple on 2017/10/19.
 */
public class Home {
    private JPanel online;
    private JPanel history_msg;
    private JTextField port;
    private JButton connect;
    private JPanel connectJP;
    private JPanel JP;
    private JTextArea error;
    private JTextArea textArea_online;
    private JTextArea textArea_history_msg;

    public Home() {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(this.JP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //注册组件
        connect();
    }

    public void setOnline(Set<User> users) {
        //在线列表
        for (User user : users) {
            textArea_online.append(user.getName() + "[" + user.getIp() + "] \r\n");
        }
    }

    public void setHistory_msg(String history_msg) {
        //历史消息
    }

    public void setError(String msg) {
        error.append(msg + " \r\n");
    }

    public void connect() {
        //发起连接
        connect.addActionListener(e -> {
            int _port = Integer.valueOf(port.getText().trim());
            //检查本地是否存在相同端口的客户端
            if (null != Launch.getMapConnect(_port)) {
                setError("已经有一个相同的会话了");
                return;
            }
            //向服务器发起创建端口监听
            Msg msg = new Msg();
            msg.setOperate(Msg.GROUP);
            Group group = new Group();
            group.setNameGroup(_port + "");
            group.setPort(_port);
            msg.setMsg(GsonUtils.objectToJson(group));
            Launch.request(msg);
        });
    }
}
