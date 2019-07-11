import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyThread implements Runnable {

    @Override
    public void run() {

        try {
            DataOutputStream dos = new DataOutputStream(ServMy.clientSocet.getOutputStream());
            DataInputStream dis = new DataInputStream(ServMy.clientSocet.getInputStream());

            dos.writeUTF("Connect sucsfull-"+ ServMy.count++ +"-number connections"+ "\n\r");
            dos.flush();
            String qw ;

            do {
                dos.writeUTF("\r1-Count to 25 \n" +
                        "\r" + "2 - Get Date and Time\n\r" +
                        "\r" + "3 - Exit\n\r");
                dos.flush();

                if (!(qw = dis.readLine()).matches("^\\d")) {
                    dos.writeUTF("inside");
                    dos.flush();
                    continue;
                }

                Integer wer = Integer.parseInt(qw);

                switch (wer) {
                    case 1:
                        for (int i = 1; i <= 25; i++) {
                            dos.writeUTF(i + "\r\n");
                            Thread.sleep(200);
                        }
                        dos.writeUTF("\r\n");
                        dos.flush();
                        break;
                    case 2:
                        dos.writeUTF("\r" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "\n");
                        dos.writeUTF("\r" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + "\n");
                        dos.writeUTF("\r\n");
                        dos.flush();
                        break;
                    case 3:
                        ServMy.clientSocet.close();
                        break;
                }
            } while (true);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ServMy {
    static Socket clientSocet;
    static int count=1;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        do {
            clientSocet = serverSocket.accept();
            new Thread(new MyThread()).start();
        } while (true);
    }


}
