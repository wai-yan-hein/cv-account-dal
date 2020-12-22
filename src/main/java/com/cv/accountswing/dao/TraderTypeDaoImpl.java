/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.TraderType;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class TraderTypeDaoImpl extends AbstractDao<String, TraderType> implements TraderTypeDao {

    @Override
    public TraderType save(TraderType ch) {
        persist(ch);
        return ch;
    }

    @Override
    public List<TraderType> findAll() {
        String hsql = "select o from TraderType o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from TraderType o where o.typeId='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

    @Override
    public TraderType findById(String id) {
        return getByKey(id);
    }

    @Override
    public List<TraderType> search(String desp) {
        String strSql = "";

        if (!desp.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "o.description like '%" + desp + "%'";
            } else {
                strSql = strSql + " and o.description like '%" + desp + "%'";
            }
        }

        if (strSql.isEmpty()) {
            strSql = "select o from TraderType o";
        } else {
            strSql = "select o from TraderType o where " + strSql;
        }

        List<TraderType> listTT = findHSQL(strSql);
        return listTT;
    }

}
