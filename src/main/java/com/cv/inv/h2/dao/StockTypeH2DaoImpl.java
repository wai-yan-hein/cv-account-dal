/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.StockTypeH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class StockTypeH2DaoImpl extends AbstractDao<String, StockTypeH2> implements StockTypeH2Dao {

    @Override
    public StockTypeH2 save(StockTypeH2 item) {
        persist(item);
        return item;
    }

    @Override
    public List<StockTypeH2> findAll() {
        String hsql = "select o from StockTypeH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from StockTypeH2 o where o.itemTypeCode='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

}
