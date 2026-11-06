package csv2xml.client2;

import csv2xml.common.Protocol;
import java.io.*;
import java.net.*;

public class Client2 {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5002);
        System.out.println("[Client2] به سرور وصل شد.");
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        String xml = Protocol.receiveMessage(in);
        System.out.println("[Client2] XML دریافت شد:\n" + xml);

        String ack;
        if (xml.startsWith("<?xml") && xml.contains("<people>") && xml.contains("</people>")) {
            ack = "XML با موفقیت دریافت شد، هیچ مغایرتی پیدا نشد.";
        } else {
            ack = "خطا: XML نامعتبر است.";
        }

        Protocol.sendMessage(out, ack);
        System.out.println("[Client2] پاسخ: " + ack);

        s.close();
    }
}