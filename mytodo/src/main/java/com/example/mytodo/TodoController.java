package com.example.mytodo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // ทำให้ class นี้ return เป็น json ได้เอง
public class TodoController {

    private List<Todo> todos = new ArrayList<>();



    public TodoController(){
        todos.add(new Todo(1,"iBlur"));
        todos.add(new Todo(2,"Cat"));
        todos.add(new Todo(3,"Dog"));

    }
    //   .../todo
    // GET
    @GetMapping("/todo")
    public List<Todo> getTodos(){
        return  todos;
    }


    //   .../todo/id
    // GET
    @GetMapping("/todo/id")
    public Todo getTodos2(){
        final long id  = 1;
        return  todos.stream().filter(result -> result.getId() == id)
                .findFirst().orElseGet( ()-> null );
    }


}
