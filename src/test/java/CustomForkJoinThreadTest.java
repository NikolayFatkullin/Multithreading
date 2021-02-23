import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustomForkJoinThreadTest {
    private static CustomForkJoinThread customForkJoinThread;
    private static CounterArrayList counterArrayList;

    @BeforeAll
    public static void setUp() {
        counterArrayList = new CounterArrayList();
        customForkJoinThread = new CustomForkJoinThread(counterArrayList.getList());
    }

    @Test
    void compute() {
        int expected = counterArrayList.getList().stream().reduce(Integer::sum).get();
        int actual = customForkJoinThread.compute();
        assertEquals(expected, actual, "Your method must return value: " + expected
                + " But was: " + actual);
    }
}
