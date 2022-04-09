package com.youkeda.exercise.demo.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 姬军伟
 * @date 2022/3/25 - 9:55
 */
@Controller
public class HelloControl {
    @RequestMapping("/demo/api/hello")
    @ResponseBody
    public String helloWorld(){
        return "HelloWorld";
    }
}
