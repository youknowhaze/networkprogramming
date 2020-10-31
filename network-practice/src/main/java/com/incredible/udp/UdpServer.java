package com.incredible.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 模拟udp传输，udp是不需要服务端的，但是为了演示接受数据，
 * 这里我们也创建一个服务端来接收数据
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9002);
        byte[] buffer = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buffer,0,buffer.length);
        socket.receive(datagramPacket);
        System.out.println("=====  "+new String(datagramPacket.getData(),0,datagramPacket.getLength()));

    }
}
