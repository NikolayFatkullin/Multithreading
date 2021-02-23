import java.util.List;
import java.util.concurrent.Callable;

public class CustomThreadImplementingCallable implements Callable<Integer> {
    private final List<Integer> list;

    public CustomThreadImplementingCallable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() {
        return list.stream()
                .reduce(Integer::sum)
                .orElseThrow(RuntimeException::new);
    }
}
