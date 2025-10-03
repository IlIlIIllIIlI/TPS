public class Task {
    String title;
    String description;
    Priority priority;

    Task(String title, String description, Priority priority) {
        if (title == null || title.equals(" ") ||description==null||priority==null) {
            throw new IllegalArgumentException("Title, Description and Priority can't be null or empty");
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




}
