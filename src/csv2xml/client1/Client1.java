package csv2xml.client1;

import csv2xml.common.Protocol;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client1 {
    public static void main(String[] args) throws Exception {

        // --- بخش جدید: خواندن از فایل ---
        String fileName = "sample.csv";
        String csv = "";
        try {
            // فایل رو به صورت رشته می‌خونیم
            csv = Files.readString(Paths.get(fileName), StandardCharsets.UTF_8);
            System.out.println("[Client1] فایل " + fileName + " با موفقیت خوانده شد.");
        } catch (IOException e) {
            System.out.println("[Client1] خطا در خواندن فایل! مطمئن شوید فایل sample.csv در پوشه پروژه وجود دارد.");
            e.printStackTrace();
            return; // اگر فایل نبود، برنامه بسته میشه
        }
        // --------------------------------

        Socket s = new Socket("localhost", 5001);
        System.out.println("[Client1] به سرور وصل شد.");
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());

        // ارسال محتوای فایل به سرور
        Protocol.sendMessage(out, csv);
        System.out.println("[Client1] محتوای CSV ارسال شد.");

        // دریافت پاسخ
        String reply = Protocol.receiveMessage(in);
        System.out.println("[Client1] پاسخ: " + reply);

        s.close();
    }
}

//public class Client1 {
//    public static void main(String[] args) throws Exception {
//        String csv =
//                "id, name, age, city\n" +
//                        "3, Ali, 25, Tehran\n" +
//                        "\n" +
//                        "1, Sara, 30, Shiraz\n" +
//                        "bad,row\n" +
//                        "2, Reza, 22, Mashhad\n" +
//                        "4, Mina, 28, Isfahan\n";
//
//        Socket s = new Socket("localhost", 5001);
//        System.out.println("[Client1] به سرور وصل شد.");
//        DataOutputStream out = new DataOutputStream(s.getOutputStream());
//        DataInputStream in = new DataInputStream(s.getInputStream());
//
//        Protocol.sendMessage(out, csv);
//        System.out.println("[Client1] CSV ارسال شد.");
//
//        String reply = Protocol.receiveMessage(in);
//        System.out.println("[Client1] پاسخ: " + reply);
//
//        s.close();
//    }
//}