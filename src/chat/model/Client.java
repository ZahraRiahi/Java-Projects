package chat.model;
import chat.model.Message;

public class Client {
    public enum State { IDLE, BUSY }

    private final String name;
    private volatile State state = State.IDLE;

    public Client(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public State getState() { return state; }
    public void setState(State s) { this.state = s; }

    // تلاش برای گرفتن پیام
    public synchronized boolean tryAccept(Message msg) {
        if (state == State.BUSY) {
            System.out.println("  [" + name + "] مشغولم، پیام " + msg.getId() + " رو نمی‌گیرم.");
            return false;
        }
        state = State.BUSY;
        System.out.println("  [" + name + "] پیام " + msg.getId() + " رو گرفتم.");
        return true;
    }

    // بعد از انجام کار
    public synchronized void finishWork() {
        state = State.IDLE;
        System.out.println("  [" + name + "] کارم تموم شد، الان بیکارم.");
    }

    @Override
    public String toString() { return name; }
}
