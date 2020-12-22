/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/dao/RetInDaoImpl.java
import com.cv.account.api.dao.AbstractDao;
import com.cv.account.inv.entity.RetInHis;
import java.sql.ResultSet;
=======
import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.entity.RetInHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/dao/RetInDaoImpl.java
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public class RetInDaoImpl extends AbstractDao<String, RetInHis> implements RetInDao {

    @Override
    public RetInHis save(RetInHis retInDetailHis) {
        persist(retInDetailHis);
        return retInDetailHis;
    }

    @Override
    public List<RetInHis> search(String fromDate, String toDate, String cusId, String locId, String vouNo, String filterCode) {
        String strFilter = "";

        if (!fromDate.equals("-") && !toDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.retInDate between '" + fromDate
                        + "' and '" + toDate + "'";
            } else {
                strFilter = strFilter + " and o.retInDate between '"
                        + fromDate + "' and '" + toDate + "'";
            }
        } else if (!fromDate.endsWith("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.retInDate >= '" + fromDate + "'";
            } else {
                strFilter = strFilter + " and o.retInDate >= '" + fromDate + "'";
            }
        } else if (!toDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.retInDate <= '" + toDate + "'";
            } else {
                strFilter = strFilter + " and o.retInDate <= '" + toDate + "'";
            }
        }

        if (!cusId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.customer = '" + cusId + "'";
            } else {
                strFilter = strFilter + " and o.customer = '" + cusId + "'";
            }
        }

        if (!locId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.location = '" + locId + "'";
            } else {
                strFilter = strFilter + " and o.location = '" + locId + "'";
            }
        }

        if (!vouNo.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.retInId = '" + vouNo + "'";
            } else {
                strFilter = strFilter + " and o.retInId = '" + vouNo + "'";
            }
        }
        if (!filterCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.remark = '" + filterCode + "'";
            } else {
                strFilter = strFilter + " and o.remark = '" + filterCode + "'";
            }
        }
        String strSql = "select o from RetInHis o";
        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<RetInHis> listPurHis = findHSQL(strSql);
        return listPurHis;
    }

    @Override
    public ResultSet searchM(String fromDate, String toDate, String cusId,
            String locId, String vouNo, String filterCode) throws Exception {
        String strFilter = "";

        if (!fromDate.equals("-") && !toDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = " date(retin.ret_in_date) between '" + fromDate
                        + "' and '" + toDate + "'";
            } else {
                strFilter = strFilter + " and date(retin.ret_in_date) between '"
                        + fromDate + "' and '" + toDate + "'";
            }
        } else if (!fromDate.endsWith("-")) {
            if (strFilter.isEmpty()) {
                strFilter = " date(retin.ret_in_date) >= '" + fromDate + "'";
            } else {
                strFilter = strFilter + " date(retin.ret_in_date) >= '" + fromDate + "'";
            }
        } else if (!toDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = " date(retin.ret_in_date) <= '" + toDate + "'";
            } else {
                strFilter = strFilter + " and  date(retin.ret_in_date) <= '" + toDate + "'";
            }
        }

        if (!cusId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "retin.cus_id = '" + cusId + "'";
            } else {
                strFilter = strFilter + " and retin.cus_id = '" + cusId + "'";
            }
        }

        if (!locId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "retin.location = '" + locId + "'";
            } else {
                strFilter = strFilter + " and retin.location = '" + locId + "'";
            }
        }

        if (!vouNo.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = " retin.ret_in_id = '" + vouNo + "'";
            } else {
                strFilter = strFilter + " and retin.ret_in_id = '" + vouNo + "'";
            }
        }
        if (!filterCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "retin.remark = '" + filterCode + "'";
            } else {
                strFilter = strFilter + " and retin.remark = '" + filterCode + "'";
            }
        }

        ResultSet rs = null;
        if (!strFilter.isEmpty()) {
            strFilter = "select distinct retin.ret_in_date, retin.ret_in_id, retin.remark,td.trader_name,\n"
                    + " retin.vou_total, retin.deleted, l.location_name,\n"
                    + "  apu.user_short_name from ret_in_his retin\n"
                    + "  join ret_in_his_detail rihd on rihd.voucher_no=retin.ret_in_id\n"
                    + " join location l on retin.location = l.location_id\n"
                    + " join appuser apu on retin.created_by = apu.user_id\n"
                    + "  left join trader td on retin.cus_id=td.id\n"
                    + "  where " + strFilter
                    + " and retin.deleted=false\n"
                    + "  order by retin.ret_in_date desc, retin.ret_in_id desc";
            rs = getResultSet(strFilter);
        }

        return rs;
    }

    @Override
    public RetInHis findById(String id) {
        RetInHis ph = getByKey(id);
        return ph;
    }

}
