/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.inv.h2.entity.CurrencyH2;
import com.cv.inv.h2.entity.CurrencyKeyH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WSwe
 */
@Repository
public class CurrencyH2DaoImpl extends AbstractDao<CurrencyKeyH2, CurrencyH2> implements CurrencyH2Dao {

    @Override
    public CurrencyH2 save(CurrencyH2 cur) {
        persist(cur);
        return cur;
    }

    @Override
    public CurrencyH2 findById(CurrencyKeyH2 id) {
        CurrencyH2 cur = getByKey(id);
        return cur;
    }

    @Override
    public CurrencyH2 findById(String id) {

        return null;
    }

    @Override
    public List<CurrencyH2> search(String code, String name, String compCode) {
        String strSql = "select o from CurrencyH2 o ";
        String strFilter = "";

        if (!code.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.code like '" + code + "%'";
            } else {
                strFilter = strFilter + " and o.key.code like '" + code + "%'";
            }
        }

        if (!name.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.currencyName like '%" + name + "%'";
            } else {
                strFilter = strFilter + " and o.currencyName like '%" + name + "%'";
            }
        }

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.compCode = " + compCode;
            } else {
                strFilter = strFilter + " and o.key.compCode = " + compCode;
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<CurrencyH2> listCurrency = findHSQL(strSql);
        return listCurrency;
    }

    @Override
    public int delete(String code, String compCode) {
        String strSql = "delete from CurrencyH2 o where o.key.code = '" + code
                + "' and o.key.compId = " + compCode;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
