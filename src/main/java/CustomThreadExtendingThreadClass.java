public class CustomThreadExtendingThreadClass extends Thread {
    private int value;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            value++;
            System.out.println(Thread.currentThread().getName() + " value = " + value);
        }
    }
}