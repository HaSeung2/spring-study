package com.sparta.springmvc.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class RequestController {

    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    // [Request sample]
    // GET http://localhost:8080/hello/request/star/하승/age/25
    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age)
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
    // GET http://localhost:8080/hello/request/form/param?name=하승&age=25
    @GetMapping("/form/param")
    @ResponseBody
    // @RequestParam 생략 가능.
    public String helloGetRequestParam(@RequestParam(required = false) String name, int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
    // POST http://localhost:8080/hello/request/form/param
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=하승&age=25
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
    // POST http://localhost:8080/hello/request/form/model
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=하승&age=25
    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    // [Request sample]
    // GET http://localhost:8080/hello/request/form/param/model?name=하승&age=25
    @GetMapping("/form/param/model")
    @ResponseBody
    // @ModelAttrubyte << 생략 가능
    public String helloRequestParam(Star star) {
        return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    // [Request sample]
    // POST http://localhost:8080/hello/request/form/json
    // Header
    //  Content type: application/json
    // Body
    //  {"name":"하승","age":"25"}
    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }



}
