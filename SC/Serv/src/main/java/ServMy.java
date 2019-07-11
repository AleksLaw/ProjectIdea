import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class MyThread implements Runnable {

    @Override
    public void run() {
      int count=0;
        try {
            DataOutputStream dos = new DataOutputStream(ServMy.clientSocet.getOutputStream());
            for (int i = 0; i <50 ; i++) {
                dos.writeUTF(count++ + " ");
                Thread.sleep(1000);
            }




            ServMy.clientSocet.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ServMy {
   static Socket clientSocet;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);

        do {
            clientSocet = serverSocket.accept();
            System.out.println("sadad" );
            new Thread(new MyThread()).start();
        } while (true);


    }



}
