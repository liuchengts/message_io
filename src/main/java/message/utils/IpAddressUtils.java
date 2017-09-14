package message.utils;

import message.dto.Constant;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by apple on 2017/9/14.
 */
public class IpAddressUtils {

    /*******
     *获取外网ip
     */
    public static String getIpW() throws Exception {
        String ip = null;
        URL url = new URL("http://www.ip138.com/ip2city.asp");
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.15) Gecko/20110303 Firefox/3.6.15");
        conn.setRequestProperty("Content-Type", "text/html");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.contains("您的IP地址是")) {
                // System.out.println(line);
                int start = line.indexOf('[') + 1;
                int end = line.indexOf(']');
                ip = line.substring(start, end);
            }
        }
        br.close();
        return ip;
    }

    /*****
     * 获得本地局域网ip
     */
    public static String getIplocal() throws Exception {
        return InetAddress.getLocalHost().getHostAddress().toString();
    }

    /******
     * 获得ip地址
     * 先获取外网ip，获取不到再获取本地ip
     */
    public static String getIp() {
        String ip = null;
        try {
            ip = getIpW();
        } catch (Exception e) {
            System.out.println("外网ip地址获取失败！开始获取内网地址");
            e.printStackTrace();
        }
        if (null == ip) {
            try {
                ip = getIplocal();
            } catch (Exception e) {
                System.out.println("内网ip地址获取失败！");
                e.printStackTrace();
            }
        }
        if (null == ip) {
            ip = Constant.LOCALHOST;
            System.out.println("ip地址获取失败！，默认为:" + ip);
        }
        return ip;
    }
}
