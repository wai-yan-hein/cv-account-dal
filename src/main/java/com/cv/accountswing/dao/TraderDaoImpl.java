/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.Trader;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WSwe
 */
@Repository
public class TraderDaoImpl extends AbstractDao<String, Trader> implements TraderDao {

    @Override
    public Trader findById(String id) {
        Trader trader = getByKey(id);
        return trader;
    }

    @Override
    public List<Trader> searchTrader(String code, String name, String address,
            String phone, String parentCode, String compCode, String appTraderCode) {
        String strSql = "select o from Trader o ";
        String strFilter = "";

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.compCode = '" + compCode + "'";
            } else {
                strFilter = strFilter + " and o.compCode = '" + compCode + "'";
            }
        }

        if (!code.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.code like '" + code + "%'";
            } else {
                strFilter = strFilter + " and o.code like '" + code + "%'";
            }
        }

        if (!name.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.traderName like '%" + name + "%'";
            } else {
                strFilter = strFilter + " and o.traderName like '%" + name + "%'";
            }
        }

        if (!address.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.address like '%" + address + "%'";
            } else {
                strFilter = strFilter + " and o.address like '%" + address + "%'";
            }
        }

        if (!phone.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.phone like '%" + phone + "%'";
            } else {
                strFilter = strFilter + " and o.phone like '%" + phone + "%'";
            }
        }

        if (!parentCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.coaParent like '%" + parentCode + "%'";
            } else {
                strFilter = strFilter + " and o.coaParent like '%" + parentCode + "%'";
            }
        }
        if (!appTraderCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.appTraderCode ='" + appTraderCode + "'";
            } else {
                strFilter = strFilter + " and o.appTraderCode  '" + appTraderCode + "'";
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        strSql = strSql + " order by o.traderName";
        List<Trader> listTR = findHSQL(strSql);
        return listTR;
    }

    @Override
    public Trader saveTrader(Trader trader) {
        persist(trader);
        return trader;
    }

    @Override
    public List<Trader> searchM(String updatedDate) {
        String strSql = "select o from Trader o where o.updatedDate > '" + updatedDate + "'";
        List<Trader> listTrader = findHSQL(strSql);
        return listTrader;
    }

    @Override
    public List<Trader> search(String regionCode, String coaCode) {
        String hsql = null;
        if (!regionCode.equals("-")) {
            hsql = "select distinct o.region.regCode from Trader o where o.region.regCode = '" + regionCode + "'";
        } else if (!coaCode.equals("-")) {
            hsql = "select distinct o.account.code from Trader o where o.account.code = '" + coaCode + "'";
        }
        return findHSQL(hsql);
    }
}
