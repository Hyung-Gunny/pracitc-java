public class Task implements Comparable<Task> {
    private int _priority;
    private String _title;

    public Task(int priority, String title) {
        _priority = priority;
        _title = title;
    }

    public int getPriority() {
        return _priority;
    }

    @Override
    public String toString() {
        return "Task(" + _priority + ", " + _title + ")";
    }

    @Override
    public int compareTo(Task other) {
        // Compare tasks by priority
        return Integer.compare(this._priority, other._priority);
    }
}
