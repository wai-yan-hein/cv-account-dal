/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

import java.sql.ResultSet;
import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.entity.SaleHis;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Repository
public class SaleHisDaoImpl extends AbstractDao<String, SaleHis> implements SaleHisDao {

    @Override
    public SaleHis save(SaleHis sh) {
        persist(sh);
        return sh;
    }

    @Override
    public List<SaleHis> search(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId) {
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
        String strSql = "select o from SaleHis o";
        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter + " order by date(o.saleDate) desc, o.vouNo desc";
        }

        List<SaleHis> listSaleHis = findHSQL(strSql);
        return listSaleHis;
    }

    @Override
    public SaleHis findById(String id) {
        SaleHis sh = getByKey(id);
        return sh;
    }

    @Override
    public int delete(String vouNo) {
        String strSql1 = "delete from SaleDetailHis o where o.vouNo = '" + vouNo + "'";
        execUpdateOrDelete(strSql1);
        String strSql = "delete from SaleHis o where o.vouNo = '" + vouNo + "'";
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

    @Override
    public ResultSet searchM(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId) throws Exception {

        String strSql = "";

        if (!fromDate.equals("-") && !toDate.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "date(sh.sale_date) between '" + fromDate
                        + "' and '" + toDate + "'";
            } else {
                strSql = strSql + " and date(sh.sale_date) between '" + fromDate
                        + "' and '" + toDate + "'";
            }
        } else if (!fromDate.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "date(sh.sale_date) >= '" + fromDate + "'";
            } else {
                strSql = strSql + " and date(sh.sale_date) >= '" + fromDate + "'";
            }
        } else if (!toDate.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "date(sh.sale_date) <= '" + toDate + "'";
            } else {
                strSql = strSql + " and date(sh.sale_date) <= '" + toDate + "'";
            }
        }

        if (!cusId.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "sh.cus_id = " + cusId;
            } else {
                strSql = strSql + " and sh.cus_id = " + cusId;
            }
        }

        if (!vouStatusId.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "sh.vou_status_id = " + vouStatusId;
            } else {
                strSql = strSql + " and sh.vou_status_id = " + vouStatusId;
            }
        }

        if (!remark.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "sh.remark like '%" + remark + "%' ";
            } else {
                strSql = strSql + " and sh.remark like '%" + remark + "%' ";
            }
        }

        if (!userId.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "sh.user_id = '" + userId + "'";
            } else {
                strSql = strSql + " and sh.user_id = '" + userId + "'";
            }
        }

        if (!stockCode.equals("-")) {

            if (strSql.isEmpty()) {
                strSql = "sdh.comp_code =" + stockCode;
            } else {
                strSql = strSql + " and sdh.comp_code =" + stockCode;
            }
        }

        ResultSet rs = null;
        if (!strSql.isEmpty()) {
            strSql = " select distinct sh.sale_date, sh.voucher_no, sh.remark,td.trader_name,\n"
                    + " sh.grand_total, sh.deleted,\n"
                    + " apu.user_short_name \n"
                    + " from sale_his sh	\n"
                    + " join sale_his_detail shd ON shd.vou_id = sh.voucher_no\n"
                    + " join appuser apu on sh.user_id = apu.user_id\n"
                    + " left join trader td on sh.cus_id=td.id\n"
                    + " where " + strSql
                    + " and sh.deleted= false order by sh.sale_date desc, sh.voucher_no desc";
            rs = getResultSet(strSql);
        }

        return rs;
    }
}
