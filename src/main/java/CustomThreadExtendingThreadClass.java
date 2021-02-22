import org.apache.log4j.Logger;

public class CustomThreadExtendingThreadClass extends Thread {
    private int value;
    private static final Logger LOGGER = Logger.getLogger(CustomThreadExtendingThreadClass.class);

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            value++;
            System.out.println(Thread.currentThread().getName() + " value = " + value);
            LOGGER.info(Thread.currentThread().getName() + " value = " + value);
        }
    }
}
