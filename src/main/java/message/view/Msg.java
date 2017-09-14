//package message.view;
//
//import message.server.socket.AcceptoCore;
//import message.server.socket.SendCore;
//import message.utils.DateUtils;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * Created by apple on 2017/9/14.
// * 消息视图
// */
//public class Msg {
//    public static JTextArea ta1,ta2;
//    private DateUtils da=new DateUtils();
//    private static AcceptoCore acceptoThread=new AcceptoCore();
//    private static SendCore sendThread=new SendCore();
//    public Msg(JFrame frame,JPanel panel,Integer width,Integer height) {
//        //panel.add(new JLabel("消息显示："));
//        ta1=new JTextArea(6,15);//6行15列
//        ta1.setLineWrap(true);// 设置文本区的换行策略
//        ta1.setWrapStyleWord(true);// 激活断行不断字功能
//        ta1.setFont(new Font("标楷体", Font.BOLD, 12));  //设置当前字体
//        ta1.setTabSize(2);//使用setTabSize()方法设置[Tab]键的跳离距离
//        ta1.setEditable(false);
//        JScrollPane scrollPane1=new JScrollPane(ta1);
//        scrollPane1.setPreferredSize(new Dimension(width-80,height-300));
//        panel.add(scrollPane1);
//        panel.setVisible(true);
//        //创建消息输入面板
//        //panel.add(new JLabel("消息输入："));
//        ta2=new JTextArea(6,15);//6行15列
//        ta2.setLineWrap(true);// 设置文本区的换行策略
//        ta2.setWrapStyleWord(true);// 激活断行不断字功能
//        ta2.setFont(new Font("标楷体", Font.BOLD, 16));  //设置当前字体
//        ta2.setTabSize(2);//使用setTabSize()方法设置[Tab]键的跳离距离
//        JScrollPane scrollPane2=new JScrollPane(ta2);
//        scrollPane2.setPreferredSize(new Dimension(width-80,height-585));
//        panel.add(scrollPane2);
//        panel.setVisible(true);
//        frame.add(panel);//消息输入框
//        //发送按钮
//        JButton button = new JButton("发送");
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                msgResponse(ta2.getText());
//            }
//        });
//        frame.add(button, BorderLayout.SOUTH);//发送按钮添加到窗口中 SOUTH下
//        //接受消息的线程需要在页面初始化完成后启动
//        msgRequest();
//    }
//    //把服务器接受的消息显示到页面
//    public void msgRequest(){
//        new Thread(){
//            public void run() {
//                System.out.println("接受服务器消息到页面线程启动......");
//                String  request;
//                while (true) {
//                    request=DATA.getRequest();
//                    if(null==request ||request.trim().length()<=0){
//                        try {
//                            this.sleep(1000);
//                        } catch (InterruptedException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                        continue;
//                    }else{
//                        ta1.append(DATA.getIp()+da.getDate_sj()+DATA.getRequest());
//                        ta1.append("\n");
//                        DATA.setRequest(null);
//                    }
//                }
//            }
//        }.start();
//    }
//    //将输入的消息显示到页面
//    public void msgResponse(String msg){
//        if(null==msg ||msg.trim().length()<=0){
//            return;
//        }
//        DATA.setResponse(msg);
//        ta1.append(da.getDate_sj()+DATA.getResponse());
//        ta1.append("\n");
//        ta2.setText("");
//    }
//}
