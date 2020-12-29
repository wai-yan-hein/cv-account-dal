/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.AppUserH2;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAI
 */
@Repository
public class AccountH2DaoImpl extends AbstractDao<Integer, AppUserH2> implements AccountH2Dao {

    @Override
    public AppUserH2 saveAccount(AppUserH2 au) {
        persist(au);
        return au;
    }

    @Override
    public AppUserH2 findUserByShort(String userShort) {
        List<AppUserH2> listAU = search("-", userShort, "-", "-");
        AppUserH2 au = null;

        if (listAU != null) {
            if (!listAU.isEmpty()) {
                au = listAU.get(0);
            }
        }

        return au;
    }
   

    @Override
    public List<AppUserH2> search(String id, String userShort, String email, String owner) {
      String strSql = "select o from AppUserH2 o";
        String strFilter = null;

        if (!id.equals("-")) {
            if (strFilter == null) {
                strFilter = "o.userId like '" + id + "%'";
            } else {
                strFilter = strFilter + " and o.userId like '" + id + "%'";
            }
        }

        if (!userShort.equals("-")) {
            if (strFilter == null) {
                strFilter = "o.userShort = '" + userShort + "'";
            } else {
                strFilter = strFilter + " and o.userShort = '" + userShort + "'";
            }
        }

        if (!email.equals("-")) {
            if (strFilter == null) {
                strFilter = "o.userId = '" + email + "'";
            } else {
                strFilter = strFilter + " and o.userId = '" + email + "'";
            }
        }

        if (!owner.equals("-")) {
            if (strFilter == null) {
                strFilter = "(o.owner = " + owner + " or o.userId = " + owner + ")";
            } else {
                strFilter = strFilter + " and (o.owner = " + owner + " or o.userId = " + owner + ")";
            }
        }

        if (strFilter != null) {
            strSql = strSql + " where " + strFilter;
        }

        List<AppUserH2> listAU = findHSQL(strSql);
        return listAU;
    }

    @Override
    public AppUserH2 login(String user, String password) throws AuthenticationException {
        AppUserH2 au = findUserByShort(user);

        if (au == null) {
            throw new AuthenticationException(
                    "Either username/password is worng.");
        } else if (!au.getActive()) {
            throw new AuthenticationException(
                    "Either username/password is worng.");
        } else if (!au.getPassword().equals(password)) {
            throw new AuthenticationException(
                    "Either username/password is worng.");
        }

        return au;
    }

    @Override
    public int delete(String userId) {
         String strSql = "delete from AppUserH2 o where o.userId = " + userId;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

    @Override
    public AppUserH2 finfById(String id) {
        return getByKey(Integer.parseInt(id));
     }

}
