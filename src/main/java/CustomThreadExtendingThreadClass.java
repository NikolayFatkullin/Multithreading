import org.apache.log4j.Logger;

public class CustomThreadExtendingThreadClass extends Thread {
    private static final Logger LOGGER = Logger.getLogger(CustomThreadExtendingThreadClass.class);

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            int value = Counter.getCount() + 1;
            Counter.setCount(value);
            LOGGER.info(Thread.currentThread().getName() + " value = " + value);
        }
    }
}
