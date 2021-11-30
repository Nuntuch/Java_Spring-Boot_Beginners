package com.example.mytodo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@RestController // ทำให้ class นี้ return เป็น json ได้เอง
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();


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
    @GetMapping("/todo/{id}")
    public Todo getTodosById(@PathVariable long id){
        return  todos.stream().filter(result -> result.getId() == id)
//                .findFirst().orElseGet( ()-> null );
                .findFirst().orElseThrow(()-> new TodoNotFoundException(id));

    }



    //   .../todo/search?name=iblurblur
    // GET
    @GetMapping("/todo/search")
    public String getTodosByName(@RequestParam(defaultValue = "cat") String name){
        return  "search: " + name;
    }


    // ..../todo
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo){
        todos.add(new Todo(counter.getAndIncrement(), todo.getName()));
    }


    //   .../todo/1234
    // GET
    @PutMapping("/todo/{id}")
    public void editTodo(@RequestBody Todo todo, @PathVariable long id){
        AtomicBoolean cannot_remove = new AtomicBoolean(true);
        todos.stream().filter(result -> result.getId() == id)
                .findFirst()
//                .ifPresentOrElse()
                .ifPresent(result -> {
                    result.setName(todo.getName());
                    todos.remove(result);
                    cannot_remove.set(false);
//                },() -> {

                });    ;

        if(cannot_remove.get()){
            throw new TodoNotFoundException(id);

        }

    }



    //   .../todo/1234
    // GET
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping ("/todo/{id}")
    public void deleteTodo(@PathVariable long id){
        AtomicBoolean cannot_remove = new AtomicBoolean(true);
        todos.stream().filter(result -> result.getId() == id)
                .findFirst()
//                .ifPresentOrElse()
                .ifPresent(result -> {
                    todos.remove(result);
                    cannot_remove.set(false);
//                },() -> {

                });    ;

                if(cannot_remove.get()){
                    System.out.println("55555555555555555555555555555555555555555555555555555555555555555");
                    throw new TodoNotFoundException(id);

                }
    }



}
