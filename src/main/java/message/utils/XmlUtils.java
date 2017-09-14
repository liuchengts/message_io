package message.utils;

import message.dto.Data;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2017/9/14.
 */
public class XmlUtils extends Data {
    private static Map<String, Object> xml=new HashMap<String, Object>();
    public XmlUtils(String fileurl,String type){
        if(type.equals("pz")){
            readXml(fileurl);
        }
        if(xml.containsKey("ip")){
            DATA.setIp(xml.get("ip").toString());
        }else{
            //输入ip地址
            System.out.println("没有读取到ip地址");
            DATA.setIp("127.0.0.1");
        }
        if(xml.containsKey("mac")){
            DATA.setMac(xml.get("mac").toString());
        }else{
            DATA.setMac("");
        }
        if(xml.containsKey("port_Text")){
            DATA.setPort_Text(Integer.parseInt(xml.get("port_Text").toString()));
        }else{
            System.out.println("没有读取到文本端口");
            DATA.setPort_Text(DATA.TEXT_PORT);
        }
        if(xml.containsKey("port_Voice")){
            DATA.setPort_Voice(Integer.parseInt(xml.get("port_Voice").toString()));
        }else{
            DATA.setPort_Voice(DATA.VOICE_PORT);
        }
        if(xml.containsKey("port_Video")){
            DATA.setPort_Video(Integer.parseInt(xml.get("port_Voice").toString()));
        }else{
            DATA.setPort_Video(DATA.VIDEO_PORT);
        }
    }
    /*******
     * 只读取xml文件
     * @param xmlFileUrl
     */
    public  void readXml(String xmlFileUrl){
        String type=getFileType(xmlFileUrl);
        if(!type.equals("xml")){
            return ;
        }
        try {
            SAXReader sr = new SAXReader();
            Document doc = sr.read(xmlFileUrl);
            doc.accept(new VisitorSupport() {//使用观察器的子类，来完成对xml文件的读取。
                public void visit(Element el) {//利用观察期进行xml的读取。
                    if(el.getName().equalsIgnoreCase("ip")){
                        xml.put("ip", el.getText());
                    }
                    if(el.getName().equalsIgnoreCase("port_Text")){
                        xml.put("port_Text", el.getText());
                    }
                    if(el.getName().equalsIgnoreCase("port_Voice")){
                        xml.put("port_Voice", el.getText());
                    }
                    if(el.getName().equalsIgnoreCase("port_Video")){
                        xml.put("port_Video", el.getText());
                    }
                    if(el.getName().equalsIgnoreCase("mac")){
                        xml.put("mac", el.getText());
                    }
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    /******
     * 获得文件类型
     * 文件url
     */
    public static String getFileType(String xmlFileUrl){
        int index=xmlFileUrl.lastIndexOf(".");//最后一次出现的位置
        return xmlFileUrl.substring((index+1),xmlFileUrl.length()).toLowerCase();
    }
    /******
     * 获得文件类型
     * 文件
     */
    public static String getFileType(File file){
        String fname=file.getName();
        return fname.substring(fname.length()-fname.lastIndexOf("."),fname.length()).toLowerCase();
    }
}
