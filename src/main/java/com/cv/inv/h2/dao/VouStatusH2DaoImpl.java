/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.VouStatusH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Repository
public class VouStatusH2DaoImpl extends AbstractDao<Integer, VouStatusH2> implements VouStatusH2Dao {

    @Override
    public VouStatusH2 save(VouStatusH2 vouStatus) {
        persist(vouStatus);
        return vouStatus;
    }

    @Override
    public List<VouStatusH2> findAll() {
        String hsql = "select o from VouStatusH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from VouStatusH2 o where o.vouStatusId='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

    @Override
    public VouStatusH2 findById(String id) {
        return getByKey(Integer.parseInt(id));
    }

    @Override
    public List<VouStatusH2> search(String statusDesp) {
        String strSql = "";

        if (!statusDesp.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "o.statusDesp like '%" + statusDesp + "%'";
            } else {
                strSql = strSql + " and o.statusDesp like '%" + statusDesp + "%'";
            }
        }

        if (strSql.isEmpty()) {
            strSql = "select o from VouStatusH2 o";
        } else {
            strSql = "select o from VouStatusH2 o where " + strSql;
        }

        List<VouStatusH2> listVS = findHSQL(strSql);
        return listVS;
    }
}
