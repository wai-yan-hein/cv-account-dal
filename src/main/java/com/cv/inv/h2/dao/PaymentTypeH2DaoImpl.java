/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.accountswing.entity.PaymentType;
import com.cv.inv.h2.entity.PaymentTypeH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class PaymentTypeH2DaoImpl extends AbstractDao<Integer, PaymentTypeH2> implements PaymentTypeH2Dao {

    @Override
    public PaymentTypeH2 save(PaymentTypeH2 pt) {
        persist(pt);
        return pt;
    }

    @Override
    public PaymentTypeH2 findById(Integer id) {
        PaymentTypeH2 pt = getByKey(id);
        return pt;
    }

    @Override
    public List<PaymentTypeH2> search(String name, String compId) {
        String strSql = "select o from PaymentTypeH2 o ";
        String strFilter = "";

        if (!name.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.paymentTypeName like '%" + name + "%'";
            } else {
                strFilter = strFilter + " and o.paymentTypeName like '%" + name + "%'";
            }
        }

        if (!compId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.compId = " + compId;
            } else {
                strFilter = strFilter + " and o.compId = " + compId;
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<PaymentTypeH2> listPT = findHSQL(strSql);
        return listPT;
    }

    @Override
    public int delete(Integer id, String compId) {
        String strSql = "delete from PaymentTypeH2 o where o.typeId = " + id
                + " and o.compId = " + compId;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
