package message.view;

import javax.swing.*;

/**
 * Created by apple on 2017/9/14.
 * 菜单
 */
public class Menu {
    public static JMenuBar menubar1;
    public static JMenu menu1,menu2,menu3,menu4,menu5;
    public static JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12;
    public static JToolBar bar;
    public static JButton button1,button2,button3,button4,button5,button6;

    public Menu(JFrame frame,JPanel p) {
        menubar1 = new JMenuBar();
        frame.setJMenuBar(menubar1);
        menu1 = new JMenu("菜单1");
        menu2 = new JMenu("菜单2");
        menu3 = new JMenu("菜单3");
        menu4 = new JMenu("菜单4");
        menu5 = new JMenu("菜单5");
        menubar1.add(menu1);
        menubar1.add(menu2);
        menubar1.add(menu3);
        menubar1.add(menu4);
        menubar1.add(menu5);
        item1 = new JMenuItem("子菜单1");
        item2 = new JMenuItem("子菜单2");
        item3 = new JMenuItem("子菜单3");
        item4 = new JMenuItem("子菜单4");
        item5 = new JMenuItem("子菜单5");
        item6 = new JMenuItem("子菜单6");
        item7 = new JMenuItem("子菜单7");
        item8 = new JMenuItem("子菜单8");
        item9 = new JMenuItem("子菜单9");
        item10 = new JMenuItem("子菜单10");
        item11 = new JMenuItem("子菜单11");
        item12 = new JMenuItem("子菜单12");
        menu1.add(item1);
        menu1.addSeparator();
        menu1.add(item2);
        menu1.addSeparator();
        menu1.add(item3);
        menu2.add(item4);
        menu2.addSeparator();
        menu2.add(item5);
        menu3.add(item6);
        menu3.addSeparator();
        menu3.add(item7);
        menu4.add(item8);
        menu4.addSeparator();
        menu4.add(item9);
        menu4.addSeparator();
        menu4.add(item10);
        menu5.add(item11);
        menu5.addSeparator();
        menu5.add(item12);
        button1 = new JButton("工具1");
        button2 = new JButton("工具2");
        button3 = new JButton("工具3");
        button4 = new JButton("工具4");
        button5 = new JButton("工具5");
        button6 = new JButton("工具6");
        bar = new JToolBar();
        bar.add(button1);
        bar.add(button2);
        bar.add(button3);
        bar.add(button4);
        bar.add(button5);
        bar.add(button6);
        p.add("North",bar);
        frame.add(p);
    }
}
