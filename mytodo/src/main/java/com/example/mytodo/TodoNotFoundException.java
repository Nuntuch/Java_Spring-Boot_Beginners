package com.example.mytodo;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(){

    }

    public TodoNotFoundException(long id){
        super("Could not find todo " + id);
    }
}
