package my.pvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyThread implements Runnable {


    @Override
    public void run() {


        try {
            DataOutputStream dos = new DataOutputStream(Server.clientSocet.getOutputStream());
            DataInputStream dis = new DataInputStream(Server.clientSocet.getInputStream());
            DateFormat df = new SimpleDateFormat("dd MM yyyy");

            dos.writeUTF("Connection successful " + Server.count++ + "-connection number" + "\n\r");
            dos.flush();
            String qw;

            do {
                dos.writeUTF("\r1 - Count up to 10 \n" +
                        "\r" + "2 - Get Date and Time\n\r" +
                        "\r" + "3 - Next time and how many are left\n\r" +
                        "\r" + "4 - Exit\n\r" +
                        "\n\r");

                dos.flush();

                if (!(qw = dis.readLine()).matches("^\\d")) {
                    continue;
                }

                int wer = Integer.parseInt(qw);

                switch (wer) {
                    case 1:
                        for (int i = 1; i <= 10; i++) {
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
                        SortedSet<GregorianCalendar> set = getGregorianCalendars();

                        set.removeIf(element -> element.getTimeInMillis() < System.currentTimeMillis() && !df.format(element.getTimeInMillis()).equals(df.format(System.currentTimeMillis())));

                        dos.writeUTF("\rNext lessons on-" + df.format(set.first().getTime()) + "\n");
                        dos.flush();
                        dos.writeUTF("\rLessons left-" + set.size() + "\n");
                        dos.flush();
                        dos.writeUTF("\n");
                        dos.flush();
                        break;

                    case 4:
                        dos.writeUTF("Thanks and have a nice day");
                        dos.flush();

                        Server.clientSocet.close();
                        break;
                }
            } while (true);

        } catch (IOException | InterruptedException e) {
            System.out.println("Соединение прервано: Поток");
        }
    }

    private SortedSet<GregorianCalendar> getGregorianCalendars() {
        SortedSet<GregorianCalendar> set = new TreeSet<>();

        set.add(new GregorianCalendar(2019, 6, 12));
        set.add(new GregorianCalendar(2019, 6, 16));
        set.add(new GregorianCalendar(2019, 6, 19));
        set.add(new GregorianCalendar(2019, 6, 23));
        set.add(new GregorianCalendar(2019, 6, 26));
        set.add(new GregorianCalendar(2019, 6, 30));
        set.add(new GregorianCalendar(2019, 7, 13));
        set.add(new GregorianCalendar(2019, 7, 16));
        set.add(new GregorianCalendar(2019, 7, 20));
        set.add(new GregorianCalendar(2019, 7, 23));
        set.add(new GregorianCalendar(2019, 7, 27));
        set.add(new GregorianCalendar(2019, 7, 30));
        set.add(new GregorianCalendar(2019, 8, 3));
        set.add(new GregorianCalendar(2019, 8, 6));
        set.add(new GregorianCalendar(2019, 8, 10));
        set.add(new GregorianCalendar(2019, 8, 13));
        set.add(new GregorianCalendar(2019, 8, 17));
        set.add(new GregorianCalendar(2019, 8, 20));
        set.add(new GregorianCalendar(2019, 8, 24));
        set.add(new GregorianCalendar(2019, 8, 27));
        set.add(new GregorianCalendar(2019, 9, 1));
        set.add(new GregorianCalendar(2019, 9, 4));
        set.add(new GregorianCalendar(2019, 9, 8));
        set.add(new GregorianCalendar(2019, 9, 11));
        set.add(new GregorianCalendar(2019, 9, 15));
        set.add(new GregorianCalendar(2019, 9, 18));
        set.add(new GregorianCalendar(2019, 9, 22));
        set.add(new GregorianCalendar(2019, 9, 25));
        set.add(new GregorianCalendar(2019, 9, 29));
        set.add(new GregorianCalendar(2019, 10, 1));
        return set;
    }

}
