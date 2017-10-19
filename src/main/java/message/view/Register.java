package message.view;


import message.client.Launch;

import javax.swing.*;

/**
 * Created by apple on 2017/10/19.
 */
public class Register {
    private JFrame frame;
    private JTextField name;
    private JButton save;
    private JPanel JP;
    private JTextArea error;

    public Register() {
        frame = new JFrame("欢迎使用");
        frame.setContentPane(this.JP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //注册组件
        save();

    }

    public void setError(String msg) {
        error.append(msg + " \r\n");
    }

    public void save() {
        save.addActionListener(e -> {
            String nick = name.getText().trim();
            String res = Launch.launchConnect(nick);
            if (null != res) {
                setError(res);
                hide();
                return;
            }
        });
    }

    private void hide() {
        System.out.println("home");
    }
}
