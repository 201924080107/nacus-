package com.youkeda.comment.api;

import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 姬军伟
 * @date 2022/3/25 - 20:14
 */
@Controller
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/reg")
    @ResponseBody
    public Result<User> reg(@RequestBody User user){
        Result<User> result = new Result<>();
        result = userService.register(user.getUserName(), user.getPwd());
        return result;
    }

    @PostMapping("/api/user/login")
    @ResponseBody
    public Result<User> login(@RequestBody User user, HttpServletRequest request){
        Result<User> result = new Result<>();
        result = userService.login(user.getUserName(), user.getPwd(),request);
        return result;
    }

    @PostMapping("/api/user/loginOut")
    @ResponseBody
    public Result<User> loginOut(@RequestBody User user, HttpServletRequest request){
        Result<User> result = new Result<>();
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        result.setSuccess(true);
        return result;
    }
}
