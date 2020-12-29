/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.StockUnitH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class StockUnitH2DaoImpl extends AbstractDao<String, StockUnitH2> implements StockUnitH2Dao {

    @Override
    public StockUnitH2 save(StockUnitH2 item) {
        persist(item);
        return item;
    }

    @Override
    public List<StockUnitH2> findAll() {
        String hsql = "select o from StockUnitH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from StockUnitH2 o where o.itemUnitCode='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

}
