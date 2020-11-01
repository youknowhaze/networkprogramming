package com.incredible.filedown;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 通过网络地址下载文件（视频、音乐、文件）
 */
public class FileDownByUrl {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://p4.music.126.net/YeFiNgHX3XctdJRbsmuICQ==/109951164250805104.jpg?param=50y50");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        FileOutputStream fos = new FileOutputStream(new File("incredible.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        fos.close();
        inputStream.close();

    }
}
