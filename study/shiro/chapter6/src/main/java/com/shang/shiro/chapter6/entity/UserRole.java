package com.shang.shiro.chapter6.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户角色关系
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Data
@ToString
public class UserRole implements Serializable {

    private Long userId;
    private Long roleId;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRole userRole = (UserRole) o;

        if (roleId != null ? !roleId.equals(userRole.roleId) : userRole.roleId != null) {
            return false;
        }
        if (userId != null ? !userId.equals(userRole.userId) : userRole.userId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

}