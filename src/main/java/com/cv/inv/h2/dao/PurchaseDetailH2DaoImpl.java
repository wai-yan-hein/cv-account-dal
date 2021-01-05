/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.PurHisDetailH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class PurchaseDetailH2DaoImpl extends AbstractDao<String, PurHisDetailH2> implements PurchaseDetailH2Dao {

    @Override
    public PurHisDetailH2 save(PurHisDetailH2 pd) {
        persist(pd);
        return pd;
    }

    @Override
    public List<PurHisDetailH2> search(String vouId) {
        String hsql = "select o from PurHisDetail o where o.purDetailKey.vouId = '" + vouId + "'";
        return findHSQL(hsql);

    }

    @Override
    public int delete(String id) {
        String strSql = "delete from PurchaseDetail o where o.purDetailKey.purDetailId = '" + id + "'";
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
