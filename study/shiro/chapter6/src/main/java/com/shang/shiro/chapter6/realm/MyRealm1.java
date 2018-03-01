package com.shang.shiro.chapter6.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Realm：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager
 * 要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；
 * 也需要从 Realm 得到用户相应的角色/权限进行验证用户是否能进行操作；可以把 Realm 看
 * 成 DataSource ， 即 安 全 数 据 源 。
 *
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-29
 * <p>Version: 1.0
 */
public class MyRealm1 implements Realm {

    //  返回一个唯一的 Realm 名字
    @Override
    public String getName() {
        // realm name 为 “a”
        return "a";
    }

    // 判断此 Realm 是否支持此 Token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    // 根据 Token 获取认证信息
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo(
                // 身份 字符串类型
                "zhang",
                // 凭据
                "123",
                // Realm Name
                getName()
        );
    }
}
