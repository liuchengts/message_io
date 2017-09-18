package message.dto;

/**
 * Created by apple on 2017/9/14.
 * 常量声明
 */
public class Constant {
    /***
     * 普通文本
     */
    public static final String  TEXT ="TEXT";
    /***
     * 语音
     */
    public static final String  VOICE ="VOICE";
    /***
     * 视频
     */
    public static final String  VIDEO ="VIDEO";

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

    /***
     * 本机ip
     */
    public static final String  LOCALHOST ="127.0.0.1";
    /***
     * 中央服务器host
     */
    public static final String  SERVERHOST ="www.modaolc.com";
    /***
     * 中央服务器端口
     */
    public static final Integer  SERVERPROT =8020;
    /****
     * 检测端口区间
     * 8000,8100
     */
    public static final  Integer[] arrayProt={8000,8019};

    /***
     * 超时时间
     */
    public static final Integer  TIMEOUT =10000;

    /***
     * 线程休眠时间长
     */
    public static final long  MILLIS =1000;
    /***
     * 端口维护线程休眠时间长
     */
    public static final long  MILLIS_PROT =100;
}
