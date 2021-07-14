package com.waters.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
    public static byte[] getStringFromFile(String path) throws IOException {
        FileInputStream fileIs = null;
        try {
            fileIs = new FileInputStream(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = fileIs.available(); //得到文件大小
        byte data[] = new byte[i];
        fileIs.read(data); //读数据
        fileIs.close();
        return data;
    }
}
