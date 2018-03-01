package com.shang.shiro.chapter3;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Assert;
import org.junit.Test;

/**
 * 基于资源的访问控制（显示角色）
 * 这样的方式的一般规则是“资源标识符：操作”，即是资源级别的粒度；这种方式的好处就是如果
 * 要修改基本都是一个资源级别的修改，不会对其他模块代码产生影响，粒度小。但是实现
 * 起来可能稍微复杂点，需要维护“用户——角色，角色——权限（资源：操作）”之间的
 * 关系。
 * @author NICK
 * @date 2017-12-12 20:35
 **/
public class PermissionTest extends BaseTest {

    /**
     * 判断是否有权限
     */
    @Test
    public void testIsPermitted() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    /**
     * 失败的情况下会抛出 UnauthorizedException 异常
     */
    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        // 断言拥有权限：user:create
        subject().checkPermission("user:create");
        // 断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        // 断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }



    @Test
    public void testWildcardPermission1() {
        login("classpath:shiro-permission.ini", "li", "123");

        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }

    /**
     * 单个资源全部权限
     */
    @Test
    public void testWildcardPermission2() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("system:user:create,delete,update:view");

        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");
    }

    /**
     * 所有资源全部权限
     * role61=*:view
     */
    @Test
    public void testWildcardPermission3() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("user:view");

        subject().checkPermissions("system:user:view");
    }

    /**
     * 单个实例多个权限
     */
    @Test
    public void testWildcardPermission4() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("user:view:1");

        // 对资源 user 的 1 实例拥有 update、delete 权限。
        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");

        // 对资源 user 的 1 实例拥有所有权限
        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");

        //对资源 user 的 1 实例拥有所有权限
        subject().checkPermissions("user:auth:1", "user:auth:2");

    }

    /**
     * 单个实例所有权限
     */
    @Test
    public void testWildcardPermission5() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("menu:view:1");

        subject().checkPermissions("organization");
        subject().checkPermissions("organization:view");
        subject().checkPermissions("organization:view:1");

    }


    /**
     * 所有实例单个权限
     */
    @Test
    public void testWildcardPermission6() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermission("menu:view:1");
        subject().checkPermission(new WildcardPermission("menu:view:1"));

    }

}
