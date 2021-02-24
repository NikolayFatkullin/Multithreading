import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustomExecutorServiceTest {
    private static CustomExecutorService customExecutorService;
    private static CustomExecutorService customExecutorServiceOneHundredThousand;
    private static ListSupplier listSupplier;
    private static List<Integer> listOneHundredThousand;

    @BeforeAll
    public static void setUp() {
        listSupplier = new ListSupplier();
        customExecutorService = new CustomExecutorService(listSupplier.getList());
        listOneHundredThousand = IntStream.range(0, 100000)
                .boxed()
                .collect(Collectors.toList());
        customExecutorServiceOneHundredThousand = new CustomExecutorService(listOneHundredThousand);
    }

    @Test
    public void executeOk() {
        int firstExpected = listSupplier.getList().stream().reduce(Integer::sum).get();
        int secondExpected = listOneHundredThousand.stream().reduce(Integer::sum).get();
        int firstActual = customExecutorService.execute();
        int secondActual = customExecutorServiceOneHundredThousand.execute();
        assertEquals(firstExpected, firstActual, "Your method must return value: "
                + firstExpected + " But was: " + firstActual);
        assertEquals(secondExpected, secondActual, "Your method must return value: "
                + secondExpected + " But was: " + secondActual);
    }

    @Test
    public void executeNotOk() {
        assertThrows(RuntimeException.class,() -> new CustomExecutorService(List.of()).execute());
    }
}
