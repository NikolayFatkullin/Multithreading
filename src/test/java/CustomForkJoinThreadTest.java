import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustomForkJoinThreadTest {
    private static CustomForkJoinThread customForkJoinThread;
    private static CustomForkJoinThread customForkJoinThreadOneHundredThousand;
    private static ListSupplier listSupplier;
    private static List<Integer> listOneHundredThousand;

    @BeforeAll
    public static void setUp() {
        listSupplier = new ListSupplier();
        listOneHundredThousand = IntStream.range(0, 100000)
                .boxed()
                .collect(Collectors.toList());
        customForkJoinThread = new CustomForkJoinThread(listSupplier.getList());
        customForkJoinThreadOneHundredThousand = new CustomForkJoinThread(listOneHundredThousand);
    }

    @Test
    public void compute_OK() {
        int firstExpected = listSupplier.getList().stream().reduce(Integer::sum).get();
        int secondExpected = listOneHundredThousand.stream().reduce(Integer::sum).get();
        int firstActual = customForkJoinThread.compute();
        int secondActual = customForkJoinThreadOneHundredThousand.compute();
        assertEquals(firstExpected, firstActual, "Your method must return value: "
                + firstExpected + " But was: " + firstActual);
        assertEquals(secondExpected, secondActual, "Your method must return value: "
                + secondExpected + " But was: " + secondActual);
    }

    @Test
    public void compute_NotOk() {
        assertThrows(RuntimeException.class,() -> new CustomForkJoinThread(List.of()).compute());
    }
}
