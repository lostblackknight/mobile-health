package io.github.lostblackknight.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello message";
    }
}
