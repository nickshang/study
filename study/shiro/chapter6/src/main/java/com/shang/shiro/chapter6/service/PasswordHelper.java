package com.shang.shiro.chapter6.service;

import com.shang.shiro.chapter6.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 2;

    public void encryptPassword(User user) {

        // 设置盐值
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        // 密码转换
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),

                // 盐值
                ByteSource.Util.bytes(user.getCredentialsSalt()),

                // 迭代次数
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
