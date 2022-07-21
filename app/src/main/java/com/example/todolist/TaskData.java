package com.example.todolist;

public class TaskData {
    String Heading, Description;
    public TaskData(String heading, String description) {
        Heading = heading;
        Description = description;
    }

    public String getHeading() {
        return Heading;
    }

    public String getDescription() {
        return Description;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
