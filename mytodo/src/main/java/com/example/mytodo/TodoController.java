package com.example.mytodo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // ทำให้ class นี้ return เป็น json ได้เอง
public class TodoController {
    public TodoController(){

    }
    // GET
    @GetMapping("/say")
    public String say(){
        return  "iBlurBlur";
    }

}
