package chat.server;


import chat.model.Client;
import chat.model.Message;

import java.util.List;

public class Server {
    private final List<Client> clients;
    private int messageCounter = 0;

    // برای تست سریع‌تر، ۵ ثانیه؛ در واقعیت ۶۰_۰۰۰
    private static final long INTERVAL_MS = 5_000;
    // چقدر شبیه‌سازی کنیم که کلاینت "مشغوله" (برای تست)
    private static final long WORK_MS = 25_000;

    public Server(List<Client> clients) {
        this.clients = clients;
    }

    public void start() {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(INTERVAL_MS);
                    Message msg = new Message(++messageCounter, "Task-" + messageCounter);
                    System.out.println("\n>>> سرور پیام جدید ساخت: " + msg);
                    dispatch(msg);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ServerThread");
        t.setDaemon(true);
        t.start();
    }

    /**
     * ارسال پیام به اولین کلاینت بیکار.
     * اگر همه مشغول بودن → منتظر می‌مونه تا یکی بیکار بشه.
     */
    private void dispatch(Message msg) {
        while (true) {
            Client target = findIdleClient();
            if (target != null) {
                if (target.tryAccept(msg)) {
                    // شبیه‌سازی کار کلاینت در ترد جدا
                    new Thread(() -> simulateClientWork(target, msg)).start();
                    return; // پیام تحویل داده شد
                }
            } else {
                System.out.println("  [سرور] همه کلاینت‌ها مشغولن، منتظر می‌مونم...");
                sleepQuietly(1000);
            }
        }
    }

    private Client findIdleClient() {
        for (Client c : clients) {
            if (c.getState() == Client.State.IDLE) return c;
        }
        return null;
    }

    private void simulateClientWork(Client client, Message msg) {
        sleepQuietly(WORK_MS);
        client.finishWork();
        System.out.println("  [" + client.getName() + "] ack برای پیام " + msg.getId() + " فرستاد.");
    }

    private void sleepQuietly(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
