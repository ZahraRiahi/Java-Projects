package chat.test;

import chat.model.Client;
import chat.model.Message;

public class Test1 {
    public static void main(String[] args) throws Exception {
        Client c1 = new Client("C1");
        Client c2 = new Client("C2");

        c2.tryAccept(new Message(0, "warmup")); // c2 رو مشغول می‌کنیم

        Message m = new Message(1, "T1");
        System.out.println("C1 می‌گیرد؟ " + c1.tryAccept(m));   // true
        System.out.println("C2 می‌گیرد؟ " + c2.tryAccept(m));   // false

    }
}
