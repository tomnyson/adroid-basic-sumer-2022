package com.example.android_summer_2022.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Helper {
    public static final  String EMAIL = "tabletkindfire@gmail.com";
    public static final String PASSWORD = "lkbivmsskcnzpgmb";

    public static byte[] getBytes(InputStream inputStream){
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while (true) {
            try {
                if (!((len = inputStream.read(buffer)) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
