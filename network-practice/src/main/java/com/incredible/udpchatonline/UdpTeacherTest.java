package com.incredible.udpchatonline;

public class UdpTeacherTest {
    public static void main(String[] args) {
        UdpSenderServer udpSenderServer = new UdpSenderServer(9989,"127.0.0.1","老师");
        UdpObtainServer udpObtainServer = new UdpObtainServer(8888,"老师");

        //启动老师端消息发送进程服务
        new Thread(udpSenderServer).start();
        //启动老师端消息接收进程服务
        new Thread(udpObtainServer).start();
    }
}
