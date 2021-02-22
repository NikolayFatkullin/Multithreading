import org.apache.log4j.Logger;

public class CustomThreadImplementingRunnable implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(CustomThreadImplementingRunnable.class);
    private int value;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            value++;
            System.out.println(Thread.currentThread().getName() + " value = " + value);
            LOGGER.info(Thread.currentThread().getName() + " value = " + value);
        }
    }
}
