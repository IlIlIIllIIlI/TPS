//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MovieScheduler schedule = new MovieScheduler();
        TaskManager taskManager = new TaskManager();

        System.out.println("=== Test données valides ===");
        taskManager.importTasks(TaskTestData.getValidTasks());
        System.out.println("=== Test données problématiques ===");
        taskManager.importTasks(TaskTestData.getProblematicTasks());
        System.out.println("=== Test grande liste ===");
        taskManager.importTasks(TaskTestData.getLargeTaskSet());

        schedule.importMovieSchedule(MovieSlotTestData.getLargeMovieSchedule());
        schedule.importMovieSchedule(MovieSlotTestData.getInvalidMovieSchedule());
        schedule.importMovieSchedule(MovieSlotTestData.getDuplicateMovieSchedule());
        schedule.importMovieSchedule(MovieSlotTestData.getProblematicMovieSchedule());
    }
}