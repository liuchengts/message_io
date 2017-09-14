package message.dto;
import java.io.File;

/******
 * 所有消息机制的基类
 * @author Administrator
 *
 */
public class MsgBase {
	private static MsgBase msgBase = null;
	static {
		if(null==msgBase){
			msgBase = new MsgBase();
		}
	}
	private MsgBase (){}
	public static MsgBase getMsgBase() {
		return msgBase;
	}

	private  String   ip;//ip
	private  Integer  port_Text;//TEXT端口号
	private  Integer  port_Voice;//音频端口号
	private  Integer  port_Video;//视频端口号
	private  String   mac;//网卡地址
	private  Integer  status;//请求通信状态
	private  String   request;//返回消息内容
	private  String   response;//发送消息内容
	private  File   MFileVoiceReq;//返回消息 音频文件
	private  File   MFileVideoReq;//返回消息视频文件
	private  Integer  MPlayVoiceStatusReq;//返回消息 当前音频播放完成状态
	private  Integer  MPlayVideoStatusReq;//返回消息 当前视频播放完成状态
	private  File  MFileVoiceRes;//发送消息 音频文件
	private  Integer  MFileVideoRes;// 发送消息 当前视频文件
	private  Integer  MPlayVoiceStatusRes;//返回消息 当前音频播放完成状态
	private  Integer  MPlayVideoStatusRes;//返回消息 当前视频播放完成状态
	private  Integer  MPlayVoiceAuthorization;//音频消息授权
	private  Integer  MPlayVideoAuthorization;//视频消息授权
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort_Text() {
		return port_Text;
	}
	public void setPort_Text(Integer port_Text) {
		this.port_Text = port_Text;
	}
	public Integer getPort_Voice() {
		return port_Voice;
	}
	public void setPort_Voice(Integer port_Voice) {
		this.port_Voice = port_Voice;
	}
	public Integer getPort_Video() {
		return port_Video;
	}
	public void setPort_Video(Integer port_Video) {
		this.port_Video = port_Video;
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

	/***
	 * 普通文本端口
	 */
	public static final Integer  TEXT_PORT =9090;
	/***
	 * 语音端口
	 */
	public static final Integer  VOICE_PORT =9091;
	/***
	 * 视频端口
	 */
	public static final Integer  VIDEO_PORT =9092;
	/****
	 * 音频消息授权  成功  1
	 */
	public static final  Integer  MPLAYVOICEAUTHORIZATION_YES=1;
	/****
	 * 视频消息授权  成功  1
	 */
	public static final  Integer  MPLAYVIDEOAUTHORIZATION_YES=1;
	/****
	 * 音频消息授权  失败  2
	 */
	public static final  Integer  MPLAYVOICEAUTHORIZATION_NO=2;
	/****
	 * 视频消息授权  失败  2
	 */
	public static final  Integer  MPLAYVIDEOAUTHORIZATION_NO=2;
	/****
	 * 配置文件默认地址
	 */
//	public static final  String  pzxml="c:/setting..xml";
	public static final  String  pzxml="/Users/apple/Documents/lc/work/message_io/src/main/resources/setting.xml";

}
