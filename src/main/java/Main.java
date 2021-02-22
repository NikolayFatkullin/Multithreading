public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            CustomThreadImplementingRunnable customThreadImplementingRunnable =
                    new CustomThreadImplementingRunnable();
            Thread threadRunnable = new Thread(customThreadImplementingRunnable);
            threadRunnable.start();
        }
        for (int i = 0; i < 3; i++) {
            CustomThreadExtendingThreadClass customThreadExtendingThreadClass =
                    new CustomThreadExtendingThreadClass();
            customThreadExtendingThreadClass.start();
        }
    }
}
