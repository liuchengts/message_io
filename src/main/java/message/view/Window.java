//package message.view;
//
//import message.utils.XmlUtils;
//
//import javax.swing.*;
//import java.awt.*;
//
///****
// * 聊天窗口
// * @author lc
// * 2015年4月14日
// *
// */
//public class Window extends WinControl{
//    public Window(){
//        //加载文件信息
//        xml();
//        // 创建窗体
//        frame = new JFrame("Socket Client窗口");
//        frame.setLayout(new BorderLayout());
//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        width = d.width/2+200;
//        height = d.height/2+200;
//        frame.setSize(width, height);
//        frame.setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
//        panel=new JPanel();
//        //调用注册组件===========================================
//        this.control();
//        //===================================================
//        frame.add(panel);//消息输入框
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
//    }
//    public void xml(){
//        //预读取配置文件
//        new XmlUtils(DATA.pzxml,"pz");
//    }
//}
