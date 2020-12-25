/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import org.springframework.stereotype.Repository;
import com.cv.inv.h2.entity.SaleHisDetailH2;
import java.util.List;

/**
 *
 * @author SAI
 */
@Repository
public class SaleHisDetailH2DaoImpl extends AbstractDao<String, SaleHisDetailH2> implements SaleHisDetailH2Dao {

    @Override
    public SaleHisDetailH2 save(SaleHisDetailH2 sdh) {
        persist(sdh);
        return sdh;
    }

    @Override
    public List<SaleHisDetailH2> search(String vouId) {
        String hsql = "select o from SaleHisDetailH2 o where o.saleDetailKeyH2.vouId ='" + vouId + "'";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String strSql = "delete from SaleHisDetailH2 o where o.saleDetailKeyH2.saleDetailId = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

}
