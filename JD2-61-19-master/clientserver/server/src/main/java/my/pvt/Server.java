package my.pvt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static Socket clientSocet;
    static int count = 1;

    public static void main(String[] args)  {
        ServerSocket serverSocket = null;
        try {
        do {
                serverSocket = new ServerSocket(9999);
                clientSocet = serverSocket.accept();
            new Thread(new MyThread()).start();
        } while (true);
        } catch (IOException e) {
            System.out.println("Соединение прервано: Сервер");
    }

}
}
