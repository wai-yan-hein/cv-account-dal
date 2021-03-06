/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.Privilege;
import com.cv.accountswing.entity.PrivilegeKey;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class PrivilegeDaoImpl extends AbstractDao<PrivilegeKey, Privilege> implements PrivilegeDao {

    @Override
    public Privilege save(Privilege privilege) {
        persist(privilege);
        return privilege;
    }

    @Override
    public Privilege findById(PrivilegeKey key) {
        Privilege privilege = getByKey(key);
        return privilege;
    }

    @Override
    public List<Privilege> search(String roleId, String menuId) {
        String strSql = "select o from Privilege o ";
        String strFilter = "";

        if (!roleId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.roleCode = " + roleId;
            } else {
                strFilter = strFilter + " and o.key.roleCode = " + roleId;
            }
        }

        if (!menuId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.menuCode = " + menuId;
            } else {
                strFilter = strFilter + " and o.key.menuCode = " + menuId;
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<Privilege> listPrivilege = findHSQL(strSql);
        return listPrivilege;
    }

    @Override
    public int delete(String roleId, String menuId) {
        String hsql = "";
        if (menuId.equals("-")) {
            hsql = "delete from Privilege o where o.key.roleCode = '"
                    + roleId + "'";
        } else {
            hsql = "delete from Privilege o where o.key.roleCode = '"
                    + roleId + "' and o.key.menuCode = '" + menuId + "'";
        }
        int cnt = execUpdateOrDelete(hsql);
        return cnt;
    }
    @Override
    public void copyPrivilege(String fromRoleId, String toRoleId) throws Exception {
        String strSql = "insert into privilege(role_code, menu_code) "
                + "select " + toRoleId + ", menu_code "
                + "from privilege where role_code = " + fromRoleId;
        execSQL(strSql);
    }

    @Override
    public List<Privilege> searchM() {
        String strSql = "select o from Privilege o";
        List<Privilege> listPrv = findHSQL(strSql);
        return listPrv;
    }
}
