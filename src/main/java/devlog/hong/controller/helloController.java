package devlog.hong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class helloController {

    @GetMapping("")
    public String hello() {
        System.out.println("hello 진입");
        return "hello world";
    }

    @RequestMapping("/no")
    public String no() {
        return "no";
    }
}
