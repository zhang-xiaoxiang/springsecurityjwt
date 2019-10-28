package com.example.springsecurityjwt.util;


import com.example.springsecurityjwt.constant.TokenKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @FileName: JwtHelper
 * @Author: zhaoxinguo
 * @Date: 2018/12/10 19:39
 * @Description: 实现Jwt
 */
public class JwtUtil {

    public static String createToken(String username) {
        // 这里可以根据用户信息查询对应的角色信息，这里为了简单，我直接设置个空list
        // (可以理解为客户端APP用户一般不考虑角色权限,至于类似VIP等级也是可以考虑处理为角色全新来,但是一般不考虑,
        // 如果处理后台用户,那么一般就要分角色权限)
        List roleList = new ArrayList<>();
        String token = Jwts.builder()
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid(或者角色)之类的，作为什么用户的唯一标志。
                .setSubject(username)
                // 设置过期时间 365 * 24 * 60 * 60秒(这里为了方便测试，所以设置了1年的过期时间，实际项目需要根据自己的情况修改)
                // (这个注意一下,设置这个是告诉权限框架,但是这个token给前端,还是得用redis管理才科学,解释如下)
                //这个token应该是使用后只存在一个,就是说,比如你登录了6次,6次生成的token都是可以使用的,显然不符合逻辑,(在第二次登陆时删除之前的token)
                // 把这个给redis管理,在redis查询到用户的token过期就算过期,且只有一个有效,之前的token无效(用逻辑代码处理)
                // .setExpiration(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000))
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                //采用什么算法是可以自己选择的，不一定非要采用HS512,使用的签名key =>TokenKey.SIGNING_KEY
                .signWith(SignatureAlgorithm.HS512, TokenKey.SIGNING_KEY)
                //iat: jwt的签发时间
                .setIssuedAt(new Date())
                //其他设置...
                .compact();
        // 登录成功后，返回token到header里面(直接按照正常返回到response也是可以的,一般放在header就行了)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.addHeader("Authorization", "Bearer " + token);
        return "Bearer " + token;
    }





}
