package com.incredible.udpchat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 数据接收方
 */
public class UdpWatcher {
    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket(9090);
        System.out.println("-----------进入控制台-----------");
        while (true){
            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buffer,0,buffer.length);
            datagramSocket.receive(datagramPacket);
            String data = new String(datagramPacket.getData(),0,buffer.length);
            System.out.println("udp信息: " + data);
            if (data.equals("bye")){
                break;
            }
        }
        datagramSocket.close();
    }
}
