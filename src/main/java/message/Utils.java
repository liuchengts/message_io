package message;

/**
 * Created by apple on 2017/9/20.
 */
public class Utils {
    static boolean isfag = false;

    static {
        isfag = isOS();
    }

    /***
     * 将字符串编码转换层utf-8
     * @param message
     * @return
     * @throws Exception
     */
    public static String decoding(String message) throws Exception {
        if(!isfag){
            byte[] jiema = message.getBytes("gbk");
            return new String(jiema, "utf-8");
        }
        return message;
    }

    /****
     * 判断当前系统
     * @return windows为true否则为false
     */
    public static boolean isOS() {
        boolean fag = false;
        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println("os is " + OS);
        if (OS.contains("windows")) {
            fag = true;
        }
        return fag;
    }
}
