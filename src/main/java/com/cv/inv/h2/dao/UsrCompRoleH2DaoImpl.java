/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.accountswing.entity.UsrCompRoleKey;
import com.cv.inv.h2.entity.UsrCompRoleH2;
import com.cv.inv.h2.entity.UsrCompRoleKeyH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class UsrCompRoleH2DaoImpl extends AbstractDao<UsrCompRoleKeyH2, UsrCompRoleH2> implements UsrCompRoleH2Dao {

    @Override
    public UsrCompRoleH2 save(UsrCompRoleH2 ucr) {
        persist(ucr);
        return ucr;
    }

    @Override
    public UsrCompRoleH2 findById(UsrCompRoleKeyH2 key) {
        UsrCompRoleH2 ucr = getByKey(key);
        return ucr;
    }

    @Override
    public List<UsrCompRoleH2> search(String userId, String compCode, String roleId) {
        String strSql = "select o from UsrCompRoleH2 o ";
        String strFilter = "";

        if (!userId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.userId = " + userId;
            } else {
                strFilter = strFilter + " and o.key.userId = " + userId;
            }
        }

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.compCode = '" + compCode + "'";
            } else {
                strFilter = strFilter + " and o.key.compCode = '" + compCode + "'";
            }
        }

        if (!roleId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.roleId = " + roleId;
            } else {
                strFilter = strFilter + " and o.key.roleId = " + roleId;
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<UsrCompRoleH2> listUsrCompRole = findHSQL(strSql);
        return listUsrCompRole;
    }

    @Override
    public List getAssignRole(String userId) {
        String strSql = "select o from VUsrCompRole o where o.key.userId = " + userId;
        List listAssignRole = findHSQL(strSql);
        return listAssignRole;
    }

    @Override
    public List getAssignCompany(String userId) {
        //String strSql = "select o from VUsrCompAssign o where o.key.userId = " + userId;
       /* String strSql = "select ci from CompanyInfoH2 ci where ci.compId in"
                + "(select ucr.key.compCode from UsrCompRoleH2 ucr where ucr.key.userId ="
                + userId +")";*/
       String strSql ="select ucr from UsrCompRoleH2 ucr  where ucr.key.userId= "+ userId +" and "
               + "ucr.key.compCode in (select  ci.compId from CompanyInfoH2 ci)";
     
        List listAssignComp = findHSQL(strSql);
        return listAssignComp;
    }

    @Override
    public List getAssignCompany(String userId, String roleId, String compId) {
        String strSql = "select o from VUsrCompAssign o where o.key.userId = "
                + userId + " and o.key.roleId = " + roleId + " and o.key.compCode = " + compId;
        List listAssignComp = findHSQL(strSql);
        return listAssignComp;
    }

    @Override
    public int delete(String userId, String compCode, String roleId) {
        String strSql = "delete from UsrCompRoleH2 o where o.key.userId = "
                + userId + " and o.key.compCode = " + compCode + " and o.key.roleId = " + roleId;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
