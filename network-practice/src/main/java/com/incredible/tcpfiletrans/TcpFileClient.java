package com.incredible.tcpfiletrans;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 模拟文件传输客户端
 */
public class TcpFileClient {
    public static void main(String[] args) throws Exception {
        //建立socket连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9992);
        // socket文件输出流
        OutputStream os = socket.getOutputStream();
        // 文件读取流，读取指定路径文件
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\incredible\\Desktop\\1.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        //将文件流中的byte字节通过socket输出流，通信传输到服务端
        while((len = fis.read(buffer))!= -1){
            os.write(buffer,0,len);
        }
        // 告知服务端通讯结束
        socket.shutdownOutput();
        os.close();
        fis.close();
        socket.close();
    }
}
