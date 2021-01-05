/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.StockH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class StockH2DaoImpl extends AbstractDao<String, StockH2> implements StockH2Dao {

    @Override
    public StockH2 save(StockH2 stock) {
        persist(stock);
        return stock;
    }

    @Override
    public StockH2 findById(String id) {
        return getByKey(id);
    }

    @Override
    public List<StockH2> findAll() {
        String hsql = "select o from StockH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from StockH2 o where o.stock_code = '" + id + "'";
        return execUpdateOrDelete(hsql);
    }

    @Override
    public Object getMax(String sql) {
        return getAggregate(sql);
    }

    @Override
    public List<StockH2> findActiveStock() {
        String hsql = "select o from StockH2 o where o.isActive=true";
        return findHSQL(hsql);

    }

    @Override
    public List<StockH2> search(String saleInvId) {
        String strFilter = "";
        if (!saleInvId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "v.stockType = '" + saleInvId + "'";
            } else {
                strFilter = strFilter + " and v.stockType = '" + saleInvId + "'";
            }
        }
        String strSql = "select v from StockH2 v";

        List<StockH2> listDH = null;
        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
            listDH = findHSQL(strSql);
        }

        return listDH;
    }

    @Override
    public List<StockH2> searchC(String saleInvId) {
        String strFilter = "";
        if (!saleInvId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "v.category = '" + saleInvId + "'";
            } else {
                strFilter = strFilter + " and v.category = '" + saleInvId + "'";
            }
        }
        String strSql = "select v from StockH2 v";

        List<StockH2> listDH = null;
        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
            listDH = findHSQL(strSql);
        }

        return listDH;
    }

    @Override
    public List<StockH2> searchB(String saleInvId) {
        String strFilter = "";
        if (!saleInvId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "v.brand = '" + saleInvId + "'";
            } else {
                strFilter = strFilter + " and v.brand = '" + saleInvId + "'";
            }
        }
        String strSql = "select v from StockH2 v";

        List<StockH2> listDH = null;
        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
            listDH = findHSQL(strSql);
        }

        return listDH;
    }
}
