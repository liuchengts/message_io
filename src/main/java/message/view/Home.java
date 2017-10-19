package message.view;

import message.client.Launch;
import message.dto.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        error.append(msg);
    }

    public void connect() {
        //发起连接
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int _port = Integer.valueOf(port.getText().trim());
                String res = Launch.launchConnect(_port, null);
                if (null != res) {
                    setError(res);
                    return;
                }
                Module.getClient(_port);
            }
        });
    }
}
