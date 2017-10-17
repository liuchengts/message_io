package message.utils;

/**
 * Created by apple on 2017/10/17.
 * 加密和解密
 */
public class MixUtils {

    static RSAUtils rsaUtils = RSAUtils.getInstance();

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encryption(String str) {
        try {
            return rsaUtils.encryption(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
            return rsaUtils.decode(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "你好";
        String estr = encryption(str);
        System.out.println("加密：" + estr);
        String dstr = decode(estr);
        System.out.println("解密：" + dstr);
        System.out.println("原文:" + str);
    }

}
