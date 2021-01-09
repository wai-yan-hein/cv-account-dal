/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.UsrCompRole;
import com.cv.accountswing.entity.UsrCompRoleKey;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class UsrCompRoleDaoImpl extends AbstractDao<UsrCompRoleKey, UsrCompRole> implements UsrCompRoleDao {

    @Override
    public UsrCompRole save(UsrCompRole ucr) {
        persist(ucr);
        return ucr;
    }

    @Override
    public UsrCompRole findById(UsrCompRoleKey key) {
        UsrCompRole ucr = getByKey(key);
        return ucr;
    }

    @Override
    public List<UsrCompRole> search(String userCode, String compCode, String roleCode) {
        String strSql = "select o from UsrCompRole o ";
        String strFilter = "";

        if (!userCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.userCode = " + userCode;
            } else {
                strFilter = strFilter + " and o.key.userCode = " + userCode;
            }
        }

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.compCode = '" + compCode + "'";
            } else {
                strFilter = strFilter + " and o.key.compCode = '" + compCode + "'";
            }
        }

        if (!roleCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.roleCode = " + roleCode;
            } else {
                strFilter = strFilter + " and o.key.roleCode = " + roleCode;
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<UsrCompRole> listUsrCompRole = findHSQL(strSql);
        return listUsrCompRole;
    }

    @Override
    public List getAssignRole(String userCode) {
        String strSql = "select o from VUsrCompRole o where o.key.userCode = " + userCode;
        List listAssignRole = findHSQL(strSql);
        return listAssignRole;
    }

    @Override
    public List getAssignCompany(String userCode) {
        String strSql = "select o from VUsrCompAssign o where o.key.userCode = '" + userCode + "'";
        List listAssignComp = findHSQL(strSql);
        return listAssignComp;
    }

    @Override
    public List getAssignCompany(String userCode, String roleCode, String compCode) {
        String strSql = "select o from VUsrCompAssign o where o.key.userCode = "
                + userCode + " and o.key.roleCode = " + roleCode + " and o.key.compCode = " + compCode;
        List listAssignComp = findHSQL(strSql);
        return listAssignComp;
    }

    @Override
    public int delete(String userCode, String compCode, String roleCode) {
        String strSql = "delete from UsrCompRole o where o.key.userCode = "
                + userCode + " and o.key.compCode = " + compCode + " and o.key.roleCode = " + roleCode;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
