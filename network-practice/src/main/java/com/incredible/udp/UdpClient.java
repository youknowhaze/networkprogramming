package com.incredible.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 模拟udp传输，udp是不需要服务端的，但是为了演示接受数据，
 * 这里我们也创建一个服务端来接收数据
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        String msg = "测试udp通信";
        DatagramPacket packet = new DatagramPacket(msg.getBytes(),0,
                msg.getBytes().length,InetAddress.getByName("127.0.0.1"),9002);

        datagramSocket.send(packet);
        datagramSocket.close();
    }
}
