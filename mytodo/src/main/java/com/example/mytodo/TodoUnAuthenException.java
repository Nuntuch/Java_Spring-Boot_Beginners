package com.example.mytodo;

public class TodoUnAuthenException extends RuntimeException{

    public TodoUnAuthenException(){
        super("Token Invalid");
    }

}
