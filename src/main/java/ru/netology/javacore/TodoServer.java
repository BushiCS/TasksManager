package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class TodoServer {

    private final int port;
    private final Todos todos;
    private final Gson gson;
    private final Deque<Task> logList;


    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
        gson = new Gson();
        logList = new ArrayDeque<>();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started.");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    Task task = gson.fromJson(in.readLine(), Task.class);
                    System.out.println(gson.toJson(task));
                    switch (task.getType()) {
                        case "ADD":
                            if (todos.addTask(task.getTask())) {
                                logList.offer(new Task(task.getType(), task.getTask()));
                            }
                            break;
                        case "REMOVE":
                            if (todos.removeTask(task.getTask())) {
                                logList.offer(new Task(task.getType(), task.getTask()));
                            }
                            break;
                        case "RESTORE":
                            if (!logList.isEmpty()) {
                                var logType = logList.getLast().getType();
                                var logTask = logList.getLast().getTask();
                                if (logType.equals("ADD")) {
                                    todos.removeTask(logTask);
                                    logList.removeLast();
                                    break;
                                }
                                if (logType.equals("REMOVE")) {
                                    todos.addTask(logTask);
                                    logList.removeLast();
                                    break;
                                }
                            }
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
