package com.incredible.tcp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟tcp服务端
 */
public class TcpService {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // 指明socket服务
            serverSocket = new ServerSocket(9999);
            System.out.println("-------启动ServerSocket------");
            // 一直接受消息
           while (true){
               // 得到socket进程
               socket = serverSocket.accept();
               // 得到socket流
               is = socket.getInputStream();
               // 声明一个缓冲输入流
               baos = new ByteArrayOutputStream();
               byte[] buffer = new byte[1024];
               int len;
               while ((len = is.read(buffer)) != -1){
                   // 将socket中的字节放入缓冲流中，最后进行一次性输出，而不是一段一段的输出
                   baos.write(buffer,0,len);
               }
               System.out.println(baos.toString());
           }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if (baos != null){
                    baos.close();
                }
                if (is != null){
                    is.close();
                }
                if (socket !=null){
                    socket.close();
                }
                if (serverSocket != null){
                    serverSocket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
