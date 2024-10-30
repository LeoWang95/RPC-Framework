package com.leowang.register;

import com.leowang.common.URL;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRemoteRegister {
    private static Map<String, List<URL>> map = new HashMap();

    public MapRemoteRegister() {
    }

    public static void regist(String interfaceName, URL url) {
        List<URL> list = (List)map.get(interfaceName);
        if (list == null) {
            list = new ArrayList();
        }

        list.add(url);
        map.put(interfaceName, list);
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        map = getFile();
        return map.get(interfaceName);
    }

    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);
        } catch (IOException var2) {
            IOException e = var2;
            e.printStackTrace();
        }

    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map)objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException var2) {
            Exception e = var2;
            e.printStackTrace();
            return null;
        }
    }
}
