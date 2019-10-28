package com.example.springsecurityjwt.constant;

/**
 * ConstantKey:配置签名(放在类里面安全但是不方便修改,放在配置文件可以使用加密等方式,这里按照简单方式来)
 *
 * @author zhangxiaoxiang
 * @date 2019/10/28
 */
public class TokenKey {

    /**
     * 签名key
     */
    // public static final String SIGNING_KEY = "spring-security-@Jwt!&Secret^#";
    public static final String SIGNING_KEY = "MyJwtSecret";
}
