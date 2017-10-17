package message.server;


import message.dto.*;
import message.utils.GsonUtils;

import java.net.Socket;
import java.util.*;

/**
 * Created by apple on 2017/10/11.
 * 注册中心
 */
public class Register {
    private static Map<Integer, Map<String, Client>> mapClient = new HashMap<>();
    private static List<Group> listGroup = new ArrayList<>();
    private static Set<User> listUser = new HashSet<>();
    /**
     * 客户端向服务器注册
     * @param key    端口号
     * @param msg   信息
     * @param socket 当前连接信息
     * @return false表示存在了相同名称的用户，true表示成功
     */
    public static Res regClient(Integer key, Msg msg, Socket socket) throws Exception {
        Map<String, Client> map = new HashMap<>();
        Res res = new Res();
        Client client = new Client(socket);
        res.setObject(client);
        if (!mapClient.containsKey(key)) {
            map.put(msg.getName(), client);
            mapClient.put(key, map);
        } else {
            map = mapClient.get(key);
            if (map.containsKey(msg.getName())) {
                res.setFag(false);
                res.setMsg("相同用户名【" + msg.getName() + "】已存在");
                return res;
            }
            mapClient.get(key).put(msg.getName(), client);
        }
        //将带入的user对象更新到缓存
        User user= (User)GsonUtils.jsonToObject(msg.getMsg(),User.class);
        listUser.add(user);
        return res;
    }

    /***
     * 获得在线列表
     * @return
     */
    public static Set<User> getUsers() {
        return listUser;
    }

    /***
     * 获得端口号在线客户端
     * @return
     */
    public static Map<String, Client> getClients(Integer port) {
        if(!mapClient.containsKey(port)){
            return null;
        }
        return mapClient.get(port);
    }

    /***
     * 获得端口号在线列表
     * @return
     */
    public static Set<User> getUsers(Integer port) {
        Set<User> list = new HashSet<>();
        for (Integer in : mapClient.keySet()) {
            if (!in.equals(port)) {
                continue;
            }
            for (String name : mapClient.get(in).keySet()) {
                User user = new User();
                user.setName(name);
                user.setIp(mapClient.get(in).get(name).getSocket().getLocalAddress().toString());
                list.add(user);
            }
        }
        return list;
    }

    /***
     * 获得在线聊天组
     * @return
     */
    public static List<Group> getGroup() {
        return listGroup;
    }

    /**
     * 用户离开port房间
     *
     * @param name 用户昵称
     * @param port 端口号
     * @return true成功
     */

    public static boolean leaveUser(String name, Integer port) {
        try {
            mapClient.get(port).get(name).getSocket().close();
            mapClient.get(port).remove(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
