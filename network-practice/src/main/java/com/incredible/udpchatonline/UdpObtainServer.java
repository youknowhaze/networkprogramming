package com.incredible.udpchatonline;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * udp通信方式获取信息
 */
public class UdpObtainServer implements Runnable{

    private DatagramSocket datagramSocket = null;

    private int port;
    private String name;

    public UdpObtainServer(int port, String name) {
        this.port = port;
        this.name = name;
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

            try {
                datagramSocket.receive(packet);
                String data = new String(packet.getData(),0,buffer.length);
                System.out.println(name+" : "+data);
                if ("bye".equals(data)){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        datagramSocket.close();

    }
}
