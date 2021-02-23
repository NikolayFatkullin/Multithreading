import org.apache.log4j.Logger;

public class CustomThreadExtendingThreadClass extends Thread {
    private static final Logger LOGGER = Logger.getLogger(CustomThreadExtendingThreadClass.class);
    private Counter counter;

    public CustomThreadExtendingThreadClass(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.getCount() <= 100) {
            int value = counter.getCount() + 1;
            counter.setCount(value);
            LOGGER.info(Thread.currentThread().getName() + " value = " + value);
        }
    }
}
