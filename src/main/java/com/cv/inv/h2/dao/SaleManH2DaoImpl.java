/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.SaleManH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Repository
public class SaleManH2DaoImpl extends AbstractDao<String, SaleManH2> implements SaleManH2Dao {

    @Override
    public SaleManH2 save(SaleManH2 saleMan) {
        persist(saleMan);
        return saleMan;
    }

    @Override
    public List<SaleManH2> findAll() {
        String hsql = "select o from SaleManH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from SaleManH2 o where o.saleManId='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

    @Override
    public SaleManH2 findById(String id) {
        return getByKey(id);
    }

}
