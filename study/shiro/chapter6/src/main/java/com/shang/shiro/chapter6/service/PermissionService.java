package com.shang.shiro.chapter6.service;

import com.shang.shiro.chapter6.entity.Permission;

/**
 * 权限
 * @author NICK
 * @date 2017-12-15 17:39
 **/
public interface PermissionService {

    /**
     * 创建权限
     * @param permission
     * @return
     */
    public Permission createPermission(Permission permission);

    /**
     * 删除限制
     * @param permissionId
     */
    public void deletePermission(Long permissionId);

}
