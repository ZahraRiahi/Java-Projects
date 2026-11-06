package chat;


import chat.model.Client;
import chat.server.Server;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            clients.add(new Client("Client" + i));
        }

//        Server server = new Server(clients);
//        server.start();
//
//        System.out.println("سرور راه افتاد. برای خروج Enter بزنید.");
//        System.in.read();   // ⬅️ منتظر Enter می‌مونه
//        System.out.println("خروج...");
//        System.exit(0);
        Server server = new Server(clients);
        server.start();

        System.out.println("=== تست ۳ شروع شد ===");
        System.out.println("برای پایان، Enter بزن.\n");

        System.in.read();          // با Enter ببند
        System.out.println("=== پایان تست ===");
    }
}