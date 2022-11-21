package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTests {

    Todos todos = new Todos();

    // ваши тесты для класса Todos
    @Test
    void testSortedAndUniqueTasks() {
        todos.addTask("Работа");
        todos.addTask("Отдых");
        todos.addTask("Сон");
        todos.addTask("Работа");
        Assertions.assertEquals("Отдых, Работа, Сон", todos.getAllTasks());
    }

    @Test
    void testMaxTasks() {
        todos.addTask("Работа");
        todos.addTask("Отдых");
        todos.addTask("Сон");
        todos.addTask("Пища");
        todos.addTask("Рисование");
        todos.addTask("Футбол");
        todos.addTask("Компьютерные игры");
        todos.addTask("Тренажерный зал");
        todos.addTask("Поход в музей");
        Assertions.assertEquals("Компьютерные игры, Отдых, Пища, Работа, Рисование, Сон, Футбол", todos.getAllTasks());
    }

    @Test
    void removeTask() {
        todos.addTask("Рисование");
        todos.addTask("Футбол");
        todos.removeTask("Рисование");
        Assertions.assertEquals("Футбол", todos.getAllTasks());
    }

    @Test
    void maxTasksWithBoolean(){
        todos.addTask("1");
        todos.addTask("2");
        todos.addTask("3");
        todos.addTask("4");
        todos.addTask("5");
        todos.addTask("6");
        todos.addTask("7");
        Assertions.assertFalse(todos.addTask("1 more task"));
    }

    @Test
    void removeEmptyTasks(){
        Assertions.assertFalse(todos.removeTask("nothingToRemove"));
    }
}
