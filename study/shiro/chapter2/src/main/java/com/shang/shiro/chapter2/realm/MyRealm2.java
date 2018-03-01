package com.shang.shiro.chapter2.realm;/**
 * Created by Think on 2017/12/12.
 */

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author NICK
 * @date 2017-12-12 12:29
 **/
public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持 UsernamePasswordToken 类型的 Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        // 得到用户名
        String username = (String) token.getPrincipal();
        // 得到密码
        String password = new String((char[]) token.getCredentials());

        // 如果用户名错误
        if (!"shang".equals(username)) {
            throw new UnknownAccountException();
        }
        // 如果密码错误
        if (!"shang123456_2".equals(password)) {
            throw new IncorrectCredentialsException();
        }

        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}