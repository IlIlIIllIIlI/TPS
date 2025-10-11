public class Task {
    String title;
    String description;
    Priority priority;

    Task(String title, String description, Priority priority) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be null or empty");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description can't be null or empty");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority can't be null or empty");
        }
        this.title=title;
        this.description=description;
        this.priority=priority;
    }

    public void display(){
        System.out.println("Title : "+this.title);
        System.out.println("Description : "+this.description);
        System.out.println("Priority : "+this.priority.toString());
    }


    public String getTitle() {
        return this.title;
    }

    public Priority getPriority() {
        return this.priority;
    }
}
