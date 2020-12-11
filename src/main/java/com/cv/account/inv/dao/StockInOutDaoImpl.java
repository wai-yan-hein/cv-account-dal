/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.dao;

import com.cv.account.api.dao.AbstractDao;
import com.cv.account.inv.entity.StockInOut;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class StockInOutDaoImpl extends AbstractDao<String, StockInOut> implements StockInOutDao {

    @Override
    public StockInOut findById(String id) {
        return getByKey(id);
    }

    @Override
    public StockInOut save(StockInOut stock) {
        persist(stock);
        return stock;
    }

    @Override
    public List<StockInOut> search(String batchCode, String date, String desp, String remark) {
        String hsql;
        return null;
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from StockInOut o where o.batchCode '" + id + "'";
        return execUpdateOrDelete(hsql);

    }

}
