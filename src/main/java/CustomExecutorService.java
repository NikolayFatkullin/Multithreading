import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;

public class CustomExecutorService {
    private static final int THREADS_COUNT = 10;
    private final List<Integer> list;

    public CustomExecutorService(List<Integer> list) {
        this.list = list;
    }

    public int execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        List<List<Integer>> listList = ListUtils.partition(list, list.size() / THREADS_COUNT);
        List<Callable<Integer>> callables = listList.stream()
                .map(CustomThreadImplementingCallable::new)
                .collect(Collectors.toList());
        try {
            List<Future<Integer>> futureList = executorService.invokeAll(callables);
            executorService.shutdown();
            int sum = 0;
            for (Future<Integer> future : futureList) {
                sum += future.get();
            }
            return sum;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Can't invoke all threads");
        }
    }
}
