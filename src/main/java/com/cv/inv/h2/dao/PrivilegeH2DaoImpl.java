/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.accountswing.entity.Privilege;
import com.cv.accountswing.entity.PrivilegeKey;
import com.cv.inv.h2.entity.PrivilegeH2;
import com.cv.inv.h2.entity.PrivilegeKeyH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class PrivilegeH2DaoImpl extends AbstractDao<PrivilegeKeyH2, PrivilegeH2> implements PrivilegeH2Dao {

    @Override
    public PrivilegeH2 save(PrivilegeH2 privilege) {
        persist(privilege);
        return privilege;
    }

    @Override
    public PrivilegeH2 findById(PrivilegeKeyH2 key) {
        PrivilegeH2 privilege = getByKey(key);
        return privilege;
    }

    @Override
    public List<PrivilegeH2> search(String roleId, String menuId) {
        String strSql = "select o from PrivilegeH2 o ";
        String strFilter = "";

        if (!roleId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.roleId = " + roleId;
            } else {
                strFilter = strFilter + " and o.key.roleId = " + roleId;
            }
        }

        if (!menuId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.menuId = " + menuId;
            } else {
                strFilter = strFilter + " and o.key.menuId = " + menuId;
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<PrivilegeH2> listPrivilege = findHSQL(strSql);
        return listPrivilege;
    }

    @Override
    public int delete(String roleId, String menuId) {
        String strSql = "delete from PrivilegeH2 o where o.key.roleId = "
                + roleId + " and o.key.menuId = " + menuId;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

    @Override
    public void copyPrivilege(String fromRoleId, String toRoleId) throws Exception {
        String strSql = "insert into privilege(role_id, menu_id) "
                + "select " + toRoleId + ", menu_id "
                + "from privilege where role_id = " + fromRoleId;
        execSQL(strSql);
    }

    @Override
    public List<PrivilegeH2> searchM() {
        String strSql = "select o from Privilege o";
        List<PrivilegeH2> listPrv = findHSQL(strSql);
        return listPrv;
    }
}
