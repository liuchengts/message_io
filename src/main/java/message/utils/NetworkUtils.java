package message.utils;

import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by apple on 2017/9/14.
 * 网卡工具包
 */
public class NetworkUtils {

    /***
     * 获得当前网卡物理地址
     * @return
     */
    public static String getPhysicalNetworkMAC() {
        String networkMAC = null;
        try {
            Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
            StringBuilder builder = new StringBuilder();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null) {
                    continue;
                }
                if (builder.length() > 0) {
                    builder.append(",");
                }
                for (byte b : mac) {

                    //convert to hex string.
                    String hex = Integer.toHexString(0xff & b).toUpperCase();
                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }
                    builder.append(hex);
                    builder.append("-");
                }
                builder.deleteCharAt(builder.length() - 1);
            }

            if (builder.length() != 0) {
                networkMAC = builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return networkMAC;
    }
}