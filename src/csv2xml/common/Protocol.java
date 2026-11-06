package csv2xml.common;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Protocol {

    public static void sendMessage(DataOutputStream out, String msg) throws IOException {
        byte[] data = msg.getBytes(StandardCharsets.UTF_8);
        out.writeInt(data.length);
        out.write(data);
        out.flush();
    }

    public static String receiveMessage(DataInputStream in) throws IOException {
        int len = in.readInt();
        byte[] data = new byte[len];
        in.readFully(data);
        return new String(data, StandardCharsets.UTF_8);
    }
}