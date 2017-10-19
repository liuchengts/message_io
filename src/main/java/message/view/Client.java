package message.view;

import javax.swing.*;

/**
 * Created by apple on 2017/10/19.
 */
public class Client {
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

    public Client(){
        JFrame frame = new JFrame("主页");
        frame.setContentPane(new Client().JP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
