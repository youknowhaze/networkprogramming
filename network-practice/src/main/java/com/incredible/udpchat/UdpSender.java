package com.incredible.udpchat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

/**
 * udp消息发送方
 */
public class UdpSender {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        BufferedReader bufferedReader = null;
        while (true){
            // 获取控制台的输入，并读取为流
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            // 输入内容读取为字符
            String inputData = bufferedReader.readLine();
            // 数据发送包
            DatagramPacket packet = new DatagramPacket(inputData.getBytes(),0,
                    inputData.getBytes().length,new InetSocketAddress("127.0.0.1",9090));
            // udp协议发送数据
            datagramSocket.send(packet);

            if (inputData.equals("bye")){
                break;
            }
        }
        bufferedReader.close();
        //关闭socket连接
        datagramSocket.close();
    }
}
