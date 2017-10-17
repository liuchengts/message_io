package message.dto;

import lombok.Data;

/**
 * Created by apple on 2017/10/11.
 * 客户端信息
 */
@Data
public class User {
    private String name;
    private String ip;

    public User(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }
}
