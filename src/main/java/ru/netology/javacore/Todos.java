package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    //...
    protected Set<String> tasks = new TreeSet<>();
    protected final int TASKS_MAX_SIZE = 7;

    public void addTask(String task) {
        //...
        if (tasks.size() < TASKS_MAX_SIZE) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        //...
        tasks.remove(task);
    }

    public String getAllTasks() {
        return String.join(" ", tasks);
        //return tasks.stream().sorted().collect(Collectors.joining(" ")); // если бы был HashSet
    }

}
