package csv2xml.server;

import csv2xml.common.Person;
import csv2xml.common.Protocol;
import java.io.*;
import java.net.*;
import java.util.List;

public class Server {
    static final int PORT1 = 5001;
    static final int PORT2 = 5002;

    public static void main(String[] args) throws Exception {
        ServerSocket ss1 = new ServerSocket(PORT1);
        ServerSocket ss2 = new ServerSocket(PORT2);
        System.out.println("[Server] منتظر Client1 و Client2...");

        // مرحله 1: CSV از Client1
        Socket c1 = ss1.accept();
        System.out.println("[Server] Client1 متصل شد.");
        DataInputStream in1 = new DataInputStream(c1.getInputStream());
        DataOutputStream out1 = new DataOutputStream(c1.getOutputStream());

        String csv = Protocol.receiveMessage(in1);
        System.out.println("[Server] CSV دریافت شد.");

        // پردازش
        List<Person> people = CsvProcessor.readCsv(csv);
        CsvProcessor.sort(people);
        String xml = CsvProcessor.toXml(people);
        System.out.println("[Server] XML ساخته شد (" + people.size() + " رکورد).");

        // مرحله 2: XML به Client2
        Socket c2 = ss2.accept();
        System.out.println("[Server] Client2 متصل شد.");
        DataInputStream in2 = new DataInputStream(c2.getInputStream());
        DataOutputStream out2 = new DataOutputStream(c2.getOutputStream());

        Protocol.sendMessage(out2, xml);

        // مرحله 3: پیام تایید از Client2
        String ack = Protocol.receiveMessage(in2);
        System.out.println("[Server] پیام از Client2: " + ack);

        // مرحله 4: پیام تایید به Client1
        Protocol.sendMessage(out1, ack);

        c1.close(); c2.close(); ss1.close(); ss2.close();
        System.out.println("[Server] پایان.");
    }
}