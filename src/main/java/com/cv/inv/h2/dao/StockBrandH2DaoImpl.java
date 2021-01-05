/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.StockBrandH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAI
 */
@Repository
public class StockBrandH2DaoImpl extends AbstractDao<String, StockBrandH2> implements StockBrandH2Dao {

    @Override
    public StockBrandH2 save(StockBrandH2 brand) {
        persist(brand);
        return brand;
    }
    
      @Override
    public List<StockBrandH2> findAll() {
        String hsql = "select o from StockBrandH2 o";
        List<StockBrandH2> listStockBrand = findHSQL(hsql);
        return listStockBrand;
    }

    @Override
    public StockBrandH2 findById(String id) {
        StockBrandH2 mh2 = getByKey(id);
        return mh2;
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from StockBrandH2 o where o.brandId='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

}
