package message.dto;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by apple on 2017/10/16.
 * 客户端对象
 */
@Data
public class Client {
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;

    public Client(Socket socket) throws  Exception{
        this.socket = socket;// 根据端口号和服务器ip建立连接
        this.writer = new PrintWriter(socket.getOutputStream());
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
        try {
            if(!socket.isClosed()){
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
