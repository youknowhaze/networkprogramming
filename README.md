## 网络编程学习demo

### 一、tcp
创建两个类，模拟tcp的两端，使用java socket建立tcp通信

#### 1、服务端

````java
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
````

#### 2、客户端
````java
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
````

### 二、tcp文件传输
通过tcp协议进行文件传输

#### 1、客户端
````java
/**
 * 模拟文件传输客户端
 */
public class TcpFileClient {
    public static void main(String[] args) throws Exception {
        //建立socket连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9992);
        // socket文件输出流
        OutputStream os = socket.getOutputStream();
        // 文件读取流，读取指定路径文件
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\incredible\\Desktop\\1.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        //将文件流中的byte字节通过socket输出流，通信传输到服务端
        while((len = fis.read(buffer))!= -1){
            os.write(buffer,0,len);
        }
        // 告知服务端通讯结束
        socket.shutdownOutput();
        os.close();
        fis.close();
        socket.close();
    }
}
````

#### 2、服务端
````java
/**
 * 模拟tcp文件传输fuw端
 */
public class TcpFileService {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9992);
        System.out.println("===========socket服务端开启===========");
        //得到socket连接
        Socket socket = serverSocket.accept();
        //接受socket字节流
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len;

        //文件输出流
        FileOutputStream fos = new FileOutputStream(new File("test.jpg"));
        while ((len = is.read(buffer))!=-1){
            //将socket传输的字节流全部输入至文件流中
            fos.write(buffer,0,len);
        }

        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
        System.out.println("=============socket服务端关闭==============");

    }
}
````

### 三、udp通信测试
````java
#### 1、模拟发送
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
````
#### 2、模拟接收
````java
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
        socket.close();
    }
````

### 四、udp模拟发送通知
#### 1、通知发送方
```java
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
```
#### 2、消息接收方
````java
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
````

