package ru.netology.javacore;

import java.util.*;

public class Todos {
    protected Set<String> tasks = new TreeSet<>();

    protected final int TASKS_MAX_SIZE = 7;

    public boolean addTask(String task) {
        if (tasks.size() < TASKS_MAX_SIZE) {
            tasks.add(task);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeTask(String task) {
        if (tasks.size() != 0) {
            tasks.remove(task);
            return true;
        } else {
            return false;
        }
    }

    public String getAllTasks() {
        return String.join(", ", tasks);
        //return tasks.stream().sorted().collect(Collectors.joining(", ")); // если бы был HashSet
    }

}
