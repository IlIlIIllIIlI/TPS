import java.util.ArrayList;
import java.util.Objects;

public class TaskManager {
    ArrayList<Task> tasks;

    TaskManager(){
        this.tasks = new ArrayList<>();
    }

    TaskManager(ArrayList<Task> tasks){
        this.tasks=tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void removeTask(Task task){
        this.tasks.remove(task);
    }

    public void displayAll() {
        if (tasks.isEmpty()){
            throw new ArrayStoreException("Manager is empty");
        }
        for (Task task : this.tasks) {
            task.display();
        }
    }

    public void filterPriority(Priority priority){
        for (Task task : this.tasks){
            if (Objects.equals(task.getPriority(), priority)){
                task.display();
            }
        }
    }

    public void filterTitle(String title){
        for (Task task : this.tasks){
            if (Objects.equals(task.getTitle
(), title)){
                task.display();
            }
        }
    }


    public int countPriority(Priority priority){
        int count=0;
        for (Task task : this.tasks){
            if (Objects.equals(task.getPriority(), priority)){
                count++;
            }
        }
        return count;
    }

    public void statDump(){
        System.out.printf("There are %s tasks :%n",this.tasks.size());
        this.displayAll();
        System.out.printf("There are %s High priority tasks :%n",this.countPriority(Priority.HIGH));
        this.filterPriority(Priority.HIGH);
        System.out.printf("There are %s Medium priority tasks%n",this.countPriority(Priority.MEDIUM));
        this.filterPriority(Priority.MEDIUM);
        System.out.printf("There are %s Low priority tasks%n",this.countPriority(Priority.LOW));
        this.filterPriority(Priority.LOW);


    }

    private boolean importTask(String[] taskData){
        try {
            if (taskData.length !=3){
                throw new IndexOutOfBoundsException("Array must have exactly 3 values");
            }
            String title = taskData[0];
            String description = taskData[1];
            Priority priority = Priority.fromString(taskData[2]);

            Task task = new Task(title,description,priority);

            this.addTask(task);
        } catch (IllegalArgumentException e) {
            System.out.println("Couldn't import task : "+e.getMessage());
            return false;
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Couldn't import task : Wrong Format");
            return false;
        }
        return true;
    }

    public void importTasks(String[][] tasks){
        int counter=0;
        for (String[] task : tasks){
            if (importTask(task)){
                counter++;
            }
        }

        System.out.printf("Imported %s tasks out of %s",counter,tasks.length);
    }
}
