import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustomExecutorServiceTest {
    private static CustomExecutorService customExecutorService;
    private static CounterArrayList counterArrayList;

    @BeforeAll
    public static void setUp() {
        counterArrayList = new CounterArrayList();
        customExecutorService = new CustomExecutorService(counterArrayList.getList());
    }

    @Test
    void execute() {
        int expected = counterArrayList.getList().stream().reduce(Integer::sum).get();
        int actual = customExecutorService.execute();
        assertEquals(expected, actual, "Your method must return value: " + expected
                + " But was: " + actual);
    }
}
