package com.sparta.springmvc.response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/response")
public class ResponseController {
    // Content - Type : text/html
    // ResponseBody
    // {"name" : "Robbie", "age" : 95}

    @GetMapping("/json/string")
    @ResponseBody
    public String helloStringJson() {
        return "{\"name\":\"Robbie\",\"age\":95}";
    }

    // Content -T ype : application/json
    // ResponseBody
    // {"name : 하승", "age" : 25}
    @GetMapping("/json/class")
    @ResponseBody
    public Star helloClassJson(){
        return new Star("하승",25);
    }
}













