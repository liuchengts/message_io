package message.dto;

import lombok.Data;

/**
 * Created by apple on 2017/10/16.
 * 返回
 */
@Data
public class Res {
    private  boolean fag;
    private String msg;
    private Object object;

    public Res() {
        this.fag = true;
        this.msg = "操作成功";
    }

    public Res(boolean fag, String msg, Object object) {
        this.fag = fag;
        this.msg = msg;
        this.object = object;
    }
}
