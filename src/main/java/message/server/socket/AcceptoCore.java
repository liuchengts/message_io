package message.server.socket;

import message.dto.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by apple on 2017/9/14.
 * 文本消息接收
 */
public class AcceptoCore extends Data{private ServerSocket s;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    public AcceptoCore(){
        start();
    }
    public void run() {
        System.out.println("服务端线程启动...");
        Integer  port_Text=DATA.getPort_Text();//TEXT端口号
        System.out.println("服务端线程监听端口号："+port_Text);
        try {
            s = new ServerSocket(port_Text);
            //等待新请求、否则一直阻塞
            int i=1;
            while(true){
                socket = s.accept();
                System.out.println("接收端ip："+socket.getInetAddress().getHostAddress());
                if(null==socket){
                    try {
                        this.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    continue;
                }
                System.out.println("socket文本消息服务端线程接受到第"+i+"个请求，socket:"+socket);

                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

                try {
                    String str = br.readLine();
                    if(null==str){
                        try {
                            this.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        continue;
                    }
                    //输出视图
                    DATA.setRequest(str);
                    System.out.println("服务器接受到的消息 :"+str);
                    pw.flush();
                } catch (Exception e) {
                }
                i++;
            }
        } catch (Exception e) {
            try {
                br.close();
                pw.close();
                socket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
