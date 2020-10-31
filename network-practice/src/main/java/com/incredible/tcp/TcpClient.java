package com.incredible.tcp;


import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 模拟tcp客户端
 */
public class TcpClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;
        try {
            // 获取ip地址
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress,9999);
            os = socket.getOutputStream();
            os.write("this is a test message".getBytes());
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if (os != null){
                    os.close();
                }
                if (socket != null){
                    socket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
