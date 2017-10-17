package message.server;


import message.dto.Client;
import message.dto.Group;
import message.dto.Res;
import message.dto.User;

import java.net.Socket;
import java.util.*;

/**
 * Created by apple on 2017/10/11.
 * 注册中心
 */
public class Register {
    private static Map<Integer, Map<String, Client>> mapClient = new HashMap<>();
    private static List<Group> listGroup = new ArrayList<>();

    /**
     * 在指定的端口中塞入一个socket
     *
     * @param key    端口号
     * @param name   用户昵称
     * @param socket 当前连接信息
     * @return false表示存在了相同名称的用户，true表示成功
     */
    public static Res regClient(Integer key, String name, Socket socket) throws Exception {
        Map<String, Client> map = new HashMap<>();
        Res res = new Res();
        Client client = new Client(socket);
        res.setObject(client);
        if (!mapClient.containsKey(key)) {
            map.put(name, client);
            mapClient.put(key, map);
        } else {
            map = mapClient.get(key);
            if (map.containsKey(name)) {
                res.setFag(false);
                res.setMsg("相同用户名【" + name + "】已存在");
                return res;
            }
            mapClient.get(key).put(name, client);
        }
        return res;
    }

    /***
     * 获得在线列表
     * @return
     */
    public static Set<User> getUsers() {
        Set<User> list = new HashSet<>();
        for (Map<String, Client> map : mapClient.values()) {
            for (String name : map.keySet()) {
                User user = new User(name, map.get(name).getSocket().getLocalAddress().toString());
                list.add(user);
            }
        }
        return list;
    }

    /***
     * 获得端口号在线客户端
     * @return
     */
    public static Map<String, Client> getClients(Integer port) {
        for (Integer in : mapClient.keySet()) {
            if (!in.equals(port)) {
                continue;
            }
            return mapClient.get(in);
        }
        return null;
    }

    /***
     * 获得端口号在线列表
     * @return
     */
    public static List<User> getUsers(Integer port) {
        List<User> list = new ArrayList<>();
        for (Integer in : mapClient.keySet()) {
            if (!in.equals(port)) {
                continue;
            }
            for (String name : mapClient.get(in).keySet()) {
                User user = new User(name, mapClient.get(in).get(name).getSocket().getLocalAddress().toString());
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
