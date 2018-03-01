package com.shang.shiro.chapter6.service;

import com.shang.shiro.chapter6.entity.Role;

/**
 * 角色
 * Created by Think on 2017/12/1S5.
 */
public interface RoleService {
    /**
     * 创建角色
     * @param role
     * @return
     */
    public Role createRole(Role role);

    /**
     * 删除角色
     * @param roleId
     */
    public void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);//
}
