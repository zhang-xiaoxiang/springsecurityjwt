package com.example.springsecurityjwt.controller;


import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.repository.UserRepository;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * UserController:用户接口
 *
 * @author zhangxiaoxiang
 * @date 2019/10/28
 */


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/hello")
    public String hello() {
        System.out.println("---------------进入了一个普通的接口----------");
        return "{\n" +
                "\tcode: 200,\n" +
                "\tdata: \"我的后端返回的模拟数据\",\n" +
                "\tmsg: \"模拟个json返回\"\n" +
                "}";
    }

    /**
     * 该方法是注册用户的方法，默认放开访问控制
     *
     * @param user
     */
    @PostMapping("/register")
    public void signUp(@RequestBody User user) {
        System.out.println("---------------" + user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * 用户登录
     * <p>
     * 自定义生成Token，因为使用了自定义登录，就不会执行JWTLoginFilter了，所以需要字段调用生成token并返给前端
     *
     * @param user
     * @param response
     * @return
     */
    @PostMapping(value = "/login")
    public Object login(@RequestBody User user, HttpServletResponse response) {
        User userVo = userRepository.findByUsername(user.getUsername());
        System.out.println("---->" + userVo);
        if (userVo != null) {
            String token = JwtUtil.createToken(userVo.getUsername());
            System.out.println("Authorization:" + token);
            return "{\n" +
                    "\tcode: 200,\n" +
                    "\tdata: \"Authorization:" + token + "\",\n" +
                    "\tmsg: \"模拟个json返回\"\n" +
                    "}";
        }
        return "登录失败,token无效导致!";
    }


}
