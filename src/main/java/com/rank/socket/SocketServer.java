package com.rank.socket;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Caolp
 */
public class SocketServer {

    /**
     * 指定监听的端口
     */
    private static final int PORT = 50000;

    public static void main(String[] args) {
        optimizeServerSocketDemo();

    }

    private static void basicServerSocketDemo() {


        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("server 将一直等待连接到来");
            Socket socket = serverSocket.accept();
            // 建立好连接之后，从 socket 中获取输入流，并建立缓冲区进行读取
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder stringBuilder = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1){
                stringBuilder.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
            }
            System.out.println("get message from client:" + stringBuilder);
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void optimizeServerSocketDemo(){

        try {
            ServerSocket server = new ServerSocket(PORT);
            // server 将一直等待连接到来
            System.out.println("server 将一直等待连接到来");
            Socket socket = server.accept();
            // 建立好连接后，从 socket 中获取输入流，并建立缓冲区进行读取
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1){
                sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
            }
            System.out.println("get message from client: "+ sb);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Hello Client, i get the message.".getBytes(StandardCharsets.UTF_8));
            inputStream.close();
            outputStream.close();
            socket.close();
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
