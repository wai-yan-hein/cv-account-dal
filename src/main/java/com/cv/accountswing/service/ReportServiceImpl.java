/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.COADao;
import com.cv.accountswing.dao.ReportDao;
import com.cv.accountswing.dao.TmpBalanceSheetDao;
import com.cv.accountswing.dao.TmpProfitAndLostDao;
import com.cv.accountswing.entity.ChartOfAccount;
import com.cv.accountswing.entity.helper.BalanceSheetRetObj;
import com.cv.accountswing.entity.helper.ProfitAndLostRetObj;
import com.cv.accountswing.util.Util1;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private ReportDao dao;
    @Autowired
    private COADao coaDao;
    @Autowired
    private TmpProfitAndLostDao tapDao;
    @Autowired
    private TmpBalanceSheetDao balDao;

    @Override
    public void genReport(String reportPath, String filePath, String fontPath,
            Map<String, Object> parameters) throws Exception {
        dao.genReport(reportPath, filePath, fontPath, parameters);
    }

    @Override
    public void getProfitLost(String plProcess, String from, String to, String dept,
            String currency, String comp, String userCode, String macId, String inventory) {
        try {
            dao.execSQLRpt("delete from tmp_profit_lost where mac_id = " + macId + "");
        } catch (Exception ex) {
            logger.error("delete tmp_profit_lost : " + ex.getMessage());
        }
        String strInsert = "insert into tmp_profit_lost(acc_code,curr_code,dept_code,\n "
                + "acc_total,comp_code, sort_order,mac_id)";
        String[] process = plProcess.split(",");
        int sortOrder = 1;

        from = Util1.toDateStrMYSQL(from, "dd/MM/yyyy");
        to = Util1.toDateStrMYSQL(to, "dd/MM/yyyy");
        String tmpFrom = Util1.addDateTo(from, -1);
        tmpFrom = Util1.toDateStrMYSQL(tmpFrom, "dd/MM/yyyy");
        //Sales Income 
        for (String tmp : process) {
            switch (tmp) {
                case "os":
                    try {
                    String sql = "select coa_code,curr_id,ifnull(dept_code,'-'),(dr_amt+cr_amt) acc_total,\n"
                            + "'" + comp + "'," + sortOrder + "," + macId + "\n"
                            + "from tmp_op_cl\n"
                            + "where mac_id = " + macId + " \n"
                            + "and coa_code = '" + inventory + "'";
                    dao.execSQLRpt(strInsert + "\n" + sql);
                } catch (Exception e) {
                    logger.error("OS :" + e.getMessage());
                }
                break;
                case "cs":
                   try {
                    String strCS = "select coa_code,curr_code,dept_code,amount,\n"
                            + "'" + comp + "'," + sortOrder + "," + macId + "\n"
                            + "from stock_op_value\n"
                            + "where coa_code = '" + inventory + "'\n"
                            + "and dept_code = '" + dept + "' or '-' ='" + dept + "'\n"
                            + "and comp_code = '" + comp + "'\n"
                            + "and curr_code = '" + currency + "'\n"
                            + "and date(tran_date) = '" + to + "'";
                    dao.execSQLRpt(strInsert + "\n" + strCS);
                } catch (Exception e) {
                    logger.error("CS : " + e.getMessage());
                }
                break;
                default:
                    String coaCode = getCOACode(tmp, comp);
                    try {
                        String sql = "select coa_code,curr_id,ifnull(dept_code,'-'),if(dr_amt>0,dr_amt*-1,cr_amt) acc_toal,\n"
                                + "'" + comp + "'," + sortOrder + "," + macId + "\n"
                                + "from tmp_op_cl\n"
                                + "where mac_id = " + macId + " \n"
                                + "and coa_code in (" + coaCode + ")";
                        dao.execSQLRpt(strInsert + "\n" + sql);
                    } catch (Exception e) {
                        logger.error("Default : " + e.getMessage());
                    }
                    break;
            }
            sortOrder++;
        }
    }

    public String getCOACode(String code, String compCode) {
        String tmp = "-";
        List<ChartOfAccount> listCoA = coaDao.getAllChild(code, compCode);
        if (!listCoA.isEmpty()) {
            tmp = "";
            tmp = listCoA.stream().map(coa -> String.format("'%s',", coa.getCode())).reduce(tmp, String::concat);
        }
        tmp = tmp.substring(0, tmp.length() - 1);
        return Util1.isNull(tmp, "-");
    }

    @Override
    public ProfitAndLostRetObj getPLCalculateValue(String compCode, String macId) {
        ProfitAndLostRetObj obj = new ProfitAndLostRetObj();
        String sql = "select abs(sum(acc_total)) acc_total,sort_order\n"
                + "from tmp_profit_lost\n"
                + "where mac_id = " + macId + " and comp_code ='" + compCode + "'\n"
                + "group by sort_order";
        ResultSet rs;
        try {
            rs = dao.executeSql(sql);
            if (rs != null) {
                while (rs.next()) {
                    double ttl = rs.getDouble("acc_total");
                    int order = rs.getInt("sort_order");
                    switch (order) {
                        case 1://Sale Income
                            obj.addSaleIncome(ttl);
                            break;
                        case 2://Opening Stock
                            obj.addOPStock(ttl);
                            break;
                        case 3://Purchase
                            obj.addPurchase(ttl);
                            break;
                        case 4://Closiing Stock
                            obj.addCLStock(ttl);
                            break;
                        case 5://Other Income
                            obj.addOtherIncome(ttl);
                            break;
                        case 6://Other Expense
                            obj.addOtherExpense(ttl);
                            break;
                    }
                }

            }
        } catch (Exception ex) {
            logger.error("getPLCalculateValue : " + ex.getMessage());
        }

        return obj;
    }

    @Override
    public void genGLReport(String from, String to, String sourceAcId, String acId, String compCode,
            String desp, String fromCurr, String toCurr, String ref, String dept, String tranSource,
            String vouNo, String cvId, String userCode, String glVouNo, String deptName, String traderName) {
    }

    @Override
    public void genBalanceSheet(String from, String to, String dept,
            String compCode, String curr, String macId, String blProcess) throws Exception {
        String strSqlDelete = "delete from tmp_balance_sheet where mac_id = '" + macId + "'";
        dao.execSQLRpt(strSqlDelete);
        String strInsert = "insert into tmp_balance_sheet(acc_code,curr_code,dept_code,\n "
                + "acc_total, comp_code, sort_order,mac_id)";
        String[] process = blProcess.split(",");
        int sortOrder = 1;
        for (String tmp : process) {
            String coaCode = getCOACode(tmp, compCode);
            String sql = "select coa_code,curr_id,ifnull(dept_code,'-'),if(dr_amt>0,dr_amt*-1,cr_amt) acc_toal,\n"
                    + "'" + compCode + "'," + sortOrder + "," + macId + "\n"
                    + "from tmp_op_cl\n"
                    + "where mac_id = " + macId + " \n"
                    + "and coa_code in (" + coaCode + ")";
            dao.execSQLRpt(strInsert + "\n" + sql);
            sortOrder++;
        }
    }

    @Override
    public Object getAggResult(String sql
    ) {
        return dao.getAggResult(sql);
    }

    @Override
    public BalanceSheetRetObj getBSCalculateValue(String compCode, String macId) throws Exception {
        ProfitAndLostRetObj pl = getPLCalculateValue(compCode, macId);
        double profit = Util1.getDouble(pl.getNetProfit());
        String delSql = "delete from tmp_balance_sheet where mac_id = " + macId + " and sort_order= 10";
        String insertPL = "insert into tmp_balance_sheet(acc_code,curr_code,dept_code,acc_total,comp_code, sort_order,mac_id)\n"
                + "select '-','-','-'," + profit + ",'" + compCode + "',10," + macId + "";
        dao.execSQLRpt(delSql, insertPL);
        //
        BalanceSheetRetObj bs = new BalanceSheetRetObj();
        bs.setProfit(profit);
        String sql = "select abs(sum(acc_total)) acc_total,sort_order\n"
                + "from tmp_balance_sheet\n"
                + "where mac_id = " + macId + " and comp_code ='" + compCode + "'\n"
                + "group by sort_order";
        ResultSet rs;
        try {
            rs = dao.executeSql(sql);
            if (rs != null) {
                while (rs.next()) {
                    double ttl = rs.getDouble("acc_total");
                    int order = rs.getInt("sort_order");
                    switch (order) {
                        case 1:
                            bs.setFixedAss(ttl);
                            break;
                        //curr
                        case 2:
                            bs.setCurrentAss(ttl);
                            break;
                        //lia
                        case 3:
                            bs.setLiabilitie(ttl);
                            break;
                        //capital
                        case 4:
                            bs.setCapital(ttl);
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("getPLCalculateValue : " + ex.getMessage());
        }
        return bs;
    }
}
