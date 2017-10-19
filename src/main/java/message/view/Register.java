package message.view;


import message.client.Launch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by apple on 2017/10/19.
 */
public class Register {
    private JTextField name;
    private JButton save;
    private JPanel JP;
    private JTextArea error;


    public static void init() {
        JFrame frame = new JFrame("欢迎使用");
        frame.setContentPane(new Register().JP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setError(String msg) {
        error.append(msg);
    }

    public void save() {
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nick = name.getText().trim();
                String res = Launch.launchConnect(nick);
                if (null != res) {
                    error.append(res);
                    return;
                }
                new Home();
            }
        });
    }

}
