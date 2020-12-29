/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.accountswing.entity.Trader;
import com.cv.inv.h2.entity.TraderH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WSwe
 */
@Repository
public class TraderH2DaoImpl extends AbstractDao<Integer, TraderH2> implements TraderH2Dao {

    @Override
    public TraderH2 findById(Integer id) {
        TraderH2 trader = getByKey(id);
        return trader;
    }

    @Override
    public List<TraderH2> searchTrader(String code, String name, String address,
            String phone, String parentCode, String compCode, String appTraderCode) {
        String strSql = "select o from TraderH2 o ";
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
                strFilter = "o.traderId like '" + code + "%'";
            } else {
                strFilter = strFilter + " and o.traderId like '" + code + "%'";
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
                strFilter = "o.parent like '%" + parentCode + "%'";
            } else {
                strFilter = strFilter + " and o.parent like '%" + parentCode + "%'";
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
        List<TraderH2> listTR = findHSQL(strSql);
        return listTR;
    }

    @Override
    public TraderH2 saveTrader(TraderH2 trader) {
        persist(trader);
        return trader;
    }

    @Override
    public List<TraderH2> searchM(String updatedDate) {
        String strSql = "select o from TraderH2 o where o.updatedDate > '" + updatedDate + "'";
        List<TraderH2> listTrader = findHSQL(strSql);
        return listTrader;
    }
}
