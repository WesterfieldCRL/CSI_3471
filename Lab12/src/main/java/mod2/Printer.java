package mod2;

public class Printer {
    private int nextThread = 1;

    public synchronized void print(Thread thread) {
        int threadId = Integer.parseInt(thread.getName());
        while (threadId != nextThread) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println("Thread " + threadId);
        nextThread++;
        notifyAll();
    }

    public static void main(String[] args) {
        Printer printer = new Printer();

        for (int i = 1; i <= 20; i++) {
            Thread thread = new Thread(() -> {
                printer.print(Thread.currentThread());
            });
            thread.setName(Integer.toString(i));
            thread.start();
        }
    }

}
