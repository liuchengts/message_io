package message.view;

import message.dto.Data;

import javax.swing.*;

/**
 * Created by apple on 2017/9/14.
 * 窗口组件注册
 */
public class WinControl extends Data {
    public static JFrame frame;
    public static JPanel panel;
    public static Integer width;
    public static Integer height;
    public static  Msg msgView;
    public static  Menu menuView;
    public static  Right rightView;
    public void control(){
        msgView=new Msg(frame, panel, width, height);
        menuView=new Menu(frame, panel);
        // rightView=new RightView(frame, panel, width, height);
    }
}
