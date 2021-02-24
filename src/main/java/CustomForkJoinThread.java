import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomForkJoinThread extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 100000;
    private final List<Integer> counter;

    public CustomForkJoinThread(List<Integer> counter) {
        this.counter = counter;
    }

    @Override
    protected Integer compute() {
        if (counter.size() > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }
        return processing(counter);
    }

    private Collection<CustomForkJoinThread> createSubtasks() {
        List<CustomForkJoinThread> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomForkJoinThread(counter.subList(0, counter.size() / 2)));
        dividedTasks.add(new CustomForkJoinThread(counter.subList(counter.size() / 2,
                counter.size())));
        return dividedTasks;
    }

    private Integer processing(List<Integer> list) {
        return list.stream()
                .reduce(Integer::sum)
                .orElseThrow(RuntimeException::new);
    }
}
