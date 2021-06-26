/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import java.sql.ResultSet;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class ReportDaoImpl extends AbstractDao<String, String> implements ReportDao {

    @Override
    public void genReport(String reportPath, String filePath, String fontPath,
            Map<String, Object> parameters) throws Exception {
        doReportJasper(reportPath, filePath, parameters, fontPath);
    }

    @Override
    public void execSQLRpt(String... strSql) throws Exception {
        execSQL(strSql);
    }

    @Override
    public Object getAggResult(String sql) {
        return getAggregate(sql);
    }

    @Override
    public ResultSet executeSql(String sql) throws Exception{
        return getResultSet(sql);
    }
}
