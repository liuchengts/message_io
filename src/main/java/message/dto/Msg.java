package message.dto;

import lombok.Data;

/**
 * Created by apple on 2017/10/17.
 */
@Data
public class Msg {
    public static String GROUP = "group";
    public static String CLOSE = "close";
    public static String ERROR = "error";
    public static String BACK = "back";
    public static String END = "@end";


    private String name;//昵称
    private String operate;//操作
    private String msg;//消息内容

//    public String getMsg() {
//        return MixUtils.decode(msg);
//    }
//
//    public void setMsg(String msg) {
//        this.msg = MixUtils.encryption(msg);
//    }
}
