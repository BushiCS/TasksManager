package ru.netology.javacore;

import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {

    private final int port;
    private final Todos todos;
    private final Gson gson;

    public TodoServer(int port, Todos todos) {
        //...
        this.port = port;
        this.todos = todos;
        gson = new Gson();
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    Task task = gson.fromJson(in.readLine(), Task.class);
                    switch (task.getType()) {
                        case "ADD":
                            todos.addTask(task.getTask());
                            break;
                        case "REMOVE":
                            todos.removeTask(task.getTask());
                            break;
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    public Todos getTodos() {
        return todos;
    }
}
