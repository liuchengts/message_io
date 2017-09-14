package message.server.socket;

import message.dto.Data;

import java.io.*;
import java.net.Socket;

/**
 * Created by apple on 2017/9/14.
 */
public class SendCore extends Data{
    public static final int LOCAL_PORT = 5678;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    public SendCore(){
        start();
    }
    public void run() {
        System.out.println("发送线程启动...");
        String response;
        Integer  port_Text=DATA.getPort_Text();//TEXT端口号
        String  ip=DATA.getIp();//ip地址
        System.out.println("发送ip："+ip);
        System.out.println("发送端口号："+port_Text);
        while(true){
            //每次都去重新获得文本消息内容，确保数据是最新的
            response=DATA.getResponse();//发送消息内容
            if(null==response){
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    throw new RuntimeException("发送线程睡眠出现意外",e);
                }
                continue;
            }
            try {
                //客户端socket指定服务器的地址和端口号
                socket = new Socket(ip, port_Text);
                System.out.println("发送端ip："+socket.getInetAddress().getHostAddress());
            } catch (Exception e) {
                throw new RuntimeException("发送线程无法建立socket连接",e);
            }
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                pw.println(response);
                pw.flush();
                //重新初始化消息发送参数
                DATA.setResponse(null);
            } catch (IOException e) {
                throw new RuntimeException("消息发送失败",e);
            }
        }
    }
}
