package com.incredible.tcpfiletrans;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟tcp文件传输fuw端
 */
public class TcpFileService {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9992);
        System.out.println("===========socket服务端开启===========");
        //得到socket连接
        Socket socket = serverSocket.accept();
        //接受socket字节流
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len;

        //文件输出流
        FileOutputStream fos = new FileOutputStream(new File("test.jpg"));
        while ((len = is.read(buffer))!=-1){
            //将socket传输的字节流全部输入至文件流中
            fos.write(buffer,0,len);
        }

        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
        System.out.println("=============socket服务端关闭==============");

    }
}
