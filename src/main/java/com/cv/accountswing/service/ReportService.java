/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.helper.ProfitAndLostRetObj;
import java.util.Map;

/**
 *
 * @author winswe
 */
public interface ReportService {

    public void genCreditVoucher(String reportPath, String filePath, String fontPath,
            Map<String, Object> parameters) throws Exception;

    public void getProfitLost(String plProcess, String from, String to, String dept,
            String currency, String comp, String userCode) throws Exception;

    public ProfitAndLostRetObj getPLCalculateValue(String userCode, String compCode);

    public void genGLReport(String from, String to, String sourceAcId, String acId, String compCode,
            String desp, String fromCurr, String toCurr, String ref, String dept, String tranSource,
            String vouNo, String cvId, String userCode, String glVouNo, String deptName, String traderName);

    public void genBalanceSheet(String from, String to, String dept, String userCode,
            String compCode, String curr) throws Exception;

    public Object getAggResult(String sql);
}
