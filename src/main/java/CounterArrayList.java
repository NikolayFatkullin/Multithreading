import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CounterArrayList {
    private final List<Integer> list;

    public CounterArrayList() {
        list = IntStream.range(1, 1000000)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> getList() {
        return list;
    }
}
