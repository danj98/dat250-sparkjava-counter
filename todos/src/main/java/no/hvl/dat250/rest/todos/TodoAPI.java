package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;
import spark.Spark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.*;
/**
 * Rest-Endpoint.
 */
public class TodoAPI {

    private static final HashMap<Long, Todo> todos = new HashMap<>();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        after((req, res) -> res.type("application/json"));
        // TODO: Implement API, such that the testcases succeed.
        // Get all
        get("/todos", (req, res) -> gson.toJson(todos.values()));
        // Get one
        get("todos/:id", (req, res) -> {
            try {
                Long id = Long.valueOf(req.params(":id"));
            } catch ()
            }
            return gson.toJson(todos.get(id));
        });

        post("/todos", (req, res) -> {
            Todo todo = gson.fromJson(req.body(), Todo.class);
            if (todo.getId() == null) {
                todo = new Todo((long) todos.size()+1, todo.getSummary(), todo.getDescription());
            }
            todos.put(todo.getId(), todo);
            return gson.toJson(todo);
        });

        put("/todos/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            todos.put(id, gson.fromJson(req.body(), Todo.class));
            return gson.toJson(todos.get(id));
        });

        delete("/todos/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            todos.remove(id);
            return gson.toJson(todos.get(id));
        });
    }
}
