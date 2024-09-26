package com.sparta.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/api/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/api/get")
    @ResponseBody
    public String get() {
        return "GET Method 요청";
    }

    @PostMapping("/api/post")
    @ResponseBody
    public String post() {
        return "POST Method 요청";
    }

    @PutMapping("/api/put")
    @ResponseBody
    public String put() {
        return "PUT Method 요청";
    }

    @DeleteMapping("/api/delete")
    @ResponseBody
    public String delete() {
        return "DELETE Method 요청";
    }
}
