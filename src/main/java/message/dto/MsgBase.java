package message.dto;

import java.io.File;

/******
 * 所有消息的基类
 * @author lc
 *
 */
public class MsgBase {
    private String ip;//目标ip
    private Integer port;//端口号
    private String mac;//网卡地址
    private Integer status;//请求通信状态
    private String request;//返回消息内容
    private String response;//发送消息内容
    private File MFileVoiceReq;//返回消息 音频文件
    private File MFileVideoReq;//返回消息视频文件
    private Integer MPlayVoiceStatusReq;//返回消息 当前音频播放完成状态
    private Integer MPlayVideoStatusReq;//返回消息 当前视频播放完成状态
    private File MFileVoiceRes;//发送消息 音频文件
    private Integer MFileVideoRes;// 发送消息 当前视频文件
    private Integer MPlayVoiceStatusRes;//返回消息 当前音频播放完成状态
    private Integer MPlayVideoStatusRes;//返回消息 当前视频播放完成状态
    private Integer MPlayVoiceAuthorization;//音频消息授权
    private Integer MPlayVideoAuthorization;//视频消息授权

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public File getMFileVoiceReq() {
        return MFileVoiceReq;
    }

    public void setMFileVoiceReq(File mFileVoiceReq) {
        MFileVoiceReq = mFileVoiceReq;
    }

    public File getMFileVideoReq() {
        return MFileVideoReq;
    }

    public void setMFileVideoReq(File mFileVideoReq) {
        MFileVideoReq = mFileVideoReq;
    }

    public Integer getMPlayVoiceStatusReq() {
        return MPlayVoiceStatusReq;
    }

    public void setMPlayVoiceStatusReq(Integer mPlayVoiceStatusReq) {
        MPlayVoiceStatusReq = mPlayVoiceStatusReq;
    }

    public Integer getMPlayVideoStatusReq() {
        return MPlayVideoStatusReq;
    }

    public void setMPlayVideoStatusReq(Integer mPlayVideoStatusReq) {
        MPlayVideoStatusReq = mPlayVideoStatusReq;
    }

    public File getMFileVoiceRes() {
        return MFileVoiceRes;
    }

    public void setMFileVoiceRes(File mFileVoiceRes) {
        MFileVoiceRes = mFileVoiceRes;
    }

    public Integer getMFileVideoRes() {
        return MFileVideoRes;
    }

    public void setMFileVideoRes(Integer mFileVideoRes) {
        MFileVideoRes = mFileVideoRes;
    }

    public Integer getMPlayVoiceStatusRes() {
        return MPlayVoiceStatusRes;
    }

    public void setMPlayVoiceStatusRes(Integer mPlayVoiceStatusRes) {
        MPlayVoiceStatusRes = mPlayVoiceStatusRes;
    }

    public Integer getMPlayVideoStatusRes() {
        return MPlayVideoStatusRes;
    }

    public void setMPlayVideoStatusRes(Integer mPlayVideoStatusRes) {
        MPlayVideoStatusRes = mPlayVideoStatusRes;
    }

    public Integer getMPlayVoiceAuthorization() {
        return MPlayVoiceAuthorization;
    }

    public void setMPlayVoiceAuthorization(Integer mPlayVoiceAuthorization) {
        MPlayVoiceAuthorization = mPlayVoiceAuthorization;
    }

    public Integer getMPlayVideoAuthorization() {
        return MPlayVideoAuthorization;
    }

    public void setMPlayVideoAuthorization(Integer mPlayVideoAuthorization) {
        MPlayVideoAuthorization = mPlayVideoAuthorization;
    }


}
