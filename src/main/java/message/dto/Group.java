package message.dto;

import lombok.Data;

import java.net.ServerSocket;
import java.util.Set;

/**
 * Created by apple on 2017/10/11.
 * 聊天组
 */
@Data
public class Group {
    private Integer port;
    private String name;
    private String nameGroup;
    private ServerSocket serverSocket;
    private String msg;
    private Set<User> users;
}
