package message.dto;

import lombok.Data;
import message.client.Launch;
import message.utils.GsonUtils;

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
    private boolean fag = true;

    public Client(Socket socket) throws Exception {
        this.socket = socket;// 根据端口号和服务器ip建立连接
        this.writer = new PrintWriter(socket.getOutputStream());
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * 关闭客户端
     */
    public void close() {
        try {
            //通知服务器断开服务
            Msg msg=new Msg();
            msg.setOperate(Msg.CLOSE);
            sendMessage(msg);
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
            fag=false;
            Launch.removeMapConnect(socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(Msg msg) {
        writer.println(GsonUtils.objectToJson(msg));
        writer.flush();
        try {
            if (!socket.isClosed()) {
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
