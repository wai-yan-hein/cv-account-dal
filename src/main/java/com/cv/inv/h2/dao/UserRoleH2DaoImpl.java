/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.inv.h2.entity.UserRoleH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class UserRoleH2DaoImpl extends AbstractDao<Integer, UserRoleH2> implements UserRoleH2Dao {

    @Override
    public UserRoleH2 save(UserRoleH2 role) {
        persist(role);
        return role;
    }

    @Override
    public UserRoleH2 findById(Integer id) {
        UserRoleH2 role = getByKey(id);
        return role;
    }

    @Override
    public List<UserRoleH2> search(String roleName, String compCode) {
        String strSql = "select o from UserRoleH2 o ";
        String strFilter = "";

        if (!roleName.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.roleName like '" + roleName + "%'";
            } else {
                strFilter = strFilter + " and o.roleName like '" + roleName + "%'";
            }
        }

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.compCode = '" + compCode + "'";
            } else {
                strFilter = strFilter + " and o.compCode = '" + compCode + "'";
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<UserRoleH2> listUserRole = findHSQL(strSql);
        return listUserRole;
    }

    @Override
    public int delete(String id) {
        String strSql = "delete from UserRoleH2 o where o.roleId = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

    @Override
    public List<UserRoleH2> searchM(String updatedDate) {
        String strSql = "select o from UserRoleH2 o where o.updatedDate > '" + updatedDate + "'";
        List<UserRoleH2> listUR = findHSQL(strSql);
        /*for (UserRole ur : listUR) {
            if (ur.getPrivilege().size() > 0) {
                ur.setPrivilege(ur.getPrivilege());
            }
        }*/
        return listUR;
    }
}
