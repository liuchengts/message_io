package message.dto;

/**
 * Created by apple on 2017/9/20.
 */
public class MsgVO {

    private Integer port;//端口号
    private String request;//返回消息内容

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    public MsgVO() {
    }
    public MsgVO(Integer port, String request) {
        this.port = port;
        this.request = request;
    }
}
