package chat.test;

import chat.model.Client;
import chat.model.Message;

public class Test2 {
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) new Client("C"+i).tryAccept(new Message(0,"w"));
        // بدون بیکار، dispatch باید گیر کنه → از سرور واقعی استفاده کن
        // فقط رفتار tryAccept تست می‌شه:
        Client idleCheck = new Client("X");
        System.out.println("X می‌گیرد؟ " + idleCheck.tryAccept(new Message(9,"w"))); // true
    }
}