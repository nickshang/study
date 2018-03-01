package com.shang.shiro.chapter6.service;


import com.shang.shiro.chapter6.dao.PermissionDao;
import com.shang.shiro.chapter6.entity.Permission;
import com.shang.shiro.chapter6.dao.PermissionDaoImpl;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
