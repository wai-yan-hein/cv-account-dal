/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.UserDefault;
import com.cv.accountswing.entity.UserDefaultKey;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class DefaultUserDaoImpl extends AbstractDao<UserDefaultKey, UserDefault> implements DefaultUserDao {

    @Override
    public UserDefault save(UserDefault du) {
        persist(du);
        return du;
    }

    @Override
    public List<UserDefault> search(String user) {
        String hsql = "select o from UserDefault o where o.key.userCOde = '" + user + "'";
        return findHSQL(hsql);
    }

    @Override
    public UserDefault findById(UserDefaultKey key) {
        return getByKey(key);
    }

    @Override
    public List<UserDefault> search(String roleCode, String compCode, String key) {
        String hsql = "select o from UserDefault o where o.key.roleCode = '" + roleCode + "'"
                + " and o.key.compCode = '" + compCode + "' and o.key.key = '" + key + "'";
        return findHSQL(hsql);
    }

    @Override
    public void delete(String roleCode, String compCode, String key) {
        try {
            String strSql = "delete from role_setting where role_code = '" + roleCode + "'"
                    + " and comp_code = '" + compCode + "' and default_key = '" + key + "'";
            execSQL(strSql);
        } catch (Exception ex) {
        }
    }

}
