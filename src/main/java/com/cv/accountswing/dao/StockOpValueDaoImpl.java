/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.util.Util1;
import com.cv.accountswing.entity.StockOpValue;
import com.cv.accountswing.entity.StockOpValueKey;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class StockOpValueDaoImpl extends AbstractDao<StockOpValueKey, StockOpValue> implements StockOpValueDao {

    @Override
    public StockOpValue save(StockOpValue value) {
        persist(value);
        return value;
    }

    @Override
    public StockOpValue findById(StockOpValueKey key) {
        StockOpValue value = getByKey(key);
        return value;
    }

    @Override
    public List search(String from, String to, String coaCode, String currency,
            String dept, String compCode) {
        String strSql = "select o from VStockOpValue o";
        String strFilter = "";

        if (!from.equals("-") && !to.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "date(o.key.tranDate) between '" + Util1.toDateStrMYSQL(from, "dd/MM/yyyy")
                        + "' and '" + Util1.toDateStrMYSQL(to, "dd/MM/yyyy") + "'";
            } else {
                strFilter = strFilter + " and date(o.key.tranDate) between '"
                        + Util1.toDateStrMYSQL(from, "dd/MM/yyyy") + "' and '" + Util1.toDateStrMYSQL(to, "dd/MM/yyyy") + "'";
            }
        } else if (!from.endsWith("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "date(o.key.tranDate) >= '" + Util1.toDateStrMYSQL(from, "dd/MM/yyyy") + "'";
            } else {
                strFilter = strFilter + " and date(o.key.tranDate) >= '" + Util1.toDateStrMYSQL(from, "dd/MM/yyyy") + "'";
            }
        } else if (!to.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "date(o.key.tranDate) <= '" + Util1.toDateStrMYSQL(to, "dd/MM/yyyy") + "'";
            } else {
                strFilter = strFilter + " and date(o.key.tranDate) <= '" + Util1.toDateStrMYSQL(to, "dd/MM/yyyy") + "'";
            }
        }

        if (!coaCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.coaCode = '" + coaCode + "'";
            } else {
                strFilter = strFilter + " and o.key.coaCode = '" + coaCode + "'";
            }
        }

        if (!currency.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.currency = '" + currency + "'";
            } else {
                strFilter = strFilter + " and o.key.currency = '" + currency + "'";
            }
        }

        if (!dept.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.key.deptCode = '" + dept + "'";
            } else {
                strFilter = strFilter + " and o.key.deptCode = '" + dept + "'";
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

        List<StockOpValue> listValue = findHSQL(strSql + " order by o.key.tranDate");
        return listValue;
    }

    @Override
    public void backup(String tranDate, String coaCode, String dept, String currency,
            String compCode, String userCode, String option) throws Exception {
        String strSql = "insert into stock_op_value_log(tran_date, coa_code, dept_code,\n"
                + "  curr_code, comp_code, amount, remark, user_code, log_date, created_by, created_date, updated_by, updated_date, log_option)\n"
                + "select tran_date, coa_code, dept_code, curr_code, comp_code, amount, remark, '" + userCode + "'"
                + ", '" + Util1.getTodayDateTimeStrMySql()
                + "', created_by, created_date, updated_by, updated_date, '" + option + "' "
                + "from stock_op_value where date(tran_date) = '" + tranDate + "' and coa_code = '" + coaCode
                + "' and dept_code = '" + dept + "' and curr_code = '" + currency + "' and comp_code = " + compCode;
        execSQL(strSql);
    }

    @Override
    public int delete(String tranDate, String coaCode, String dept, String currency,
            String compCode) {
        String strSql = "delete from StockOpValue o where date(o.key.tranDate) = '"
                + tranDate + "' and o.key.coaCode = '" + coaCode
                + "' and o.key.deptCode = '" + dept + "' and o.key.currency = '" + currency
                + "' and o.key.compCode = '" + compCode + "'";
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

    @Override
    public List<StockOpValue> findAll() {
        String strSql = "select o from StockOpValue o";
        List<StockOpValue> ListSOV = findHSQL(strSql);
        return ListSOV;
    }

}
