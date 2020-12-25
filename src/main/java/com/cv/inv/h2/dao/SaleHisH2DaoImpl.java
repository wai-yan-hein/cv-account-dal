/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.SaleHisH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAI
 */
@Repository
public class SaleHisH2DaoImpl extends AbstractDao<String, SaleHisH2> implements SaleHisH2Dao {

    @Override
    public SaleHisH2 save(SaleHisH2 sh) {
        persist(sh);
        return sh;
    }

    @Override
    public List<SaleHisH2> search(String fromDate, String toDate,
            String cusId, String vouStatusId, String remark, String stockCode, String userId) {
        String strFilter = "";

        if (!fromDate.equals("-") && !toDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "date(o.saleDate) between '" + fromDate
                        + "' and '" + toDate + "'";
            } else {
                strFilter = strFilter + " and date(o.saleDate) between '"
                        + fromDate + "' and '" + toDate + "'";
            }
        } else if (!fromDate.endsWith("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "date(o.saleDate) >= '" + fromDate + "'";
            } else {
                strFilter = strFilter + " and date(o.saleDate) >= '" + fromDate + "'";
            }
        } else if (!toDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "date(o.saleDate) <= '" + toDate + "'";
            } else {
                strFilter = strFilter + " and date(o.saleDate) <= '" + toDate + "'";
            }
        }

        if (!cusId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.traderId = '" + cusId + "'";
            } else {
                strFilter = strFilter + " and o.traderId = '" + cusId + "'";
            }
        }

        if (!vouStatusId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.vouStatusId = '" + vouStatusId + "'";
            } else {
                strFilter = strFilter + " and o.vouStatusId = '" + vouStatusId + "'";
            }
        }

        if (!remark.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.remark = '" + remark + "'";
            } else {
                strFilter = strFilter + " and o.remark = '" + remark + "'";
            }
        }

        if (!userId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.createdBy = '" + userId + "'";
            } else {
                strFilter = strFilter + " and o.createdBy = '" + userId + "'";
            }
        }

        /*if (!machId.equals("-")) {
            if (strFilter.isEmpty()) {
            strFilter = "o.vouNo like '" + machId + "%'";
            } else {
                strFilter = strFilter + " and o.vouNo like '" + machId + "%'";
            }
        }*/
        String strSql = "select o from SaleHisH2 o";
        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter + " order by date(o.saleDate) desc, o.vouNo desc";
        }

        List<SaleHisH2> listSaleHisH2 = findHSQL(strSql);
        return listSaleHisH2;
    }

    @Override
    public SaleHisH2 findById(String id) {
        SaleHisH2 sh = getByKey(id);
        return sh;
    }

    @Override
    public int delete(String vouNo) {
        String strSql1 = "delete from SaleHisDetailH2 o where o.vouNo = '" + vouNo + "'";
        execUpdateOrDelete(strSql1);
        String strSql = "delete from SaleHisH2 o where o.vouNo = '" + vouNo + "'";
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

}
