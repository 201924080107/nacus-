package com.youkeda.comment.service.impl;

import com.youkeda.comment.dao.UserDAO;
import com.youkeda.comment.dataobject.UserDO;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 姬军伟
 * @date 2022/3/25 - 19:50
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Value("${code.600.message}")
    private String message600;
    @Override
    public Result<User> register(String name, String pwd) {
        Result<User> result = new Result<>();
        if (!StringUtils.hasText(name)){
            result.setSuccess(false);
            result.setCode("600");
            result.setMessage(message600);
            return result;
        }
        if (!StringUtils.hasText(pwd)){
            result.setSuccess(false);
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }
        UserDO userDO = userDAO.findByUserName(name);
        if (userDO != null){
            result.setSuccess(false);
            result.setCode("602");
            result.setMessage("用户名已存在");
            return result;
        }
        String enPwd = pwd + "jjw147258";
        String md5Pwd = DigestUtils.md5Hex(enPwd).toUpperCase();

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(name);
        userDO1.setNickName(name);
        userDO1.setPwd(md5Pwd);
        userDAO.add(userDO1);

        result.setSuccess(true);

        User user = userDO1.toModel();
        result.setData(user);
        return result;
    }

    @Override
    public Result<User> login(String name, String pwd, HttpServletRequest request) {
        Result<User> result = new Result<>();
        if (!StringUtils.hasText(name)){
            result.setSuccess(false);
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (!StringUtils.hasText(pwd)){
            result.setSuccess(false);
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }
        UserDO userDO = userDAO.findByUserName(name);
        if (userDO == null){
            result.setSuccess(false);
            result.setCode("602");
            result.setMessage("用户名不存在");
            return result;
        }
        String enPwd = pwd + "jjw147258";
        String md5Pwd = DigestUtils.md5Hex(enPwd).toUpperCase();

        String pwd1 = userDO.getPwd();
        if (!pwd1.equals(md5Pwd)){
            result.setSuccess(false);
            result.setCode("603");
            result.setMessage("用户名或密码错误");
            return result;
        }
        result.setSuccess(true);
        User user = userDO.toModel();
        result.setData(user);

        request.getSession().setAttribute("userId",user.getId());
        return result;
    }

    @Override
    public List<User> findById(List<Long> ids) {
        List<UserDO> byIds = userDAO.findByIds(ids);
        List<User> users = new ArrayList<>();
        for (UserDO byId : byIds) {
            User user = byId.toModel();
            users.add(user);
        }
        return users;
    }
}
