/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.inv.h2.entity.TraderTypeH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class TraderTypeH2DaoImpl extends AbstractDao<String, TraderTypeH2> implements TraderTypeH2Dao {

    @Override
    public TraderTypeH2 save(TraderTypeH2 ch) {
        persist(ch);
        return ch;
    }

    @Override
    public List<TraderTypeH2> findAll() {
        String hsql = "select o from TraderTypeH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from TraderTypeH2 o where o.typeId='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

    @Override
    public TraderTypeH2 findById(String id) {
        return getByKey(id);
    }

    @Override
    public List<TraderTypeH2> search(String desp) {
        String strSql = "";

        if (!desp.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "o.description like '%" + desp + "%'";
            } else {
                strSql = strSql + " and o.description like '%" + desp + "%'";
            }
        }

        if (strSql.isEmpty()) {
            strSql = "select o from TraderTypeH2 o";
        } else {
            strSql = "select o from TraderTypeH2 o where " + strSql;
        }

        List<TraderTypeH2> listTT = findHSQL(strSql);
        return listTT;
    }

}
