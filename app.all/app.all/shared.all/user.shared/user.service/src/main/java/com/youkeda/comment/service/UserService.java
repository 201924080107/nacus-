package com.youkeda.comment.service;

import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author 姬军伟
 * @date 2022/3/25 - 19:42
 */

public interface UserService {
    public Result<User> register(String name, String pwd);
    public Result<User> login(String name, String pwd, HttpServletRequest request);
    public List<User> findById(List<Long> ids);
}
