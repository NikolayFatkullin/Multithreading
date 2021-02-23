public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 3; i++) {
            CustomThreadImplementingRunnable customThreadImplementingRunnable =
                    new CustomThreadImplementingRunnable(counter);
            Thread threadRunnable = new Thread(customThreadImplementingRunnable);
            threadRunnable.start();
        }
        for (int i = 0; i < 3; i++) {
            CustomThreadExtendingThreadClass customThreadExtendingThreadClass =
                    new CustomThreadExtendingThreadClass(counter);
            customThreadExtendingThreadClass.start();
        }
    }
}
