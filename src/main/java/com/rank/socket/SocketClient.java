package com.rank.socket;

import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Caolp
 */
public class SocketClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 50000;

    public void basicClientSocketDemo(){
        try {
            Socket socket = new Socket(HOST, PORT);
            OutputStream outputStream = socket.getOutputStream();
            String message = "hello, i am client";
            socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void optimizeClientSocketDemo(){

        try {
            // 与服务端建立连接
            Socket socket = new Socket(HOST, PORT);
            // 建立连接后获取输出流
            OutputStream outputStream = socket.getOutputStream();
            String message = "hello, server, i am client";
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            // 通过 shutdownOutput 告诉服务器已经发送完数据，后续只能接收数据
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1){
                sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
            }
            System.out.println("get message from server \n" + sb);
            inputStream.close();
            outputStream.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        optimizeClientSocketDemo();

    }
}
