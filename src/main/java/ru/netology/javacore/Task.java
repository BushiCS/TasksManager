package ru.netology.javacore;

public class Task {

    private final String type;
    private String task;

    public Task(String type, String task) {
        this.type = type;
        this.task = task;
    }

    @SuppressWarnings("unused")
    public Task(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

}
