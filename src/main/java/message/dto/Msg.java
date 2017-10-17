package message.dto;

import lombok.Data;

/**
 * Created by apple on 2017/10/17.
 */
@Data
public class Msg {
    public static String GROUP = "group";
    public static String CLOSE = "close";

    private String name;//昵称
    private String operate;//操作
    private String msg;//消息内容
}
