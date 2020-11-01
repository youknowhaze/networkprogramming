package com.incredible.udpchatonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * udp通信消息发送服务进程
 */
public class UdpSenderServer implements Runnable{

    private DatagramSocket datagramSocket = null;
    private BufferedReader bufferedReader = null;
    private int toPort;
    private String toIp;
    private String name;

    public UdpSenderServer(int toPort, String toIp,String name) {
        this.toPort = toPort;
        this.toIp = toIp;
        this.name = name;
        try {
            datagramSocket = new DatagramSocket();
            // 得到控制台输入流
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                // 转换为控制台输入字符串
                String inputData = bufferedReader.readLine();
                DatagramPacket packet = new DatagramPacket(inputData.getBytes(),
                        0,inputData.getBytes().length,new InetSocketAddress(toIp,toPort));

                datagramSocket.send(packet);
                if ("bye".equals(inputData)){
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
