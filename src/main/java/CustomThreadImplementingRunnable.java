import org.apache.log4j.Logger;

public class CustomThreadImplementingRunnable implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(CustomThreadImplementingRunnable.class);
    private Counter counter;

    public CustomThreadImplementingRunnable(Counter counter) {
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
