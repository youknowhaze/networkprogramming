package com.incredible.udpchatonline;

public class UdpStudentTest {
    public static void main(String[] args) {
        UdpSenderServer udpSenderServer = new UdpSenderServer(8888,"127.0.0.1","学生");
        UdpObtainServer udpObtainServer = new UdpObtainServer(9989,"学生");

        //启动学生端消息发送服务进程
        new Thread(udpSenderServer).start();
        //启动学生端消息接收服务进程
        new Thread(udpObtainServer).start();

    }
}
